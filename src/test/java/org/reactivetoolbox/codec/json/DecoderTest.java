package org.reactivetoolbox.codec.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
}