package com.example.roskildedaycare;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class UserSystem {
    private static PreparedStatement ps = null;
    private static PreparedStatement psInsert = null;
    private static PreparedStatement psCheckUserExists = null;
    private static ResultSet rs = null;
    private static Connection connect = null;
    private static String url = System.getenv("url");
    private static String user = System.getenv("user");
    private static String password = System.getenv("password");

    public static void changeSceneNew(ActionEvent event, String fxmlFile, String title) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(UserSystem.class.getResource(fxmlFile));
            root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't change scene");
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    public static void updateUserScene(ActionEvent event, String title, int userID) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(UserSystem.class.getResource("add-new-user.fxml"));
            root = loader.load();
            AddNewUserController e = loader.getController();
            e.updateUser(userID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void updateFamilyScene(ActionEvent event, String fxmlFile, String title, String kidID) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(UserSystem.class.getResource("addFamily.fxml"));
            root = loader.load();
            AddFamilyController updateController = loader.getController();
            updateController.updateFamily(kidID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void deleteSchedule(String date, String group) {
        connection();
        try {
            ps = connect.prepareStatement("DELETE FROM roskilde_daycare.schedule WHERE dateSchedule = ? AND studentGroup = ?");
            ps.setString(2, group);
            ps.setString(1, date);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("deleteSchedule");
        } finally {
            closeConnection();
        }
    }

    public static void createSchedule(String group, String date, String teacher) {
        connection();
        try {
            ps = connect.prepareStatement("REPLACE INTO roskilde_daycare.schedule (studentGroup, dateSchedule, teacher) VALUES (?,?,?)");
            ps.setString(1, group);
            ps.setString(2, date);
            ps.setString(3, teacher);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("createSchedule");
        } finally {
            closeConnection();
        }
    }

    public static String getMaxSchedule() {
        String maxValue = new String();
        connection();
        try {
            ps = connect.prepareStatement("SELECT COUNT(*) FROM roskilde_daycare.schedule");
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No datesfound");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.getMaxSchedule");
                alert.show();
            } else {
                while (rs.next()) {
                    maxValue = rs.getString("COUNT(*)");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return maxValue;
    }

    public static ScheduleController getSchedule(int rowNum) {
        ScheduleController temporary = new ScheduleController();
        connection();
        try {
            ps = connect.prepareStatement("SELECT studentGroup, dateSchedule, teacher FROM roskilde_daycare.schedule LIMIT ?,1");
            ps.setInt(1, rowNum);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.getKidInfo");
                alert.show();
            } else {
                while (rs.next()) {
                    temporary.setDate(rs.getString("dateSchedule"));
                    temporary.setTeacher(rs.getString("teacher"));
                    temporary.setGroup(rs.getString("studentGroup"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return temporary;
    }

    public static String findParent(String kidID) {
        String parentID = null;
        connection();
        try {
            ps = connect.prepareStatement("SELECT ID FROM roskilde_daycare.parents WHERE students_id = ?");
            ps.setString(1, kidID);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.findParent");
                alert.show();
            } else {
                while (rs.next()) {
                    parentID = rs.getString("ID");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return parentID;
    }

    public static String findSecondParent(String kidID) {
        String parentID = null;
        connection();
        try {
            ps = connect.prepareStatement("SELECT ID FROM roskilde_daycare.parents WHERE students_id = ? LIMIT 1,1");
            ps.setString(1, kidID);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                ps = connect.prepareStatement("SELECT ID FROM roskilde_daycare.parents WHERE students_id = ? LIMIT 0,1");
                ps.setString(1, kidID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    parentID = rs.getString("ID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return parentID;
    }

    public static void addFamily(String nameP1, String nameP2, String nameKid, String surnameP1, String surnameP2, String surnameKid, String numberP1, String numberP2, String group) {
        String kidID = addKid(nameKid, surnameKid, group);
        addPrent(nameP1, surnameP1, numberP1, kidID);
        if (!nameP2.equals(null) && !surnameP2.equals(null) && !numberP2.equals(null) && !nameP2.equals("") && !surnameP2.equals("") && !numberP2.equals("")) {
            addPrent(nameP2, surnameP2, numberP2, kidID);
        }
    }

    public static void addPrent(String name, String surname, String number, String kidID) {
        connection();
        try {
            ps = connect.prepareStatement("INSERT INTO roskilde_daycare.parents (firstName, lastName, telephoneNumber, students_id) VALUES (?,?,?,?)");
            int numberInt = Integer.parseInt(number);
            int kidIDInt = Integer.parseInt(kidID);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setInt(3, numberInt);
            ps.setInt(4, kidIDInt);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public static String addKid(String name, String surname, String group) {
        String kidID = null;
        connection();
        try {
            ps = connect.prepareStatement("INSERT INTO roskilde_daycare.students (firstName, lastName, studentGroup) VALUES (?,?,?)");
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, group);
            ps.executeUpdate();
            ps = connect.prepareStatement("SELECT ID FROM roskilde_daycare.students ORDER BY ID DESC LIMIT 1;");
            rs = ps.executeQuery();
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.getParentInfo");
                alert.show();
            } else {
                kidID = rs.getString("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got an exception addKid");
        } finally {
            closeConnection();
        }
        return kidID;
    }

    public static String getKidMaxID() {
        String maxID = new String();
        connection();
        try {
            ps = connect.prepareStatement("SELECT COUNT(*) FROM roskilde_daycare.students");
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.getKidMaxID");
                alert.show();
            } else {
                while (rs.next()) {
                    maxID = rs.getString("COUNT(*)");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return maxID;
    }

    public static KidInfo getKidInfo(String kidID) {
        KidInfo temporaryKid = new KidInfo();
        connection();
        try {
            ps = connect.prepareStatement("SELECT firstName, lastName, studentGroup FROM roskilde_daycare.students WHERE ID = ?");
            ps.setString(1, kidID);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.getKidInfo");
                alert.show();
            } else {
                while (rs.next()) {
                    temporaryKid.setName(rs.getString("firstName"));
                    temporaryKid.setSurname(rs.getString("lastName"));
                    temporaryKid.setGroup(rs.getString("studentGroup"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return temporaryKid;
    }

    public static String getParentsMaxID() {
        String maxID = new String();
        connection();
        try {
            ps = connect.prepareStatement("SELECT COUNT(*) FROM roskilde_daycare.parents");
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.getParentsMaxID");
                alert.show();
            } else {
                while (rs.next()) {
                    maxID = rs.getString("COUNT(*)");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return maxID;
    }

    public static ParentInfo getParentInfo(String parentID) {
        ParentInfo temporaryParent = new ParentInfo();
        connection();
        try {
            ps = connect.prepareStatement("SELECT firstName, lastName, telephoneNumber, students_id FROM roskilde_daycare.parents WHERE ID = ?");
            ps.setString(1, parentID);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.getParentInfo");
                alert.show();
            } else {
                while (rs.next()) {
                    temporaryParent.setName(rs.getString("firstName"));
                    temporaryParent.setSurname(rs.getString("lastName"));
                    temporaryParent.setNumber(rs.getString("telephoneNumber"));
                    temporaryParent.setKidsID(rs.getString("students_id"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return temporaryParent;
    }

    public static UserInfo getUserInfo(String inSystemID) {
        connection();
        UserInfo temporaryUser = new UserInfo();
        try {
            ps = connect.prepareStatement("SELECT password, firstName, lastName, admin, telephoneNumber FROM roskilde_daycare.user WHERE ID = ?");
            ps.setString(1, inSystemID);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found in UserSystem.getUserInfo");
                alert.show();
            } else {
                while (rs.next()) {
                    temporaryUser.setFirstName(rs.getString("firstName"));
                    temporaryUser.setLastName(rs.getString("lastName"));
                    temporaryUser.setPassword(rs.getString("password"));
                    temporaryUser.setAdmin(rs.getBoolean("admin"));
                    temporaryUser.setTelephoneNumber(rs.getInt("telephoneNumber"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return temporaryUser;
    }

    public static String doubleToString(double num) {
        NumberFormat nf = DecimalFormat.getInstance();
        nf.setMaximumFractionDigits(0);
        String str = nf.format(num);
        return str;
    }

    public static void loginUser(ActionEvent event, String userName, String password) {
        try {
            connection();
            ps = connect.prepareStatement("SELECT password, firstName, lastName, admin, ID FROM roskilde_daycare.user WHERE userName = ?;");
            ps.setString(1, userName);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found");
                alert.show();
            } else {
                while (rs.next()) {
                    String retrievedFirstName = rs.getString("firstName");
                    String retrievedLastName = rs.getString("lastName");
                    String retrievedPassword = rs.getString("password");
                    boolean retrievedAdmin = rs.getBoolean("admin");
                    UserInfo.setUsingSystemID(rs.getString("ID"));

                    if (retrievedPassword.equals(password) && retrievedAdmin) {
                        //adminChangeScene(event, "admin-logged-in.fxml", "Welcome!", retrievedLastName, retrievedFirstName);
                        changeSceneNew(event, "admin-logged-in.fxml", "Admin menu");
                    } else if (retrievedPassword.equals(password)) {
                        changeSceneNew(event, "user-logged-in.fxml", "Welcome!");
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

    public static void addNewUser(String firstName, String lastName, String telephoneNumber, String userName, String password, boolean admin) {
        try {
            connection();
            ps = connect.prepareStatement("INSERT INTO user (firstName, lastName, telephoneNumber, userName, password, admin) " +
                    "VALUES (?, ?, ?, ?, ?, ?);");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, telephoneNumber);
            ps.setString(4, userName);
            ps.setString(5, password);
            ps.setBoolean(6, admin);
            if (firstName + lastName + telephoneNumber + userName + password != null && !(firstName + lastName + telephoneNumber + userName + password).equals(" ")) {
                ps.executeUpdate();
                System.out.println("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User created successfully");
                alert.show();
            } else {
                System.out.println("Please fill in all the information. Thank you");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public static void updateUser(int ID, String firstName, String lastName, String telephoneNumber, String userName, String password, boolean admin) {
        try {
            connection();
            ps = connect.prepareStatement("REPLACE INTO user (ID, firstName, lastName, telephoneNumber, userName, password, admin)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, Integer.toString(ID));
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, telephoneNumber);
            ps.setString(5, userName);
            ps.setString(6, password);
            ps.setBoolean(7, admin);
            if (firstName + lastName + telephoneNumber + userName + password != null) {
                ps.executeUpdate();
                System.out.println("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User created successfully");
                alert.show();
            } else {
                System.out.println("Please fill in all the information. Thank you");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public static ObservableList<UserInfo> getUserList() {
        // Create observableList
        ObservableList<UserInfo> userList = FXCollections.observableArrayList();
        try {
            // Connect to DB
            connection();
            ps = connect.prepareStatement("SELECT firstName, lastName, telephoneNumber, userName, admin FROM user;");
            rs = ps.executeQuery();
            while (rs.next()) {
                // creates userinfo object, adds the data to the list
                UserInfo userInfo = new UserInfo(rs.getString("firstName"), rs.getString("lastName"), rs.getInt("telephoneNumber"), rs.getString("userName"), rs.getBoolean("admin"));
                userList.add(userInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static ObservableList<UserInfo> getUserListID() {
        // Create observableList
        ObservableList<UserInfo> userList = FXCollections.observableArrayList();
        try {
            // Connect to DB
            connection();
            ps = connect.prepareStatement("SELECT ID, firstName, lastName, telephoneNumber, userName, admin FROM user;");
            rs = ps.executeQuery();
            while (rs.next()) {
                // creates userinfo object, adds the data to the list
                UserInfo userInfo = new UserInfo(rs.getInt("ID"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("telephoneNumber"), rs.getString("userName"), rs.getBoolean("admin"));
                userList.add(userInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static ObservableList<UserInfo> getNewUser() {
        // Create observableList
        ObservableList<UserInfo> userList = FXCollections.observableArrayList();
        try {
            // Connect to DB
            connection();
            ps = connect.prepareStatement("SELECT firstName, lastName, telephoneNumber, userName, admin FROM user ORDER BY ID DESC LIMIT 0;");
            rs = ps.executeQuery();
            while (rs.next()) {
                // creates userinfo object, adds the data to the list
                UserInfo userInfo = new UserInfo(rs.getString("firstName"), rs.getString("lastName"), rs.getInt("telephoneNumber"), rs.getString("userName"), rs.getBoolean("admin"));
                userList.add(userInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static void deleteUser(int ID) {
        connection();
        try {
            ps = connect.prepareStatement("DELETE FROM roskilde_daycare.user WHERE ID = ?;");
            ps.setString(1, String.valueOf(ID));
            ps.executeUpdate();
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
        /*if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
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
