package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button connectBtn;
    @FXML
    private TextField usernameTxtFld;
    @FXML
    private PasswordField passwordFld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.loginUser(event, usernameTxtFld.getText(), passwordFld.getText());
                // Clear's fields and sets focus
                usernameTxtFld.setText("");
                passwordFld.setText("");
                usernameTxtFld.requestFocus();
            }
        });

    }
}
