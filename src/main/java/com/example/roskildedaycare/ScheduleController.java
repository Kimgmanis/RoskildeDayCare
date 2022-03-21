package com.example.roskildedaycare;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable{

    @FXML
    private Button returnBtn;
    @FXML
    private Button addBtn;
    @FXML
    private ChoiceBox groupCB;
    @FXML
    private TextField teacherTxt;
    @FXML
    private TextField dateTxt;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "admin-logged-in.fxml", "Log in");

            }
        });

        String[] chosenGroup = new String[1];
        String groupValues[] = { "A", "B", "C" };
        groupCB.getItems().addAll("A", "B", "C");
        groupCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                chosenGroup[0] = groupValues[new_value.intValue()];
            }
        });
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.createSchedule(chosenGroup[0], dateTxt.getText(), teacherTxt.getText());
                //TODO delete SOP
                System.out.println(dateTxt.getText() + teacherTxt.getText() + "<------ createSchedule date, teacher");

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Schedule unit created successfully");
                alert.show();
            }
        });
    }


}
