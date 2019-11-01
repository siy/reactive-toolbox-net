package org.reactivetoolbox.net.http.server.netty;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import org.reactivetoolbox.net.http.server.RequestContext;

public class NettyRequestContext implements RequestContext {
    private final FullHttpRequest request;

    public NettyRequestContext(final FullHttpRequest request) {
        this.request = request;
    }

    @Override
    public boolean keepAlive() {
        return HttpUtil.isKeepAlive(request);
    }
}