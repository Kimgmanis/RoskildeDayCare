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
    @FXML
    private Button scheduleBtn;
    @FXML
    private Button childrenBtn;
    @FXML
    private Button parentsBtn;
    @FXML
    private Button managementBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserInfo temporary = UserSystem.getUserInfo(UserInfo.usingSystemID);
        welcomeLbl.setText("Welcome Admin: " + temporary.getName() + " " + temporary.getLastName() + "! to the Database");

        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeScene(event, "main.fxml", "Log in", null, null);
            }
        });
        managementBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeScene(event, "user-management.fxml", "User management", null, null);
            }
        });
        parentsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "parents.fxml", "Parents");
            }
        });
    }
}
