package com.example.roskildedaycare;

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

public class editUserController implements Initializable {

    @FXML
    private Label titleLbl;

    // Buttons
    @FXML
    private Button returnBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;

    // Table
    @FXML
    private TableView<UserInfo> table;
    @FXML
    private TableColumn<UserInfo, Integer> id;
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
        showUsers();
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserSystem.changeSceneNew(event, "user-management.fxml", "User Management");
            }
        });
        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserInfo u = table.getSelectionModel().getSelectedItem();
                UserSystem.updateUserScene(event, "Update user", u.getID());
            }
        });
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserInfo u = table.getSelectionModel().getSelectedItem();
                table.getItems().remove(u);
                UserSystem.deleteUser(u.getID());
            }
        });
    }
    public void showUsers() {
        id.setCellValueFactory(new PropertyValueFactory<UserInfo, Integer>("ID"));
        firstName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("lastName"));
        telephone.setCellValueFactory(new PropertyValueFactory<UserInfo, Integer>("telephoneNumber"));
        userName.setCellValueFactory(new PropertyValueFactory<UserInfo, String>("userName"));
        admin.setCellValueFactory(new PropertyValueFactory<UserInfo, Boolean>("admin"));

        table.setItems(UserSystem.getUserListID());
    }
}
