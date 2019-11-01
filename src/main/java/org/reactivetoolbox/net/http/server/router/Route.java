package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.async.Promise;
import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.net.http.server.RequestContext;

public interface Route {
    FN1<Promise<?>, RequestContext> handler();

    //TODO: use better type
    CharSequence outputContentType();
}
