package org.reactivetoolbox.net.http.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import org.reactivetoolbox.net.http.ContentType;
import org.reactivetoolbox.net.http.server.NativeBuffer;
import org.reactivetoolbox.net.http.server.RequestContext;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderNames.DATE;
import static io.netty.handler.codec.http.HttpHeaderNames.SERVER;
import static io.netty.handler.codec.http.HttpHeaderValues.CLOSE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

class NettyRequestContext implements RequestContext<ByteBuf> {
    private static final String SERVER_NAME = "Reactive Toolbox HTTP Server (Netty)";

    private final ChannelHandlerContext context;
    private final FullHttpRequest request;
    private final ServerConfig config;
    private final boolean keepAlive;

    NettyRequestContext(final ChannelHandlerContext context, final FullHttpRequest request, final ServerConfig config) {
        this.context = context;
        this.request = request;
        this.keepAlive = HttpUtil.isKeepAlive(request);
        this.config = config;
    }

    public void writeResponse(final HttpResponseStatus status,
                              final ContentType contentType,
                              final ByteBuf content) {

        final var response = buildResponse(status, contentType, content);

        context.writeAndFlush(response)
               .addListener(keepAlive ? (ch) -> {} : ChannelFutureListener.CLOSE);
    }

    private FullHttpResponse buildResponse(final HttpResponseStatus status,
                                           final ContentType contentType,
                                           final ByteBuf content) {
        final var response = new DefaultFullHttpResponse(HTTP_1_1, status, content, false);

        setKeepAliveHeader(response.headers()).set(SERVER, SERVER_NAME)
                                              .set(DATE, now(UTC).format(RFC_1123_DATE_TIME))
                                              .set(CONTENT_TYPE, contentType.get())
                                              .setInt(CONTENT_LENGTH, content.readableBytes());

        return response;
    }

    private HttpHeaders setKeepAliveHeader(final HttpHeaders headers) {
        if (keepAlive) {
            if (!request.protocolVersion().isKeepAliveDefault()) {
                headers.set(CONNECTION, KEEP_ALIVE);
            }
        } else {
            headers.set(CONNECTION, CLOSE);
        }

        return headers;
    }

    @Override
    public NativeBuffer<ByteBuf> allocate() {
        return new NettyNativeBuffer(context.alloc().buffer(config.initialBufferSize(), config.maxBufferSize()));
    }
}
