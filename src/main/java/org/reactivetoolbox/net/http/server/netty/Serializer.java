package org.reactivetoolbox.net.http.server.netty;

import io.netty.buffer.ByteBuf;

public interface Serializer {
    ByteBuf serialize(final Object value);
}
