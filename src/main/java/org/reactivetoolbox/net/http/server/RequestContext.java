package org.reactivetoolbox.net.http.server;

public interface RequestContext extends ParsingContext {
    NativeBuffer allocate();
}
