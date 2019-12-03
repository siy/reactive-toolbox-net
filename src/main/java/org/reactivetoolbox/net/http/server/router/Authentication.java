package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.Tuple;
import org.reactivetoolbox.core.lang.Tuple.Tuple2;
import org.reactivetoolbox.core.lang.support.KSUID;

public class Authentication {
    public static Result<KSUID> userId(final Authentication authentication) {
    }

    public Result<KSUID> matches(final KSUID userId) {
        return null;
    }
}
