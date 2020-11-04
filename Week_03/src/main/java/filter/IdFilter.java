package filter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class IdFilter implements Filter {

    @Override
    public void filter(FullHttpRequest httpRequest) {
        httpRequest.headers().add("ID", "124");
    }

    @Override
    public void filter(FullHttpResponse httpResponse) {
        httpResponse.headers().add("IDI", "124");
    }
}
