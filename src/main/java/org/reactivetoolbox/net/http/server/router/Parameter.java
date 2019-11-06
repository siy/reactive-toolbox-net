package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.net.http.server.ParsingContext;

//TODO: move to Codecs
public interface Parameter<T> extends FN1<Result<T>, ParsingContext> {
}
