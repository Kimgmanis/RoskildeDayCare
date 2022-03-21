package com.example.roskildedaycare;

import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class viewUserController implements Initializable {

    @FXML
    private Label titleLbl;
    @FXML
    private Button returnBtn;
    @FXML
    private TableView<UserInfo> table;
    @FXML
    private TableColumn<UserInfo, String> firstName;
    @FXML
    private TableColumn<UserInfo, String> lastName;
    @FXML
    private TableColumn<UserInfo, Integer> telephone;
    @FXML
    private TableColumn<UserInfo, String> userName;
    @FXML
    private TableColumn<UserInfo, Boolean> admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "user-management.fxml", "User Management");
            }
        });
    }
    public void showUsers() {
        TableView table = new TableView();

        firstName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("lastName"));
        telephone.setCellValueFactory(new PropertyValueFactory<UserInfo, Integer>("telephoneNumber"));
        userName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("userName"));
        admin.setCellValueFactory(new PropertyValueFactory<UserInfo, Boolean>("admin"));

        table.setItems(UserSystem.getUserList());
    }
}
