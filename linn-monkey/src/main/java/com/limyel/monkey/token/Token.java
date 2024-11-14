package com.limyel.monkey.token;

public class Token {

    private String type;

    private String literal;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public void setLiteral(char ch) {
        this.literal = String.valueOf(ch);
    }
}
