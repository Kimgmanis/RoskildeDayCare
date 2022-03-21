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

public class AddFamilyController implements Initializable{

    @FXML
    private TextField nameP1Txt;
    @FXML
    private TextField nameP2Txt;
    @FXML
    private TextField nameKidTxt;
    @FXML
    private TextField surnameP1Txt;
    @FXML
    private TextField surnameP2Txt;
    @FXML
    private TextField surnameKidTxt;
    @FXML
    private TextField numberP1Txt;
    @FXML
    private TextField numberP2Txt;
    @FXML
    private ChoiceBox groupChoice;
    @FXML
    private Button returnBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;

    public void initialize(URL url, ResourceBundle resourceBundle){
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "admin-logged-in.fxml", "Log in");

            }
        });
        // just makes all fields empty
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nameP1Txt.setText(null);
                nameP2Txt.setText(null);
                nameKidTxt.setText(null);
                surnameP1Txt.setText(null);
                surnameP2Txt.setText(null);
                surnameKidTxt.setText(null);
                numberP1Txt.setText(null);
                numberP2Txt.setText(null);
            }
        });

        //setting up choice btn
        String[] chosenGroup = new String[1];
        String groupValues[] = { "A", "B", "C" };
        groupChoice.getItems().addAll("A", "B", "C");
        groupChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                chosenGroup[0] = groupValues[new_value.intValue()];
            }
        });

        //creates whole family through functions addKid,addParent,addFamily in UserSystem
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!nameP1Txt.equals(null)&&!surnameP1Txt.equals(null)&&!numberP1Txt.equals(null)&&!nameKidTxt.equals(null)&&!surnameKidTxt.equals(null)&&!chosenGroup[0].equals(null))
                {
                    UserSystem.addFamily(nameP1Txt.getText(),nameP2Txt.getText(),nameKidTxt.getText(),surnameP1Txt.getText(),surnameP2Txt.getText(),surnameKidTxt.getText(),numberP1Txt.getText(),numberP2Txt.getText(),chosenGroup[0]);
                    //TODO delete SOP
                    System.out.println(nameP1Txt.getText()+nameP2Txt.getText()+"<------ addFamilyController nameP1, nameP2");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Family created successfully");
                    alert.show();
                    nameP1Txt.setText(null);
                    nameP2Txt.setText(null);
                    nameKidTxt.setText(null);
                    surnameP1Txt.setText(null);
                    surnameP2Txt.setText(null);
                    surnameKidTxt.setText(null);
                    numberP1Txt.setText(null);
                    numberP2Txt.setText(null);
                    groupChoice.setValue(null);
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Not enough information provided");
                    alert.show();
                    nameP1Txt.setText(null);
                    nameP2Txt.setText(null);
                    nameKidTxt.setText(null);
                    surnameP1Txt.setText(null);
                    surnameP2Txt.setText(null);
                    surnameKidTxt.setText(null);
                    numberP1Txt.setText(null);
                    numberP2Txt.setText(null);
                    groupChoice.setValue(null);
                }
            }
        });


    }

}
