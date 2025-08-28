package com.example.password_manager;

import com.example.password_manager.pojos.KeyPin;
import com.example.password_manager.pojos.KeyWord;
import com.example.password_manager.pojos.Password;
import com.example.password_manager.pojos.DataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML private ComboBox<String> types;
    @FXML private TextField nameField;
    @FXML private TextField keyField;
    @FXML private CheckBox caseSensitiveBox;
    @FXML private Button createBtn;
    @FXML private ListView<Password> listView;

    private final ObservableList<Password> items = FXCollections.observableArrayList();
    private final DataController dataController = new DataController("passwords.txt");

    @FXML
    public void initialize() {
        types.getItems().addAll("PASSWORD", "KEYPIN", "KEYWORD");
        types.getSelectionModel().selectFirst();
        types.setOnAction(e -> caseSensitiveBox.setVisible("KEYWORD".equals(types.getValue())));
        caseSensitiveBox.setVisible(false);

        listView.setItems(items);
        createBtn.setOnAction(e -> handleCreate());

        items.addAll(dataController.load());
    }

    private void handleCreate() {
        String type = types.getValue();
        String name = nameField.getText().trim();
        String key = keyField.getText().trim();

        if (name.isEmpty()) {
            showAlert("Name fehlt", "Bitte gib einen Namen ein.");
            return;
        }
        if (key.isEmpty()) {
            showAlert("Key fehlt", "Bitte gib einen Key ein.");
            return;
        }

        Password p = null;
        try {
            switch (type) {
                case "KEYPIN":
                    int pin = Integer.parseInt(key);
                    p = new KeyPin(name, pin);
                    break;
                case "KEYWORD":
                    p = new KeyWord(name, key, caseSensitiveBox.isSelected());
                    break;
                default:
                    p = new Password(name, key);
                    break;
            }
        } catch (NumberFormatException ex) {
            showAlert("Ungültiger PIN", "Für KEYPIN muss der Key eine Zahl sein.");
        }

        if (p != null) {
            items.add(p);
            dataController.save(items);
            clearInputs();
        }
    }

    private void clearInputs() {
        nameField.clear();
        keyField.clear();
        caseSensitiveBox.setSelected(false);
    }

    private void showAlert(String title, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }

    public void onClose() {
        dataController.save(items);
    }
}
