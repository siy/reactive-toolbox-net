package org.reactivetoolbox.net.http.server.router;

import io.netty.buffer.ByteBuf;
import org.reactivetoolbox.core.async.Promise;
import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.net.http.ContentType;
import org.reactivetoolbox.net.http.server.RequestContext;

public interface Route {
    //TODO: common data structure to return (ByteBuffer?)
    FN1<Promise<ByteBuf>, RequestContext> handler();

    //TODO: use better type
    ContentType outputContentType();
}
