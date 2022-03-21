package com.example.roskildedaycare;

public class UserInfo {
    private String name;
    private String lastName;
    private int number;
    private String username;
    private String password;
    private boolean admin;

    public void UserInfo(String name, String lastName, int number, String username, String password, boolean admin) {
        setName(name);
        setLastName(lastName);
        setNumber(number);
        setUsername(username);
        setPassword(password);
        setAdmin(admin);
    }

    public void UserInfo(String name, String lastName, int number, String username, boolean admin) {
        setName(name);
        setLastName(lastName);
        setNumber(number);
        setUsername(username);
        setAdmin(admin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
