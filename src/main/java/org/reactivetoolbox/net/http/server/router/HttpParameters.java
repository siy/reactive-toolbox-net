package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.TypeToken;

//TODO: finish it
public interface HttpParameters {
    static <T> Parameter<Option<T>> inPath(final Class<T> type, final String name) {
        return null;
    }

    static <T> Parameter<Option<T>> inPath(final TypeToken<T> type, final String name) {
        return null;
    }

}
