package com.example.password_manager.pojos;

public class KeyWord extends Password {
    private boolean caseSensitive;

    public KeyWord() { super(); }

    public KeyWord(String name, String key, boolean caseSensitive) {
        super(name, key);
        this.caseSensitive = caseSensitive;
    }

    public boolean isCaseSensitive() { return caseSensitive; }
    public void setCaseSensitive(boolean caseSensitive) { this.caseSensitive = caseSensitive; }

    @Override
    public String toFileString() {
        return String.format("KEYWORD|%s|%s|%s", escape(name), escape(key), caseSensitive);
    }

    @Override
    public String toString() {
        return String.format("[KEYWORD] %s — %s (caseSensitive=%s)", name, key, caseSensitive);
    }
}
