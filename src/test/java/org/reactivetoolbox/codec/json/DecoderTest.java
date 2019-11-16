package org.reactivetoolbox.codec.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.reactivetoolbox.codec.json.CodecError.error;
import static org.reactivetoolbox.codec.json.Decoder.decoder;
import static org.reactivetoolbox.core.lang.Option.empty;
import static org.reactivetoolbox.core.lang.Option.option;

class DecoderTest {
    @Test
    void canReadBooleanPrimitives() {
        decoder("true").read(boolean.class)
                       .onSuccess(v -> assertEquals(option(true), v))
                       .onFailure(f -> fail());
        decoder("false").read(boolean.class)
                        .onSuccess(v -> assertEquals(option(false), v))
                        .onFailure(f -> fail());
        decoder("null").read(boolean.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());

        decoder("true").read(Boolean.class)
                       .onSuccess(v -> assertEquals(option(true), v))
                       .onFailure(f -> fail());
        decoder("false").read(Boolean.class)
                        .onSuccess(v -> assertEquals(option(false), v))
                        .onFailure(f -> fail());
        decoder("null").read(Boolean.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());
    }

    @Test
    void canReadStringPrimitives() {
        decoder("\"one\"").read(String.class)
                          .onSuccess(v -> assertEquals(option("one"), v))
                          .onFailure(f -> fail());
        decoder("\"\"").read(String.class)
                       .onSuccess(v -> assertEquals(option(""), v))
                       .onFailure(f -> fail());
        decoder("null").read(String.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());
    }

    @Test
    void canReadBytePrimitives() {
        decoder("3").read(byte.class)
                    .onSuccess(v -> assertEquals(option((byte) 3), v))
                    .onFailure(f -> fail());
        decoder("null").read(byte.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());

        decoder("3").read(Byte.class)
                    .onSuccess(v -> assertEquals(option((byte) 3), v))
                    .onFailure(f -> fail());
        decoder("null").read(Byte.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());

        decoder("128").read(byte.class)
                      .onSuccess(v -> fail())
                      .onFailure(f -> assertEquals("Value is out of bounds", f.message().substring(0, 22)));
        decoder("-129").read(byte.class)
                      .onSuccess(v -> fail())
                      .onFailure(f -> assertEquals("Value is out of bounds", f.message().substring(0, 22)));
    }

    @Test
    void canReadShortPrimitives() {
        decoder("456").read(short.class)
                      .onSuccess(v -> assertEquals(option((short) 456), v))
                      .onFailure(f -> fail());
        decoder("null").read(short.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());

        decoder("789").read(Short.class)
                      .onSuccess(v -> assertEquals(option((short) 789), v))
                      .onFailure(f -> fail());
        decoder("null").read(Short.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());

        decoder("32768").read(Short.class)
                      .onSuccess(v -> fail())
                      .onFailure(f -> assertEquals("Value is out of bounds", f.message().substring(0, 22)));
        decoder("-32769").read(Short.class)
                       .onSuccess(v -> fail())
                       .onFailure(f -> assertEquals("Value is out of bounds", f.message().substring(0, 22)));
    }

    @Test
    void canReadIntPrimitives() {
        decoder("4563456").read(int.class)
                          .onSuccess(v -> assertEquals(option(4563456), v))
                          .onFailure(f -> fail());
        decoder("null").read(int.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());

        decoder("7892343").read(Integer.class)
                          .onSuccess(v -> assertEquals(option(7892343), v))
                          .onFailure(f -> fail());
        decoder("null").read(Integer.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());

        decoder("2147483648").read(Integer.class)
                        .onSuccess(v -> fail())
                        .onFailure(f -> assertEquals("Value is out of bounds", f.message().substring(0, 22)));
        decoder("-2147483649").read(Integer.class)
                         .onSuccess(v -> fail())
                         .onFailure(f -> assertEquals("Value is out of bounds", f.message().substring(0, 22)));
    }

    @Test
    void canReadLongPrimitives() {
        decoder("1234563456").read(long.class)
                             .onSuccess(v -> assertEquals(option(1234563456L), v))
                             .onFailure(f -> fail());
        decoder("null").read(long.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());

        decoder("1237892343").read(Long.class)
                             .onSuccess(v -> assertEquals(option(1237892343L), v))
                             .onFailure(f -> fail());
        decoder("null").read(Long.class)
                       .onSuccess(v -> assertEquals(empty(), v))
                       .onFailure(f -> fail());
    }
}