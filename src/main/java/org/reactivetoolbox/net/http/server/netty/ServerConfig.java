package org.reactivetoolbox.net.http.server.netty;

public class ServerConfig {
    public int maxInputContentLength() {
        return 65536;
    }

    public boolean enableSSL() {
        return false;
    }

    public int port() {
        return 8080;
    }
}
