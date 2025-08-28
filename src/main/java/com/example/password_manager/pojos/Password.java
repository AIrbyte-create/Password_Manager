package com.example.password_manager.pojos;

public class Password {
    protected String name;
    protected String key;

    public Password() {

    }

    public Password(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String toFileString() {
        return String.format("PASSWORD|%s|%s|", escape(name), escape(key));
    }

    protected String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("|", "\\|");
    }

    protected static String unescape(String s) {
        if (s == null) return "";
        StringBuilder out = new StringBuilder();
        boolean slash = false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (slash) {
                out.append(c);
                slash = false;
            } else if (c == '\\') {
                slash = true;
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    @Override
    public String toString() {
        return String.format("[PASSWORD] %s — %s", name, key);
    }

    public static String doUnescape(String s) {
        return unescape(s);
    }
}
