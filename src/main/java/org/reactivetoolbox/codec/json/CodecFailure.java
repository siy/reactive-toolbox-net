package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Failure;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.support.WebFailureTypes;

public interface CodecFailure {
    static <T> Result<T> failure(final String message) {
        return Result.failure(Failure.failure(WebFailureTypes.UNPROCESSABLE_ENTITY, message));
    }

    static <T> Result<T> failure(final String format, final Object ... params) {
        return Result.failure(Failure.failure(WebFailureTypes.UNPROCESSABLE_ENTITY, format, params));
    }
}
