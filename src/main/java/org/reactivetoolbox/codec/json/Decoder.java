package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Collection;
import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.support.KSUID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

import static org.reactivetoolbox.codec.json.Scanner.scanner;

public class Decoder {
    private static Map<Class, Deserializer> primitiveTypes = Map.of(Boolean.class, Decoders::bool,
                                                                          String.class, Decoders::string,
                                                                          byte.class, Decoders::byteInt,
                                                                          Byte.class, Decoders::byteInt,
                                                                          short.class, Decoders::shortInt,
                                                                          Short.class, Decoders::shortInt,
                                                                          long.class, Decoders::longInt,
                                                                          Long.class, Decoders::longInt,
                                                                          int.class, Decoders::regularInt,
                                                                          Integer.class, Decoders::regularInt,
                                                                          float.class, Decoders::floatNumber,
                                                                          Float.class, Decoders::floatNumber,
                                                                          double.class, Decoders::doubleNumber,
                                                                          Double.class, Decoders::doubleNumber,
                                                                          BigDecimal.class, Decoders::bigDecimal,
                                                                          LocalDate.class, Decoders::localDate,
                                                                          LocalDateTime.class, Decoders::localDateTime,
                                                                          ZonedDateTime.class, Decoders::zonedDateTime,
                                                                          //non-standard types (represented as strings in JSON)
                                                                          UUID.class, Decoders::uuid,
                                                                          KSUID.class, Decoders::ksuid);

    private final Scanner scanner;

    private Decoder(final Scanner scanner) {
        this.scanner = scanner;
    }

    public static Decoder decoder(final String source) {
        return new Decoder(scanner(source));
    }

    public <T> Result<Option<T>> read(final Class<T> type) {
        return primitiveDeserializer(type).map(this::readPrimitive)
                                          .otherwise(() -> readObject(type));
    }

    @SuppressWarnings("unchecked")
    private <T> Option<Deserializer<T>> primitiveDeserializer(final Class<T> type) {
        return Option.option((Deserializer<T>) primitiveTypes.get(type));
    }

    private <T> Result<Option<T>> readObject(final Class<T> type) {
        return null;
    }

    private <T> Result<Option<T>> readPrimitive(final Deserializer<T> deserializer) {
        scanner.next()
               .map(deserializer);
    }

    public <T> Result<T> read(final Class<Collection<T>> collectionType, final Class<T> elementType) {
    }
}
