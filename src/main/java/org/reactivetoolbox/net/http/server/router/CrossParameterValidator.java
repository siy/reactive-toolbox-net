package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.lang.Functions;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.Tuple;

@FunctionalInterface
public interface CrossParameterValidator<T extends Tuple> extends Functions.FN1<Result<T>, T> {
}
