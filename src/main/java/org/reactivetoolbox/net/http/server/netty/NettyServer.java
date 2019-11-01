package org.reactivetoolbox.net.http.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.reactivetoolbox.core.async.Promise;
import org.reactivetoolbox.core.lang.Failure;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.support.WebFailureTypes;
import org.reactivetoolbox.net.http.server.router.HttpRouter;
import org.reactivetoolbox.net.http.server.Server;

/**
 * The WebServer class is a convenience wrapper around the Netty HTTP server.
 */
public class NettyServer implements Server {

    private final HttpRouter router;
    private final ServerConfig config;

    /**
     * Creates a new WebServer.
     *
     * @param port
     * @param config
     * @param router
     */
    public NettyServer(final ServerConfig config, final HttpRouter router) {
        this.router = router;
        this.config = config;
    }

    /**
     * Starts the web server.
     */
    @Override
    public Promise<Server> start() {
        if (Epoll.isAvailable()) {
            return start(new EpollEventLoopGroup(1), new EpollEventLoopGroup(), EpollServerSocketChannel.class);
        } else if (KQueue.isAvailable()) {
            return start(new KQueueEventLoopGroup(1), new EpollEventLoopGroup(), KQueueServerSocketChannel.class);
        } else {
            return start(new NioEventLoopGroup(1), new EpollEventLoopGroup(), NioServerSocketChannel.class);
        }
    }

    /**
     * Initializes the server, socket, and channel.
     *
     * @param loopGroup
     *         The event loop group.
     * @param serverChannelClass
     *         The socket channel class.
     */
    private Promise<Server> start(final EventLoopGroup parentGroup,
                                  final EventLoopGroup loopGroup,
                                  final Class<? extends ServerChannel> serverChannelClass) {

        try {
            final ChannelFuture bindFuture = new ServerBootstrap()
                    .group(parentGroup, loopGroup)
                    .channel(serverChannelClass)
                    .childHandler(new NettyServerInitializer(router, config))
                    .bind(config.port());

            return Promise.promise(promise -> bindFuture.addListener(v -> promise.resolve(Result.success(this))));

        } catch (final Exception e) {
            return Promise.fulfilled(Failure.with(WebFailureTypes.INTERNAL_SERVER_ERROR, "Server interrupted").asFailure());
        }
    }


}