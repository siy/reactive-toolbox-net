package org.reactivetoolbox.net.http.server.router;

import org.junit.jupiter.api.Test;
import org.reactivetoolbox.net.http.ContentType;

import static org.reactivetoolbox.core.async.Promise.readyOk;
import static org.reactivetoolbox.net.http.server.router.RequestParameters.inPath;
import static org.reactivetoolbox.net.http.server.router.RouteBuilder.get;

class HttpRouteBuilderTest {

    @Test
    void routeCanBeCreated() {
        get("/users/{id}")
                .accepts(ContentType.TEXT_PLAIN)
                .returns(ContentType.JSON)
                .with(inPath(String.class, "id"))
                .then(id -> readyOk(""));
    }
}