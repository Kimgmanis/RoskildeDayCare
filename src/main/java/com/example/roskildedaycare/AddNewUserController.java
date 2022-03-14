package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewUserController implements Initializable {

    @FXML
    private Label titleLbl;
    @FXML
    private Button returnBtn;
    @FXML
    private Button createuserBtn;
    @FXML
    private TextField firstnameTxtFld;
    @FXML
    private TextField lastnameTxtFld;
    @FXML
    private TextField telephoneTxtFld;
    @FXML
    private TextField usernameTxtFld;
    @FXML
    private PasswordField passwordFld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeScene(event, "user-management.fxml", "User management", null, null);
            }
        });
        createuserBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
