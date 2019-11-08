package org.reactivetoolbox.codec;

import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.TypeToken;
import org.reactivetoolbox.net.http.server.ParsingContext;

public interface Parameter<T> extends FN1<Result<T>, ParsingContext> {


    //TODO: implement it
    static <T> Parameter<Option<T>> named(final Class<T> type, final String name) {
        return context -> Result.success(Option.empty());
    }

    static <T> Parameter<Option<T>> named(final TypeToken<T> type, final String name) {
        return context -> Result.success(Option.empty());
    }

    static <T> Parameter<Option<T>> anonymous(final Class<T> type) {
        return context -> Result.success(Option.empty());
    }

    static <T> Parameter<Option<T>> anonymous(final TypeToken<T> type) {
        return context -> Result.success(Option.empty());
    }
}
