package com.example.roskildedaycare;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class UserSystem {
    private static PreparedStatement ps = null;
    private static PreparedStatement psInsert = null;
    private static PreparedStatement psCheckUserExists = null;
    private static ResultSet rs = null;
    private static Connection connect = null;
    private static String url = System.getenv("url");
    private static String user = System.getenv("user");
    private static String password = System.getenv("password");

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String lastName, String firstName) {
        Parent root = null;

            if (firstName != null && lastName != null) {
            try {
                FXMLLoader loader = new FXMLLoader(UserSystem.class.getResource(fxmlFile));
                root = loader.load();
                UserLoggedInController userloggedInController = loader.getController();
                userloggedInController.setUserInformation(lastName, firstName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(UserSystem.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void adminChangeScene(ActionEvent event, String fxmlFile, String title, String lastName, String firstName) {
        Parent root = null;

        if (firstName != null && lastName != null) {
            try {
                FXMLLoader loader = new FXMLLoader(UserSystem.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(lastName, firstName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(UserSystem.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void loginUser(ActionEvent event, String userName, String password) {
        try {
            connection();
            ps = connect.prepareStatement("SELECT password, firstName, lastName, admin FROM roskilde_daycare.user WHERE userName = ?;");
            ps.setString(1, userName);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found");
                alert.show();
            }
            else {
                while (rs.next()) {
                    String retrievedFirstName = rs.getString("firstName");
                    String retrievedLastName = rs.getString("lastName");
                    String retrievedPassword = rs.getString("password");
                    boolean retrievedAdmin = rs.getBoolean("admin");

                    if (retrievedPassword.equals(password) && retrievedAdmin) {
                        adminChangeScene(event, "logged-in.fxml", "Welcome!", retrievedLastName, retrievedFirstName);
                    } else if (retrievedPassword.equals(password)) {
                        changeScene(event, "user-logged-in.fxml", "Welcome!", retrievedLastName, retrievedFirstName);
                    } else {
                        System.out.println("Incorrect password");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private static void connection() {
        try {
            connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psCheckUserExists != null) {
            try {
                psCheckUserExists.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psInsert != null) {
            try {
                psInsert.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
