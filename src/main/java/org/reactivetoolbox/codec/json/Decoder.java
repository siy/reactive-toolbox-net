package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Collection;
import org.reactivetoolbox.core.lang.Option;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.support.KSUID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.reactivetoolbox.codec.json.Scanner.scanner;

public class Decoder {
    private static final Map<Class, Deserializer> primitiveTypes = new HashMap<>();

    static {
        add(Boolean.class, Decoders::bool);
        add(boolean.class, Decoders::bool);
        add(String.class, Decoders::string);
        add(byte.class, Decoders::byteInt);
        add(Byte.class, Decoders::byteInt);
        add(short.class, Decoders::shortInt);
        add(Short.class, Decoders::shortInt);
        add(long.class, Decoders::longInt);
        add(Long.class, Decoders::longInt);
        add(int.class, Decoders::regularInt);
        add(Integer.class, Decoders::regularInt);
        add(float.class, Decoders::floatNumber);
        add(Float.class, Decoders::floatNumber);
        add(double.class, Decoders::doubleNumber);
        add(Double.class, Decoders::doubleNumber);
        add(BigDecimal.class, Decoders::bigDecimal);
        add(LocalDate.class, Decoders::localDate);
        add(LocalDateTime.class, Decoders::localDateTime);
        add(ZonedDateTime.class, Decoders::zonedDateTime);
        add(UUID.class, Decoders::uuid);
        add(KSUID.class, Decoders::ksuid);
    }

    private static <T> void add(final Class<T> type, final Deserializer<T> decoder) {
        primitiveTypes.put(type, decoder);
    }

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
        // '{'
        //scanner.next()
        return null;
    }

    private <T> Result<Option<T>> readPrimitive(final Deserializer<T> deserializer) {
        return scanner.next()
                      .flatMap(deserializer);
    }

    public <T> Result<T> read(final Class<Collection<T>> collectionType, final Class<T> elementType) {
        return null;
    }
}
