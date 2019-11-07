package org.reactivetoolbox.net.http.server.router.impl;

import org.reactivetoolbox.core.async.Promise;
import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.net.http.ContentType;
import org.reactivetoolbox.net.http.server.NativeBuffer;
import org.reactivetoolbox.net.http.server.RequestContext;
import org.reactivetoolbox.net.http.server.router.Path;
import org.reactivetoolbox.net.http.server.router.Route;

public class RouteImpl implements Route {
    private final FN1<Promise<NativeBuffer>, RequestContext> handler;
    private final Path path;
    private final ContentType inputType;
    private final ContentType outputType;

    public RouteImpl(final Path path,
                     final FN1<Promise<NativeBuffer>, RequestContext> handler,
                     final ContentType inputType,
                     final ContentType outputType) {
        this.handler = handler;
        this.path = path;
        this.inputType = inputType;
        this.outputType = outputType;
    }

    @Override
    public FN1<Promise<NativeBuffer>, RequestContext> handler() {
        return handler;
    }

    @Override
    public Path path() {
        return path;
    }

    @Override
    public ContentType inputType() {
        return inputType;
    }

    @Override
    public ContentType outputType() {
        return outputType;
    }

    @Override
    public Route prefix(final String prefix) {
        return new RouteImpl(path.prefix(prefix), handler, inputType, outputType);
    }
}
