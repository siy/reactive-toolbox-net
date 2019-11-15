package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;

public interface Deserializer<T> extends FN1<Result<Option<T>>, Token> {
}
