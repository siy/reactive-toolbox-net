package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.Tuple;

@FunctionalInterface
public interface CrossParameterValidator<R extends Tuple, T extends Tuple> {
    Result<R> validate(final T input);

}
