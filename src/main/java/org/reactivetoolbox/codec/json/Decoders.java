package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.support.KSUID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.reactivetoolbox.codec.json.CodecError.*;
import static org.reactivetoolbox.core.lang.Result.success;

public interface Decoders {
    static Result<String> expect(final Token token, final Token.TokenType type) {
        return token.type() == type
               ? success(token.text())
               : error("Unexpected token {0}", token.text());
    }

    static <T> Result<Option<T>> answer(final T value) {
        return success(Option.option(value));
    }

    static <T> Result<Option<T>> answerEmpty() {
        return answer(null);
    }

    static Result<Option<Boolean>> bool(final Token input) {
        return expect(input, Token.TokenType.LITERAL)
                .flatMap(Decoders::boolDecoder);
    }

    static Result<Option<Boolean>> boolDecoder(String s) {
        return "null".equals(s) ? answerEmpty():
               "true".equals(s) ? answer(true) :
               "false".equals(s) ? answer(false) :
               error("Unknown value for boolean literal {0}", s);
    }

    static Result<Option<String>> string(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<Byte>> byteInt(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<Short>> shortInt(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<Long>> longInt(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<Integer>> regularInt(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<Float>> floatNumber(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<Double>> doubleNumber(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<BigDecimal>> bigDecimal(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<LocalDate>> localDate(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<LocalDateTime>> localDateTime(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<ZonedDateTime>> zonedDateTime(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<UUID>> uuid(final Token input) {
        return error("NOT IMPLEMENTED");
    }

    static Result<Option<KSUID>> ksuid(final Token input) {
        return error("NOT IMPLEMENTED");
    }
}
