import inbound.HttpInboundServer;

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer", "http://localhost:8088");
        String proxyPort = System.getProperty("proxyPort", "8888");

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer s = new HttpInboundServer(Integer.parseInt(proxyPort), proxyServer);

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + proxyPort + " for server:" + proxyServer);
        try {
            s.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
