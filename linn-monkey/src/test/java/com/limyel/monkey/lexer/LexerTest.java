package com.limyel.monkey.lexer;

import com.limyel.monkey.token.Token;
import com.limyel.monkey.token.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LexerTest {

    private static final String input = """
            =+(){},;
            """;

    private String[] expectedTokenType = new String[]{TokenType.ASSIGN, TokenType.PLUS, TokenType.LPAREN, TokenType.RPAREN,
            TokenType.LBRACE, TokenType.RBRACE, TokenType.COMMA, TokenType.SEMICOLON};

    private static Lexer lexer;

    @BeforeAll
    public static void init() {
        lexer = new Lexer(input);
    }

    @Test
    public void testNextToken() {
        for (String expected : expectedTokenType) {
            Token token = lexer.nextToken();
            Assertions.assertEquals(token.getType(), expected);
        }
    }

}
