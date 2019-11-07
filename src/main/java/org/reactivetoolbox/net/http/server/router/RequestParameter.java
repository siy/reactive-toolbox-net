package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.net.http.server.RequestContext;

//TODO: move to Codecs
public interface RequestParameter<T> extends FN1<Result<T>, RequestContext> {
}
