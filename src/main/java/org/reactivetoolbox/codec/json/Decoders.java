package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.support.KSUID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public interface Decoders {
    static Result<String> expect(final Token token, final Token.TokenType type) {
        return token.type() == type
               ? Result.success(token.text())
               : CodecFailure.failure("Unexpected token {0}", token.text());
    }

    static Result<Option<Boolean>> bool(final Token input) {
        return expect(input, Token.TokenType.LITERAL).flatMap(Decoders::boolDecoder);
    }

    static <R> Result<R> boolDecoder(String s) {
        //TODO: finish it
        return null;
    }

    static Result<Option<String>> string(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<Byte>> byteInt(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<Short>> shortInt(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<Long>> longInt(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<Integer>> regularInt(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<Float>> floatNumber(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<Double>> doubleNumber(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<BigDecimal>> bigDecimal(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<LocalDate>> localDate(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<LocalDateTime>> localDateTime(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<ZonedDateTime>> zonedDateTime(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<UUID>> uuid(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

    static Result<Option<KSUID>> ksuid(final Token input) {
        return CodecFailure.failure("NOT IMPLEMENTED");
    }

}
