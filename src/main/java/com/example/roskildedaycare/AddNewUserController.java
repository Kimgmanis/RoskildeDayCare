package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewUserController implements Initializable {

    @FXML
    private Label titleLbl;
    @FXML
    private Button returnBtn;
    @FXML
    private Button createuserBtn;
    @FXML
    private TextField firstnameTxtFld;
    @FXML
    private TextField lastnameTxtFld;
    @FXML
    private TextField telephoneTxtFld;
    @FXML
    private TextField usernameTxtFld;
    @FXML
    private PasswordField passwordFld;
    @FXML
    private CheckBox admincheckbox;
    private boolean update = false;
    private int localID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "user-management.fxml", "User management");
            }
        });
        createuserBtn.setText("Create");
        createuserBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(update == false){
                    UserSystem.addNewUser(firstnameTxtFld.getText(), lastnameTxtFld.getText(), telephoneTxtFld.getText(), usernameTxtFld.getText(), passwordFld.getText(), admincheckbox.isSelected());
                } else if(update == true) {
                    UserSystem.updateUser(localID , firstnameTxtFld.getText(), lastnameTxtFld.getText(), telephoneTxtFld.getText(), usernameTxtFld.getText(), passwordFld.getText(), admincheckbox.isSelected());
                    update = false;
                }
            }
        });
    }

    public void updateUser(int ID) {
        firstnameTxtFld.setText(UserSystem.getUserInfo(Integer.toString(ID)).getFirstName());
        lastnameTxtFld.setText(UserSystem.getUserInfo(Integer.toString(ID)).getLastName());
        telephoneTxtFld.setText(UserSystem.getUserInfo(Integer.toString(ID)).getTelephoneNumber());
        usernameTxtFld.setText(UserSystem.getUserInfo(Integer.toString(ID)).getUserName());
        passwordFld.setText(UserSystem.getUserInfo(Integer.toString(ID)).getPassword());
        admincheckbox.setSelected(UserSystem.getUserInfo(Integer.toString(ID)).isAdmin());
        update = true;
        localID = ID;
        createuserBtn.setText("Update");
    }
}
