package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.net.http.server.RequestContext;

public interface HttpRouter {
    Result<Route> locateHandler(final RequestContext context);
}
