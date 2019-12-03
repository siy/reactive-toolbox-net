package org.reactivetoolbox.codec.validation;

import org.reactivetoolbox.codec.json.CodecError;
import org.reactivetoolbox.core.lang.Failure;
import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.support.WebFailureTypes;
import org.reactivetoolbox.net.http.server.router.Authentication;

public interface AuthIs {
    static Result<Authentication> loggedIn(final Option<Authentication> input) {
        return input.map(v -> Result.failure(Failure.failure(WebFailureTypes.FORBIDDEN, "User is not logged in")),
                         Result::success);
    }
}
