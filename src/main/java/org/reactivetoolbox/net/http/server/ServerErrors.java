package org.reactivetoolbox.net.http.server;

import org.reactivetoolbox.core.lang.Failure;
import org.reactivetoolbox.core.lang.support.WebFailureTypes;

public interface ServerErrors {
    Failure SSL_NOT_CONFIGURED = Failure.of(WebFailureTypes.NOT_IMPLEMENTED, "SSL is not configured or configuration is not applicable");
    //Failure CANCELLED = Failure.of(WebFailureTypes.NO_RESPONSE, "Request cancelled");
}
