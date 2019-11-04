package org.reactivetoolbox.net.http.server;

import org.reactivetoolbox.core.lang.List;

//TODO: switch to list of native buffers?
public interface NativeBuffer<T> {
    T asNative();

    NativeBuffer<T> write(final String value);
}
