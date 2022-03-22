package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserLoggedInController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "main.fxml", "Log in");
            }
        });


        parentsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "parentsUser.fxml", "Parents");
            }
        });
        childrenBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "kidsUser.fxml", "Students");
            }
        });
        scheduleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "scheduleUser.fxml", "Schedule");
            }
        });
    }

    public void setUserInformation(String lastName, String firstName) {
        welcomeLbl.setText("Welcome User: " + lastName + " " + firstName + "! to the Database");
    }
}
