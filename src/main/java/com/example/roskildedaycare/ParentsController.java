package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

    public class ParentsController implements Initializable {

        @FXML
        private Button returnBtn;
        @FXML
        private Button addBtn;
        @FXML
        private ScrollBar upDownScroll;
        @FXML
        private ScrollBar leftRightScroll;
        @FXML
        private TextFlow parentsTxt;


        public void initialize(URL url, ResourceBundle resourceBundle){
            returnBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    UserSystem.changeScene(event, "admin-logged-in.fxml", "Log in", null, null);
                }
            });


        }

        public void showParents()
    }
