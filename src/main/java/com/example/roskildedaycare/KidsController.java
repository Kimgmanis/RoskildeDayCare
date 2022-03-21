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

public class KidsController implements Initializable {

    @FXML
    private Button returnBtn;
    @FXML
    private Button addBtn;
    @FXML
    private ScrollBar upDownScroll;
    @FXML
    private Label nameTxt1;
    @FXML
    private Label nameTxt2;
    @FXML
    private Label nameTxt3;
    @FXML
    private Label nameTxt4;
    @FXML
    private Label nameTxt5;
    @FXML
    private Label nameTxt6;
    @FXML
    private Label nameTxt7;
    @FXML
    private Label nameTxt8;
    @FXML
    private Label surnameTxt1;
    @FXML
    private Label surnameTxt2;
    @FXML
    private Label surnameTxt3;
    @FXML
    private Label surnameTxt4;
    @FXML
    private Label surnameTxt5;
    @FXML
    private Label surnameTxt6;
    @FXML
    private Label surnameTxt7;
    @FXML
    private Label surnameTxt8;
    @FXML
    private Button kidBtn1;
    @FXML
    private Button kidBtn2;
    @FXML
    private Button kidBtn3;
    @FXML
    private Button kidBtn4;
    @FXML
    private Button kidBtn5;
    @FXML
    private Button kidBtn6;
    @FXML
    private Button kidBtn7;
    @FXML
    private Button kidBtn8;
    @FXML
    private Label numberTxt1;
    @FXML
    private Label numberTxt2;
    @FXML
    private Label numberTxt3;
    @FXML
    private Label numberTxt4;
    @FXML
    private Label numberTxt5;
    @FXML
    private Label numberTxt6;
    @FXML
    private Label numberTxt7;
    @FXML
    private Label numberTxt8;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "admin-logged-in.fxml", "Log in");

            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "addFamily.fxml", "Creating new family");
            }
        });

        //Checks how many kids are in DB
        double parentsMaxID = Double.parseDouble(UserSystem.getKidMaxID());
        //Sets scroll bar to scrollbar maximum amount same as kids amount in the DB
        upDownScroll.setMax(parentsMaxID-7);
        upDownScroll.setMin(1);
        upDownScroll.setValue(1);

        // sets values when u open list
        nameTxt1.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue())).getName());
        nameTxt2.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+1)).getName());
        nameTxt3.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+2)).getName());
        nameTxt4.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+3)).getName());
        nameTxt5.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+4)).getName());
        nameTxt6.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+5)).getName());
        nameTxt7.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+6)).getName());
        nameTxt8.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+7)).getName());

        surnameTxt1.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue())).getSurname());
        surnameTxt2.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+1)).getSurname());
        surnameTxt3.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+2)).getSurname());
        surnameTxt4.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+3)).getSurname());
        surnameTxt5.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+4)).getSurname());
        surnameTxt6.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+5)).getSurname());
        surnameTxt7.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+6)).getSurname());
        surnameTxt8.setText(UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+7)).getSurname());

        numberTxt1.setText("                     "+UserSystem.getKidInfo(Double.toString(upDownScroll.getValue())).getGroup());
        numberTxt2.setText("                     "+UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+1)).getGroup());
        numberTxt3.setText("                     "+UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+2)).getGroup());
        numberTxt4.setText("                     "+UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+3)).getGroup());
        numberTxt5.setText("                     "+UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+4)).getGroup());
        numberTxt6.setText("                     "+UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+5)).getGroup());
        numberTxt7.setText("                     "+UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+6)).getGroup());
        numberTxt8.setText("                     "+UserSystem.getKidInfo(Double.toString(upDownScroll.getValue()+7)).getGroup());

        kidBtn1.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()))).getName());
        kidBtn2.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+1))).getName());
        kidBtn3.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+2))).getName());
        kidBtn4.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+3))).getName());
        kidBtn5.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+4))).getName());
        kidBtn6.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+5))).getName());
        kidBtn7.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+6))).getName());
        kidBtn8.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+7))).getName());

        kidBtn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "admin-logged-in.fxml", "Log in");

            }
        });

        // scrolls down when scroll bar being triggered
        upDownScroll.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                nameTxt1.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue())).getName());
                nameTxt2.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+1)).getName());
                nameTxt3.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+2)).getName());
                nameTxt4.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+3)).getName());
                nameTxt5.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+4)).getName());
                nameTxt6.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+5)).getName());
                nameTxt7.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+6)).getName());
                nameTxt8.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+7)).getName());

                surnameTxt1.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue())).getSurname());
                surnameTxt2.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+1)).getSurname());
                surnameTxt3.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+2)).getSurname());
                surnameTxt4.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+3)).getSurname());
                surnameTxt5.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+4)).getSurname());
                surnameTxt6.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+5)).getSurname());
                surnameTxt7.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+6)).getSurname());
                surnameTxt8.setText(UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+7)).getSurname());

                kidBtn1.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()))).getName());
                kidBtn2.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+1))).getName());
                kidBtn3.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+2))).getName());
                kidBtn4.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+3))).getName());
                kidBtn5.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+4))).getName());
                kidBtn6.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+5))).getName());
                kidBtn7.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+6))).getName());
                kidBtn8.setText(UserSystem.getParentInfo(UserSystem.findParent(UserSystem.doubleToString(upDownScroll.getValue()+7))).getName());

                numberTxt1.setText("                     "+UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue())).getGroup());
                numberTxt2.setText("                     "+UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+1)).getGroup());
                numberTxt3.setText("                     "+UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+2)).getGroup());
                numberTxt4.setText("                     "+UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+3)).getGroup());
                numberTxt5.setText("                     "+UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+4)).getGroup());
                numberTxt6.setText("                     "+UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+5)).getGroup());
                numberTxt7.setText("                     "+UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+6)).getGroup());
                numberTxt8.setText("                     "+UserSystem.getKidInfo(UserSystem.doubleToString(upDownScroll.getValue()+7)).getGroup());

            }
        });


    }

}
