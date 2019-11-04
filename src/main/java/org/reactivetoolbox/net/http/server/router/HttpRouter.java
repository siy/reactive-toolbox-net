package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.net.http.server.RequestContext;

public interface HttpRouter {
    <T> Result<Route<T>> locateHandler(final RequestContext<T> context);
}
