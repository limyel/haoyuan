package com.limyel.monkey.lexer;

import com.limyel.monkey.token.Token;
import com.limyel.monkey.token.TokenType;

public class Lexer {

    // 输入字符串
    private String input;

    // 输入字符串中的当前位置（指向当前字符）
    private int position;

    // 输入字符串中的当前读取位置（指向当前字符之后的一个字符）
    private int readPosition;

    // 正在查看的字符
    private char ch;

    public Lexer(String input) {
        this.input = input;

        // 读取第一个字符
        readChar();
    }

    public void readChar() {
        if (readPosition >= input.length()) {
            ch = '\0';
        } else {
            ch = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition++;
    }

    public Token nextToken() {
        Token result = new Token();
        result.setLiteral(ch);

        switch (ch) {
            case '=':
                result.setType(TokenType.ASSIGN);
                break;
            case ';':
                result.setType(TokenType.SEMICOLON);
                break;
            case '(':
                result.setType(TokenType.LPAREN);
                break;
            case ')':
                result.setType(TokenType.RPAREN);
                break;
            case ',':
                result.setType(TokenType.COMMA);
                break;
            case '+':
                result.setType(TokenType.PLUS);
                break;
            case '{':
                result.setType(TokenType.LBRACE);
                break;
            case '}':
                result.setType(TokenType.RBRACE);
                break;
            case '\0':
                result.setType(TokenType.EOF);
                result.setLiteral('\0');
                break;
        }

        readChar();
        return result;
    }

}
