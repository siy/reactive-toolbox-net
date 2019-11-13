package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Option;

import java.util.Objects;
import java.util.StringJoiner;

import static org.reactivetoolbox.core.lang.Option.option;

public class Token {
    public static final Option<Token> LeftBracket = option(token(TokenType.LB, "["));
    public static final Option<Token> LeftCurlyBracket = option(token(TokenType.LCB, "{"));
    public static final Option<Token> RightBracket = option(token(TokenType.RB, "]"));
    public static final Option<Token> RightCurlyBracket = option(token(TokenType.RCB, "}"));
    public static final Option<Token> Comma = option(token(TokenType.COMMA, ","));
    public static final Option<Token> Semicolon = option(token(TokenType.SEMICOLON, ":"));
    public static final Option<Token> EOF = option(token(TokenType.EOF, ""));

    private final TokenType tokenType;
    private final String text;

    private Token(final TokenType tokenType, final String text) {
        this.tokenType = tokenType;
        this.text = text;
    }

    public static Token token(final TokenType tokenType, final String text) {
        return new Token(tokenType, text);
    }

    public TokenType type() {
        return tokenType;
    }

    public String text() {
        return text;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "Token(", ")")
                .add(tokenType.name())
                .add("'" + text + "'")
                .toString();
    }

    public enum TokenType { LB, RB, LCB, RCB, COMMA, SEMICOLON, EOF, STRING, NUMBER, INTEGER, LITERAL}
}
