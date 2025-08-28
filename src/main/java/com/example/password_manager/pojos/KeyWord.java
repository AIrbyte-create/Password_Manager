package com.example.password_manager.pojos;

public class KeyWord extends Password {
    private boolean caseSensitive;

    public KeyWord(String name, String key, boolean caseSensitive) {
        super(name, key);
        this.caseSensitive = caseSensitive;
    }

    @Override
    public String toFileString() {
        return String.format("KEYWORD;%s;%s;%s", name, key, caseSensitive);
    }

    @Override
    public String toString() {
        return String.format("KEYWORD;%s;%s;%s", name, key, caseSensitive);
    }
}