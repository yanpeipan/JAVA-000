import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import static io.netty.buffer.Unpooled.copiedBuffer;

public class NettyHttpServer {

    private final EventLoopGroup masterGroup;
    private final EventLoopGroup slaveGroup;
    private ChannelFuture channel;

    public NettyHttpServer() {
        masterGroup = new NioEventLoopGroup();
        slaveGroup = new NioEventLoopGroup();
    }

    public static void main(String[] args) {
        new NettyHttpServer().start();
    }

    public void start() // #1
    {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdown();
            }
        });

        try {
            // #3
            final ServerBootstrap bootstrap =
                    new ServerBootstrap()
                            .group(masterGroup, slaveGroup)
                            .channel(NioServerSocketChannel.class)
                            .childHandler(new ChannelInitializer<SocketChannel>() // #4
                            {
                                @Override
                                public void initChannel(final SocketChannel ch)
                                        throws Exception {
                                    ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                                    ch.pipeline().addLast("codec", new HttpServerCodec());
                                    ch.pipeline().addLast("aggregator",
                                            new HttpObjectAggregator(512 * 1024));
                                    ch.pipeline().addLast("request",
                                            new ChannelInboundHandlerAdapter() // #5
                                            {
                                                @Override
                                                public void channelRead(ChannelHandlerContext ctx, Object msg)
                                                        throws Exception {
                                                    if (msg instanceof FullHttpRequest) {
                                                        final FullHttpRequest request = (FullHttpRequest) msg;

                                                        final String responseMessage = "Hello from Netty!";

                                                        FullHttpResponse response = new DefaultFullHttpResponse(
                                                                HttpVersion.HTTP_1_1,
                                                                HttpResponseStatus.OK,
                                                                copiedBuffer(responseMessage.getBytes())
                                                        );

                                                        if (HttpHeaders.isKeepAlive(request)) {
                                                            response.headers().set(
                                                                    HttpHeaders.Names.CONNECTION,
                                                                    HttpHeaders.Values.KEEP_ALIVE
                                                            );
                                                        }
                                                        response.headers().set(HttpHeaders.Names.CONTENT_TYPE,
                                                                "text/plain");
                                                        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
                                                                responseMessage.length());

                                                        ctx.writeAndFlush(response);
                                                    } else {
                                                        super.channelRead(ctx, msg);
                                                    }
                                                }

                                                @Override
                                                public void channelReadComplete(ChannelHandlerContext ctx)
                                                        throws Exception {
                                                    ctx.flush();
                                                }

                                                @Override
                                                public void exceptionCaught(ChannelHandlerContext ctx,
                                                                            Throwable cause) throws Exception {
                                                    ctx.writeAndFlush(new DefaultFullHttpResponse(
                                                            HttpVersion.HTTP_1_1,
                                                            HttpResponseStatus.INTERNAL_SERVER_ERROR,
                                                            copiedBuffer(cause.getMessage().getBytes())
                                                    ));
                                                }
                                            });
                                }
                            })
                            .option(ChannelOption.SO_BACKLOG, 128)
                            .childOption(ChannelOption.SO_KEEPALIVE, true);
            channel = bootstrap.bind(8088).sync();
        } catch (final InterruptedException e) {
        }
    }

    public void shutdown() // #2
    {
        slaveGroup.shutdownGracefully();
        masterGroup.shutdownGracefully();

        try {
            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
        }
    }
}
