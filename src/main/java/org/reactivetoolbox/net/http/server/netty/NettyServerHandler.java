package org.reactivetoolbox.net.http.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import org.reactivetoolbox.net.http.server.router.HttpRouter;
import org.reactivetoolbox.net.http.server.RequestContext;

import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.CLOSE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * The Handler class handles all inbound channel messages.
 */
class NettyServerHandler extends SimpleChannelInboundHandler<Object> {
    public static final String TYPE_PLAIN = "text/plain; charset=UTF-8";
    public static final String TYPE_JSON = "application/json; charset=UTF-8";
    public static final String SERVER_NAME = "Reactive Toolbox Core (Netty)";

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
            send100Continue(ctx);
        }

        final RequestContext context = new NettyRequestContext(request);

        router.locateHandler(context)
              .onSuccess(route -> route.handler().apply(context)
                                           //TODO: add content type-specific conversion of the result
                                           .onResult(result -> result.onSuccess(value -> writeOkResponse(ctx,
                                                                                                         context.keepAlive(),
                                                                                                         HttpResponseStatus.OK,
                                                                                                         route.outputContentType(),
                                                                                                         value.toString()))
                                                                     .onFailure(failure -> writeErrorResponse(ctx,
                                                                                                              context.keepAlive(),
                                                                                                              HttpResponseStatus.valueOf(failure.type().code(),
                                                                                                                                         failure.message())))))
              .onFailure(failure -> writeErrorResponse(ctx, context.keepAlive(), HttpResponseStatus.NOT_FOUND));
    }


    /**
     * Handles an exception caught.  Closes the context.
     *
     * @param ctx
     *         The channel context.
     * @param cause
     *         The exception.
     */
    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        ctx.close();
    }


    /**
     * Handles read complete event.  Flushes the context.
     *
     * @param ctx
     *         The channel context.
     */
    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * Writes a HTTP error response.
     *
     * @param ctx
     *         The channel context.
     * @param request
     *         The HTTP request.
     * @param status
     *         The error status.
     */
    private static void writeErrorResponse(
            final ChannelHandlerContext ctx,
            final boolean keepAlive,
            final HttpResponseStatus status) {

        writeOkResponse(ctx, keepAlive, status, TYPE_PLAIN, status.reasonPhrase());
    }


    /**
     * Writes a HTTP response.
     *
     * @param ctx
     *         The channel context.
     * @param request
     *         The HTTP request.
     * @param status
     *         The HTTP status code.
     * @param contentType
     *         The response content type.
     * @param content
     *         The response content.
     */
    private static void writeOkResponse(
            final ChannelHandlerContext ctx,
            final boolean keepAlive,
            final HttpResponseStatus status,
            final CharSequence contentType,
            final String content) {

        final FullHttpResponse response = buildResponse(status,
                                                        contentType,
                                                        keepAlive,
                                                        content.getBytes(StandardCharsets.UTF_8));

        ctx.writeAndFlush(response).addListener(keepAlive ? (ch) -> {} : ChannelFutureListener.CLOSE);
    }

    private static FullHttpResponse buildResponse(final HttpResponseStatus status, final CharSequence contentType, boolean keepAlive, final byte[] bytes) {
        final FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                      status,
                                                                      Unpooled.wrappedBuffer(bytes), false);

        response.headers()
                .set(HttpHeaderNames.SERVER, SERVER_NAME)
                .set(HttpHeaderNames.DATE, ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME))
                .set(HttpHeaderNames.CONTENT_TYPE, contentType)
                //TODO: KEEP_ALIVE is needed only for HTTP 1.0
                .set(CONNECTION, keepAlive ? KEEP_ALIVE : CLOSE)
                .setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

        return response;
    }

    /**
     * Writes a 100 Continue response.
     *
     * @param ctx
     *         The HTTP handler context.
     */
    private static void send100Continue(final ChannelHandlerContext ctx) {
        ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
    }
}
