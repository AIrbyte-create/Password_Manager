package com.example.password_manager.pojos;

public class Password {
    protected String name;
    protected String key;

    public Password() {}
    public Password(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() { return name; }
    public String getKey() { return key; }

    public String toFileString() {
        return String.format("PASSWORD;%s;%s", name, key);
    }

    @Override
    public String toString() {
        return String.format("PASSWORD;%s;%s", name, key);
    }
}