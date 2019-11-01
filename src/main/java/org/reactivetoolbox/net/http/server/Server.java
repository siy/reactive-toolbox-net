package org.reactivetoolbox.net.http.server;

import org.reactivetoolbox.core.async.Promise;

public interface Server {
    Promise<Server> start();

    Promise<Server> stop();
}
