package com.example.password_manager;

import com.example.password_manager.pojos.FileAccess;
import com.example.password_manager.pojos.KeyPin;
import com.example.password_manager.pojos.KeyWord;
import com.example.password_manager.pojos.Password;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class DataController {

    public List<Password> load() {
        List<Password> list = new ArrayList<>();
        for (String line : FileAccess.readAllLines()) {
            Password p = parseLine(line);
            if (p != null) list.add(p);
        }
        return list;
    }

    public void save(ObservableList<Password> items) {
        FileAccess.writeAll(items);
    }

    private Password parseLine(String line) {
        String[] parts = line.split(";");
        if (parts.length < 3) return null;

        String type = parts[0];
        String name = parts[1];
        String key = parts[2];
        String extra = parts.length >= 4 ? parts[3] : "";

        switch (type) {
            case "PASSWORD":
                return new Password(name, key);
            case "KEYPIN":
                return new KeyPin(name, key);
            case "KEYWORD":
                return new KeyWord(name, key, Boolean.parseBoolean(extra));
            default:
                return new Password(name, key);
        }
    }
}