package outbound.okhttp;

import filter.IdFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler extends ChannelInboundHandlerAdapter {

    private String url;

    public OkhttpOutboundHandler(String url) {
        this.url = url;
    }

    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {

        OkHttpClient client = new OkHttpClient();

        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            FullHttpResponse proxyResponse = new DefaultFullHttpResponse(
                    HTTP_1_1, OK, Unpooled.wrappedBuffer(response.body().bytes()));
            proxyResponse.headers().set(CONTENT_TYPE, response.headers().get(String.valueOf(CONTENT_TYPE)));
            proxyResponse.headers().set(CONTENT_LENGTH,
                    proxyResponse.content().readableBytes());
            proxyResponse.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);

            new IdFilter().filter(proxyResponse);

            ctx.write(proxyResponse);
            ctx.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
