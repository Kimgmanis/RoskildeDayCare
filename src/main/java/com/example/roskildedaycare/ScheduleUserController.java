package com.example.roskildedaycare;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleUserController {

    @FXML
    private Button returnBtn;
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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "admin-logged-in.fxml", "Log in");

            }
        });

        //Checks how many parents are in DB
        double scheduleMaxValue = Double.parseDouble(UserSystem.getMaxSchedule());
        //Sets scroll bar to scrollbar maximum amount same as parents amount in the DB
        upDownScroll.setMax(scheduleMaxValue-7);
        upDownScroll.setMin(1);
        upDownScroll.setValue(1);

        // sets values when u open list
        nameTxt1.setText("                  " + UserSystem.getSchedule((int) (upDownScroll.getValue() - 1)).getGroup());
        nameTxt2.setText("                  " + UserSystem.getSchedule((int) (upDownScroll.getValue())).getGroup());
        nameTxt3.setText("                  " + UserSystem.getSchedule((int) (upDownScroll.getValue() + 1)).getGroup());
        nameTxt4.setText("                  " + UserSystem.getSchedule((int) (upDownScroll.getValue() + 2)).getGroup());
        nameTxt5.setText("                  " + UserSystem.getSchedule((int) (upDownScroll.getValue() + 3)).getGroup());
        nameTxt6.setText("                  " + UserSystem.getSchedule((int) (upDownScroll.getValue() + 4)).getGroup());
        nameTxt7.setText("                  " + UserSystem.getSchedule((int) (upDownScroll.getValue() + 5)).getGroup());
        nameTxt8.setText("                  " + UserSystem.getSchedule((int) (upDownScroll.getValue() + 6)).getGroup());

        surnameTxt1.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() - 1)).getTeacher());
        surnameTxt2.setText(UserSystem.getSchedule((int) (upDownScroll.getValue())).getTeacher());
        surnameTxt3.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 1)).getTeacher());
        surnameTxt4.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 2)).getTeacher());
        surnameTxt5.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 3)).getTeacher());
        surnameTxt6.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 4)).getTeacher());
        surnameTxt7.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 5)).getTeacher());
        surnameTxt8.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 6)).getTeacher());

        numberTxt1.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() - 1)).getDate());
        numberTxt2.setText(UserSystem.getSchedule((int) (upDownScroll.getValue())).getDate());
        numberTxt3.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 1)).getDate());
        numberTxt4.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 2)).getDate());
        numberTxt5.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 3)).getDate());
        numberTxt6.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 4)).getDate());
        numberTxt7.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 5)).getDate());
        numberTxt8.setText(UserSystem.getSchedule((int) (upDownScroll.getValue() + 6)).getDate());

        // scrolls down when scroll bar being triggered
        upDownScroll.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                nameTxt1.setText("                  "+UserSystem.getSchedule((int)(upDownScroll.getValue()-1)).getGroup());
                nameTxt2.setText("                  "+UserSystem.getSchedule((int)(upDownScroll.getValue())).getGroup());
                nameTxt3.setText("                  "+UserSystem.getSchedule((int)(upDownScroll.getValue()+1)).getGroup());
                nameTxt4.setText("                  "+UserSystem.getSchedule((int)(upDownScroll.getValue()+2)).getGroup());
                nameTxt5.setText("                  "+UserSystem.getSchedule((int)(upDownScroll.getValue()+3)).getGroup());
                nameTxt6.setText("                  "+UserSystem.getSchedule((int)(upDownScroll.getValue()+4)).getGroup());
                nameTxt7.setText("                  "+UserSystem.getSchedule((int)(upDownScroll.getValue()+5)).getGroup());
                nameTxt8.setText("                  "+UserSystem.getSchedule((int)(upDownScroll.getValue()+6)).getGroup());

                surnameTxt1.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()-1)).getTeacher());
                surnameTxt2.setText(UserSystem.getSchedule((int)(upDownScroll.getValue())).getTeacher());
                surnameTxt3.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+1)).getTeacher());
                surnameTxt4.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+2)).getTeacher());
                surnameTxt5.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+3)).getTeacher());
                surnameTxt6.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+4)).getTeacher());
                surnameTxt7.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+5)).getTeacher());
                surnameTxt8.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+6)).getTeacher());

                numberTxt1.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()-1)).getDate());
                numberTxt2.setText(UserSystem.getSchedule((int)(upDownScroll.getValue())).getDate());
                numberTxt3.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+1)).getDate());
                numberTxt4.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+2)).getDate());
                numberTxt5.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+3)).getDate());
                numberTxt6.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+4)).getDate());
                numberTxt7.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+5)).getDate());
                numberTxt8.setText(UserSystem.getSchedule((int)(upDownScroll.getValue()+6)).getDate());
            }
        });
    }

    private String group;
    private String teacher;
    private String date;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
