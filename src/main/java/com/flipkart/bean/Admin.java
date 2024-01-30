package com.flipkart.bean;

public class Admin {
    int AdminID;
    String username;
    String password;

    public int getAdminID() {
        return AdminID;
    }

    /**
     *
     * @param adminID
     */
    public void setAdminID(int adminID) {
        AdminID = adminID;
    }

    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
