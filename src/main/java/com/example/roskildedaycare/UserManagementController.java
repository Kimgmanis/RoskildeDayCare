package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {
    @FXML
    private Label welcomeLbl;
    @FXML
    private Button addnewuserBtn;
    @FXML
    private Button existinguserBtn;
    @FXML
    private Button edituserBtn;
    @FXML
    private Button deleteuserBtn;
    @FXML
    private Button returnBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeScene(event, "admin-logged-in.fxml", "Log in", null, null);
            }
        });
    }
}
