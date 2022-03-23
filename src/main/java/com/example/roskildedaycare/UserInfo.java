package com.example.roskildedaycare;

public class UserInfo {

    public UserInfo(String firstName, String lastName, String telephoneNumber, String username, String password, boolean admin) {
        setFirstName(firstName);
        setLastName(lastName);
        setTelephoneNumber(telephoneNumber);
        setUserName(username);
        setPassword(password);
        setAdmin(admin);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private String userName;
    private String password;
    private boolean admin;


    public UserInfo(String firstName, String lastName, String telephoneNumber, String userName, boolean admin) {
        setFirstName(firstName);
        setLastName(lastName);
        setTelephoneNumber(telephoneNumber);
        setUserName(userName);
        setAdmin(admin);
    }

    public UserInfo(int ID, String firstName, String lastName, String telephoneNumber, String userName, boolean admin) {
        setID(ID);
        setFirstName(firstName);
        setLastName(lastName);
        setTelephoneNumber(telephoneNumber);
        setUserName(userName);
        setAdmin(admin);
    }

    public UserInfo() {

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static String getUsingSystemID() {
        return usingSystemID;
    }

    public static void setUsingSystemID(String usingSystemID) {
        UserInfo.usingSystemID = usingSystemID;
    }

    public static String usingSystemID;


}
