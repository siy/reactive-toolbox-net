package org.reactivetoolbox.net.http.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import org.reactivetoolbox.core.lang.Failure;
import org.reactivetoolbox.net.http.ContentType;
import org.reactivetoolbox.net.http.server.router.HttpRouter;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderNames.DATE;
import static io.netty.handler.codec.http.HttpHeaderNames.SERVER;
import static io.netty.handler.codec.http.HttpHeaderValues.CLOSE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpResponseStatus.valueOf;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import static org.reactivetoolbox.net.http.ContentType.TEXT_PLAIN;

class NettyServerHandler extends SimpleChannelInboundHandler<Object> {
    private final HttpRouter router;

    NettyServerHandler(final HttpRouter router) {
        this.router = router;
    }

    /**
     * Handles a new message.
     *
     * @param ctx
     *         The channel context.
     * @param msg
     *         The HTTP request message.
     */
    @Override
    public void channelRead0(final ChannelHandlerContext ctx, final Object msg) {
        if (!(msg instanceof FullHttpRequest)) {
            return;
        }

        final FullHttpRequest request = (FullHttpRequest) msg;

        if (HttpUtil.is100ContinueExpected(request)) {
            ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
        }

        //TODO: move writeResponse to context
        final var context = new NettyRequestContext(ctx, request);

        router.locateHandler(context)
              .onSuccess(route -> route.handler().apply(context)
                                       //TODO: move conversion to handler
                                       //TODO: add content type-specific conversion of the result
                                       .onResult(result -> result.onSuccess(value -> context.writeResponse(OK,
                                                                                                           route.outputContentType(),
                                                                                                           serialize(value,
                                                                                                                     route.outputContentType())))
                                                                 .onFailure(failure -> context.writeResponse(toStatus(failure),
                                                                                                             TEXT_PLAIN,
                                                                                                             serializeString(failure.message())))))
              .onFailure(failure -> context.writeResponse(NOT_FOUND,
                                                          TEXT_PLAIN,
                                                          serializeString(NOT_FOUND.reasonPhrase())));
    }

    private static HttpResponseStatus toStatus(final Failure failure) {
        return valueOf(failure.type().code(), failure.message());
    }

    //TODO: add error handling
    private ByteBuf serialize(final Object value, final ContentType outputContentType) {
        return lookupSerializer(outputContentType).serialize(value);
    }

    //TODO: add support for more content types
    private Serializer lookupSerializer(final ContentType outputContentType) {
        if (outputContentType == TEXT_PLAIN) {
            return value -> serializeString(value.toString());
        }

        return value -> serializeString("No suitable serializer found for given content type");
    }

    private static ByteBuf serializeString(final String value) {
        return Unpooled.wrappedBuffer(value.getBytes(UTF_8));
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        ctx.close();
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
