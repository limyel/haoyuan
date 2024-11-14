package com.limyel.monkey.token;

import java.util.HashMap;
import java.util.Map;

public abstract class TokenType {

    // 未知字符
    public static final String ILLEGAL = "ILLEGAL";
    // 文件结尾
    public static final String EOF = "EOF";

    // 标识符和字面量
    public static final String IDENT = "IDENT";
    public static final String INT = "INT";

    // 运算符
    public static final String ASSIGN = "=";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String BANG = "!";
    public static final String ASTERISK = "*";
    public static final String SLASH = "/";

    public static final String LT = "<";
    public static final String GT = ">";

    public static final String EQ = "==";
    public static final String NOT_EQ = "!=";

    // 分隔符
    public static final String COMMA = ",";
    public static final String SEMICOLON = ";";

    public static final String LPAREN = "(";
    public static final String RPAREN = ")";
    public static final String LBRACE = "{";
    public static final String RBRACE = "}";

    // 关键字
    public static final String FUNCTION = "FUNCTION";
    public static final String LET = "LET";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String IF = "IF";
    public static final String ELSE = "ELSE";
    public static final String RETURN = "RETURN";

    public static final Map<String, String> KEYWORDS;

    static {
        KEYWORDS = new HashMap<>();
        KEYWORDS.put("fn", FUNCTION);
        KEYWORDS.put("let", LET);
        KEYWORDS.put("true", TRUE);
        KEYWORDS.put("false", FALSE);
        KEYWORDS.put("if", IF);
        KEYWORDS.put("else", ELSE);
        KEYWORDS.put("return", RETURN);
    }

}
