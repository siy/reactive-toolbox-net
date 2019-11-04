package org.reactivetoolbox.net.http.server;

public interface RequestContext<T> {
    NativeBuffer<T> allocate();
}
