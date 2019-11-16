package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.codec.json.Token.TokenType;
import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.support.KSUID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.reactivetoolbox.codec.json.CodecError.*;
import static org.reactivetoolbox.codec.json.Token.TokenType.*;
import static org.reactivetoolbox.core.lang.Result.success;

public interface Decoders {
    static <T> Result<Option<T>> expect(final Token token, final TokenType type, final FN1<Result<Option<T>>, String> decoder) {
        return token.type() == type ? decoder.apply(token.text()) :
               token.type() == LITERAL ? nullDecoder(token.text()) :
               error("Unexpected token {0}", token.text());
    }

    static <T> Result<Option<T>> answer(final T value) {
        return success(Option.option(value));
    }

    static <T> Result<Option<T>> answerEmpty() {
        return answer(null);
    }

    static <T> Result<Option<T>> nullDecoder(String literal) {
        return "null".equals(literal) ? answerEmpty()
                                      : error("Unrecognized literal {0}", literal);
    }

    static Result<Option<Boolean>> bool(final Token input) {
        return expect(input, LITERAL, Decoders::boolDecoder);
    }

    static Result<Option<Boolean>> boolDecoder(String literal) {
        return "null".equals(literal) ? answerEmpty() :
               "true".equals(literal) ? answer(true) :
               "false".equals(literal) ? answer(false) :
               error("Unknown value for boolean literal {0}", literal);
    }

    static Result<Option<String>> string(final Token input) {
        return expect(input, STRING, Decoders::answer);
    }

    static Result<Option<Byte>> byteInt(final Token input) {
        return expect(input, INTEGER,
                      string -> integerDecoder(string, Byte.MIN_VALUE, Byte.MAX_VALUE, Long::byteValue));
    }

    static Result<Option<Short>> shortInt(final Token input) {
        return expect(input, INTEGER,
                      string -> integerDecoder(string, Short.MIN_VALUE, Short.MAX_VALUE, Long::shortValue));
    }

    static Result<Option<Integer>> regularInt(final Token input) {
        return expect(input, INTEGER,
                      string -> integerDecoder(string, Integer.MIN_VALUE, Integer.MAX_VALUE, Long::intValue));
    }

    static Result<Option<Long>> longInt(final Token input) {
        return expect(input, INTEGER,
                      string -> integerDecoder(string, Long.MIN_VALUE, Long.MAX_VALUE, Long::longValue));
    }

    static <T extends Number> Result<Option<T>> integerDecoder(final String string, final long minValue, final long maxValue, final FN1<T, Long> mapper) {
        return longDecoder(string).flatMap(v -> boundsChecker(v, minValue, maxValue))
                                  .map(o -> o.map(mapper));
    }

    static Result<Option<Long>> boundsChecker(final Option<Long> value, final long minValue, final long maxValue) {
        return value.map($ -> Result.success(value),
                         val -> (val >= minValue && val <= maxValue) ? answer(val)
                                                                     : error("Value is out of bounds of the given type, value={0}, min={1}, max={2}",
                                                                             val, minValue, maxValue));

    }

    static Result<Option<Long>> longDecoder(final String string) {
        try {
            return answer(Long.parseLong(string));
        } catch (final NumberFormatException e) {
            return error("Invalid integer value {0}", string);
        }
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
