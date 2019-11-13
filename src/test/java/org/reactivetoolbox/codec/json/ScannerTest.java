package org.reactivetoolbox.codec.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.reactivetoolbox.codec.json.Token.*;

class ScannerTest {
    @Test
    void testParsingEmpty() {
        final var scanner = Scanner.scanner("");

        checkEof(scanner);
    }

    @Test
    void testParsingLiteral0() {
        final var scanner = Scanner.scanner("null");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.LITERAL, token.type()))
               .whenPresent(token -> assertEquals("null", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingLiteral1() {
        final var scanner = Scanner.scanner("true");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.LITERAL, token.type()))
               .whenPresent(token -> assertEquals("true", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingLiteral2() {
        final var scanner = Scanner.scanner("false");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.LITERAL, token.type()))
               .whenPresent(token -> assertEquals("false", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingInteger0() {
        final var scanner = Scanner.scanner("9");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.INTEGER, token.type()))
               .whenPresent(token -> assertEquals("9", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingInteger1() {
        final var scanner = Scanner.scanner("1324234242345");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.INTEGER, token.type()))
               .whenPresent(token -> assertEquals("1324234242345", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingNumber0() {
        final var scanner = Scanner.scanner("9.0");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.NUMBER, token.type()))
               .whenPresent(token -> assertEquals("9.0", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingNumber1() {
        final var scanner = Scanner.scanner("   345345345435.345345345345345  \n   ");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.NUMBER, token.type()))
               .whenPresent(token -> assertEquals("345345345435.345345345345345", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingNumber2() {
        final var scanner = Scanner.scanner("9.   ");

        scanner.next()
               .whenPresent(token -> fail());
        checkEof(scanner);
    }

    @Test
    void testParsingString0() {
        final var scanner = Scanner.scanner("   \"\"   ");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.STRING, token.type()))
               .whenPresent(token -> assertEquals("", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingString1() {
        final var scanner = Scanner.scanner("\"  123  \"");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.STRING, token.type()))
               .whenPresent(token -> assertEquals("  123  ", token.text()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingString2() {
        final var scanner = Scanner.scanner("\"  123  ");

        scanner.next()
               .whenPresent(token -> fail());
        checkEof(scanner);
    }

    @Test
    void testParsingObject0() {
        final var scanner = Scanner.scanner("{}");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.LCB, token.type()))
               .whenEmpty(Assertions::fail);
        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.RCB, token.type()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    @Test
    void testParsingObject1() {
        final var scanner = Scanner.scanner("  { \"one\" :     \n false }   ");

        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.LCB, token.type()))
               .whenEmpty(Assertions::fail);
        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.STRING, token.type()))
               .whenPresent(token -> assertEquals("one", token.text()))
               .whenEmpty(Assertions::fail);
        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.SEMICOLON, token.type()))
               .whenEmpty(Assertions::fail);
        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.LITERAL, token.type()))
               .whenPresent(token -> assertEquals("false", token.text()))
               .whenEmpty(Assertions::fail);
        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.RCB, token.type()))
               .whenEmpty(Assertions::fail);
        checkEof(scanner);
    }

    private void checkEof(final Scanner scanner) {
        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.EOF, token.type()))
               .whenEmpty(Assertions::fail);
        //EOF is always returned since then
        scanner.next()
               .whenPresent(token -> assertEquals(TokenType.EOF, token.type()))
               .whenEmpty(Assertions::fail);
    }
}