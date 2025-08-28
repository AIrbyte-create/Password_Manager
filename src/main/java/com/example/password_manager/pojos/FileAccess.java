package com.example.password_manager.pojos;

import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {

    private static final Path STORAGE_PATH = Path.of(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources",
            "com",
            "example",
            "password_manager",
            "data",
            "passwords.txt"
    );


    public static List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        if (!Files.exists(STORAGE_PATH)) {
            return lines;
        }

        try {
            lines = Files.readAllLines(STORAGE_PATH);
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen: " + e.getMessage());
        }
        return lines;
    }

    public static void writeAll(ObservableList<Password> items) {
        try (BufferedWriter writer = Files.newBufferedWriter(STORAGE_PATH)) {
            for (Password p : items) {
                writer.write(p.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben: " + e.getMessage());
        }
    }
}
