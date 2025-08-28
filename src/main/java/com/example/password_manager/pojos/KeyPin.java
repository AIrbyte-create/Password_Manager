package com.example.password_manager.pojos;

public class KeyPin extends Password {
    public KeyPin(String name, String pin) {
        super(name, pin);
    }
    public KeyPin(String name, int pin) {
        super(name, String.valueOf(pin));
    }

    @Override
    public String toFileString() {
        return String.format("KEYPIN;%s;%s", name, key);
    }

    @Override
    public String toString() {
        return String.format("KEYPIN;%s;%s", name, key);
    }
}