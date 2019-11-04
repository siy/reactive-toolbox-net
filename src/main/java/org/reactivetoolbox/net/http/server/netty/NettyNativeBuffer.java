package org.reactivetoolbox.net.http.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.reactivetoolbox.net.http.server.NativeBuffer;

import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.*;

public class NettyNativeBuffer implements NativeBuffer<ByteBuf> {
    private final ByteBuf buffer;

    public NettyNativeBuffer(final ByteBuf buffer) {
        this.buffer = buffer;
    }

    @Override
    public ByteBuf asNative() {
        return buffer;
    }

    @Override
    public NativeBuffer<ByteBuf> write(final String value) {
        buffer.writeBytes(value.getBytes(UTF_8));
        return this;
    }
}
