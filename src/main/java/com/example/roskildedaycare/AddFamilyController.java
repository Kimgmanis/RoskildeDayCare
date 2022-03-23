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

    private KidInfo localKid = new KidInfo();
    private ParentInfo localParent1 = new ParentInfo();
    private ParentInfo localParent2 = new ParentInfo();
    private boolean update = false;

    public void initialize(URL url, ResourceBundle resourceBundle){
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                update = false;
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
                if(update == false) {
                    if (!nameP1Txt.equals(null) && !surnameP1Txt.equals(null) && !numberP1Txt.equals(null) && !nameKidTxt.equals(null) && !surnameKidTxt.equals(null) && !chosenGroup[0].equals(null)) {
                        UserSystem.addFamily(nameP1Txt.getText(), nameP2Txt.getText(), nameKidTxt.getText(), surnameP1Txt.getText(), surnameP2Txt.getText(), surnameKidTxt.getText(), numberP1Txt.getText(), numberP2Txt.getText(), chosenGroup[0]);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Information saved successfully");
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
                    } else {
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
                }else if(update == true)
                {
                    UserSystem.updateKid(localKid.getID(),nameKidTxt.getText(),surnameKidTxt.getText(),chosenGroup[0]);
                    UserSystem.updateParent(localParent1.getID(),nameP1Txt.getText(),surnameP1Txt.getText(),numberP1Txt.getText(),localParent1.getKidsID());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Information saved successfully");
                    alert.show();
                    nameKidTxt.setText(UserSystem.getKidInfo(localKid.getID()).getName());
                    surnameKidTxt.setText(UserSystem.getKidInfo(localKid.getID()).getSurname());
                    groupChoice.setValue(UserSystem.getKidInfo(localKid.getID()).getGroup());

                    nameP1Txt.setText(UserSystem.getParentInfo(localParent1.getID()).getName());
                    surnameP1Txt.setText(UserSystem.getParentInfo(localParent1.getID()).getSurname());
                    numberP1Txt.setText(UserSystem.getParentInfo(localParent1.getID()).getNumber());
                    if (!nameP2Txt.getText().isEmpty() && !surnameP2Txt.getText().isEmpty() && !numberP2Txt.getText().isEmpty())
                    {
                        UserSystem.updateParent(localParent2.getID(),nameP2Txt.getText(),surnameP2Txt.getText(),numberP2Txt.getText(),localParent2.getKidsID());

                        nameP2Txt.setText(UserSystem.getParentInfo(localParent2.getID()).getName());
                        surnameP2Txt.setText(UserSystem.getParentInfo(localParent2.getID()).getSurname());
                        numberP2Txt.setText(UserSystem.getParentInfo(localParent2.getID()).getNumber());
                    }
                }
            }
        });
    }

    public void updateFamily(String kidID)
    {
            String p1ID = UserSystem.findParent(kidID);
            nameKidTxt.setText(UserSystem.getKidInfo(kidID).getName());
            surnameKidTxt.setText(UserSystem.getKidInfo(kidID).getSurname());
            groupChoice.setValue(UserSystem.getKidInfo(kidID).getGroup());

            localKid.setID(kidID);
            localKid.setName(UserSystem.getKidInfo(kidID).getName());
            localKid.setSurname(UserSystem.getKidInfo(kidID).getSurname());
            localKid.setGroup(UserSystem.getKidInfo(kidID).getGroup());

            nameP1Txt.setText(UserSystem.getParentInfo(p1ID).getName());
            surnameP1Txt.setText(UserSystem.getParentInfo(p1ID).getSurname());
            numberP1Txt.setText(UserSystem.getParentInfo(p1ID).getNumber());

            localParent1.setID(p1ID);
            localParent1.setName(UserSystem.getParentInfo(p1ID).getName());
            localParent1.setSurname(UserSystem.getParentInfo(p1ID).getSurname());
            localParent1.setNumber(UserSystem.getParentInfo(p1ID).getNumber());
            localParent1.setKidsID(kidID);


            String p2ID = UserSystem.findSecondParent(kidID);
            if(p2ID!=null)
            {
                nameP2Txt.setText(UserSystem.getParentInfo(p2ID).getName());
                surnameP2Txt.setText(UserSystem.getParentInfo(p2ID).getSurname());
                numberP2Txt.setText(UserSystem.getParentInfo(p2ID).getNumber());

                localParent2.setID(p2ID);
                localParent2.setName(UserSystem.getParentInfo(p1ID).getName());
                localParent2.setSurname(UserSystem.getParentInfo(p1ID).getSurname());
                localParent2.setNumber(UserSystem.getParentInfo(p1ID).getNumber());
                localParent2.setKidsID(kidID);
            }
            update = true;
    }

}
