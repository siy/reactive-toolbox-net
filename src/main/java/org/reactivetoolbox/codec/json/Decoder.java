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
        add(Boolean.class, TokenDecoders::bool);
        add(boolean.class, TokenDecoders::bool);
        add(String.class, TokenDecoders::string);
        add(byte.class, TokenDecoders::byteInt);
        add(Byte.class, TokenDecoders::byteInt);
        add(short.class, TokenDecoders::shortInt);
        add(Short.class, TokenDecoders::shortInt);
        add(long.class, TokenDecoders::longInt);
        add(Long.class, TokenDecoders::longInt);
        add(int.class, TokenDecoders::regularInt);
        add(Integer.class, TokenDecoders::regularInt);
        add(float.class, TokenDecoders::floatNumber);
        add(Float.class, TokenDecoders::floatNumber);
        add(double.class, TokenDecoders::doubleNumber);
        add(Double.class, TokenDecoders::doubleNumber);
        add(BigDecimal.class, TokenDecoders::bigDecimal);
        add(LocalDate.class, TokenDecoders::localDate);
        add(LocalDateTime.class, TokenDecoders::localDateTime);
        add(ZonedDateTime.class, TokenDecoders::zonedDateTime);
        add(UUID.class, TokenDecoders::uuid);
        add(KSUID.class, TokenDecoders::ksuid);
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
