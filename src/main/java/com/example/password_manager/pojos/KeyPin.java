package com.example.password_manager.pojos;

public class KeyPin extends Password {

    public KeyPin() {
        super();
    }

    public KeyPin(String name, int pin) {
        super(name, String.valueOf(pin));
    }

    public KeyPin(String name, String pinAsString) {
        super(name, pinAsString);
    }

    @Override
    public String toFileString() {
        return String.format("KEYPIN|%s|%s|", escape(name), escape(key));
    }

    @Override
    public String toString() {
        return String.format("[KEYPIN] %s — %s", name, key);
    }
}
