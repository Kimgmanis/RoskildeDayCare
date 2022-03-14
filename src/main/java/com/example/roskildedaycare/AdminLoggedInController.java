package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminLoggedInController implements Initializable {

    @FXML
    private Label welcomeLbl;
    @FXML
    private Button logoutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeScene(event, "main.fxml", "Log in", null, null);
            }
        });
    }

    public void setUserInformation(String lastName, String firstName) {
        welcomeLbl.setText("Welcome Admin: " + lastName + " " + firstName + "! to the Database");
    }
}
