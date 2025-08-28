package com.example.password_manager.pojos;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataController {

    private final File storageFile;

    public DataController(String fileName) {
        this.storageFile = new File(fileName);
    }

    public List<Password> load() {
        List<Password> list = new ArrayList<>();
        if (!storageFile.exists()) return list;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(storageFile));
            String line;
            while ((line = br.readLine()) != null) {
                Password p = parseLine(line);
                if (p != null) list.add(p);
            }
        } catch (IOException e) {

        } finally {
            try { if (br != null) br.close(); } catch (IOException ignored) {}
        }
        return list;
    }

    public void save(ObservableList<Password> items) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(storageFile));
            for (Password p : items) {
                bw.write(p.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {

        } finally {
            try { if (bw != null) bw.close(); } catch (IOException ignored) {}
        }
    }

    private Password parseLine(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean esc = false;
        for (int i = 0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if (esc) { cur.append(c); esc = false; }
            else if (c == '\\') esc = true;
            else if (c == '|') { parts.add(cur.toString()); cur.setLength(0); }
            else cur.append(c);
        }
        parts.add(cur.toString());
        if (parts.size() < 3) return null;

        String type = parts.get(0);
        String name = Password.doUnescape(parts.get(1));
        String key = Password.doUnescape(parts.get(2));
        String extra = parts.size() >= 4 ? parts.get(3) : "";

        switch (type) {
            case "PASSWORD":
                return new Password(name, key);
            case "KEYPIN":
                return new KeyPin(name, key);
            case "KEYWORD":
                return new KeyWord(name, key, "true".equalsIgnoreCase(extra));
            default:
                return new Password(name, key);
        }
    }
}
