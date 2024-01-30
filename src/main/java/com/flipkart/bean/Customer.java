package com.flipkart.bean;

public class Customer {
    private String customerId;
    private String password;
    private String username;
    private String email;
    private String contact;

    public String getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
}
