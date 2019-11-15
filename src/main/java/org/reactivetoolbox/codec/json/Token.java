package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Result;

import java.util.StringJoiner;

import static org.reactivetoolbox.core.lang.Result.success;

public class Token {
    public static final Result<Token> LeftBracket = success(token(TokenType.LB, "["));
    public static final Result<Token> LeftCurlyBracket = success(token(TokenType.LCB, "{"));
    public static final Result<Token> RightBracket = success(token(TokenType.RB, "]"));
    public static final Result<Token> RightCurlyBracket = success(token(TokenType.RCB, "}"));
    public static final Result<Token> Comma = success(token(TokenType.COMMA, ","));
    public static final Result<Token> Semicolon = success(token(TokenType.SEMICOLON, ":"));
    public static final Result<Token> EOF = success(token(TokenType.EOF, ""));

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
