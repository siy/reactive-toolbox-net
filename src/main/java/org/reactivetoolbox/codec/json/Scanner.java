package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.codec.json.Token.TokenType;
import org.reactivetoolbox.core.lang.Result;

import static org.reactivetoolbox.codec.json.CodecError.*;
import static org.reactivetoolbox.codec.json.Token.token;
import static org.reactivetoolbox.core.lang.Result.success;

public class Scanner {
    private final CharReader reader;

    private Scanner(final char[] input) {
        reader = new CharReader(input);
    }

    public static Scanner scanner(final String input) {
        return new Scanner(input.toCharArray());
    }

    public Result<Token> next() {
        skipWs();

        switch (reader.underCursor()) {
            case LCB:
                reader.skip();
                return Token.LeftCurlyBracket;
            case RCB:
                reader.skip();
                return Token.RightCurlyBracket;
            case LB:
                reader.skip();
                return Token.LeftBracket;
            case RB:
                reader.skip();
                return Token.RightBracket;
            case SEMICOLON:
                reader.skip();
                return Token.Semicolon;
            case COMMA:
                reader.skip();
                return Token.Comma;
            case QUOTE:
                return nextString();
            case ALPHA:
                return nextLiteral();
            case DIGIT:
                return nextNumber();

            default:
                return Token.EOF;
        }
    }

    private Result<Token> nextNumber() {
        final var text = new StringBuilder(64);
        boolean hasDot = false;

        while (true) {
            text.append(reader.current());
            reader.skip();

            final var current = reader.underCursor();

            if (current == CharType.DOT) {
                if (hasDot) {   //Double dot
                    return error("Invalid number format, double dot");
                }
                hasDot = true;
                continue;
            }

            if (current != CharType.DIGIT) {
                break;
            }
        }

        if (hasDot && text.charAt(text.length() - 1) == '.') {
            return error("Invalid number format, missing digit after dot");
        }

        return success(token(hasDot ? TokenType.NUMBER : TokenType.INTEGER, text.toString()));
    }

    private Result<Token> nextLiteral() {
        final var text = new StringBuilder(32);

        do {
            text.append(reader.current());
            reader.skip();
        } while (reader.underCursor() == CharType.ALPHA);

        return success(token(TokenType.LITERAL, text.toString()));
    }

    private Result<Token> nextString() {
        final var text = new StringBuilder(256);

        //Skip leading quote
        reader.skip();

        while (true) {
            final var current = reader.underCursor();

            if (current == CharType.QUOTE) {
                break;
            }

            text.append(reader.skip());

            if (current == CharType.EOF) {
                return error("Premature EOF");
            }
        }

        //Skip trailing quote
        reader.skip();

        return success(token(TokenType.STRING, text.toString()));
    }

    private void skipWs() {
        while (reader.isWs()) {
            reader.skip();
        }
    }

    private static class CharReader {
        private final char[] input;
        private int pos = 0;

        public CharReader(final char[] input) {
            this.input = input;
        }

        public boolean isWs() {
            return (pos < input.length) && Character.isWhitespace(input[pos]);
        }

        public char skip() {
            final char result = current();

            if (pos < input.length) {
                pos++;
            }

            return result;
        }

        public CharType underCursor() {
            if (pos >= input.length) {
                return CharType.EOF;
            }

            //TODO: potential location for optimization
            final char chr = input[pos];

            switch (chr) {
                case '[':
                    return CharType.LB;
                case ']':
                    return CharType.RB;
                case '{':
                    return CharType.LCB;
                case '}':
                    return CharType.RCB;
                case '"':
                    return CharType.QUOTE;
                case ',':
                    return CharType.COMMA;
                case ':':
                    return CharType.SEMICOLON;
                case '.':
                    return CharType.DOT;
            }

            if (Character.isDigit(chr)) {
                return CharType.DIGIT;
            }

            if (Character.isWhitespace(chr)) {
                return CharType.WS;
            }

            return CharType.ALPHA;
        }

        public char current() {
            if (pos < input.length) {
                return input[pos];
            }
            return 0;
        }
    }
}
