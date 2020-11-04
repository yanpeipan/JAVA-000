package filter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

abstract interface Filter {
    void filter(FullHttpRequest msg);

    void filter(FullHttpResponse msg);
}
