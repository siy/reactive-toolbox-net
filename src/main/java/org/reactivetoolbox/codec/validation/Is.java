package org.reactivetoolbox.codec.validation;

import org.reactivetoolbox.codec.json.CodecError;
import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;

public interface Is {
    static <T> Result<T> notNull(final Option<T> input) {
        return input.map(v -> CodecError.error("Value must not be null"),
                         Result::success);
    }
}
