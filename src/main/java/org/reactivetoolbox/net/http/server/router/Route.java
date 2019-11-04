package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.async.Promise;
import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.net.http.ContentType;
import org.reactivetoolbox.net.http.server.NativeBuffer;
import org.reactivetoolbox.net.http.server.RequestContext;

public interface Route<T> {
    FN1<Promise<NativeBuffer<T>>, RequestContext<T>> handler();

    ContentType outputContentType();
}
