package com.flipkart.bean;

public class GymOwner {
    private String gymOwnerId;
    private String password;
    private String name;
    private String contact;
    private String email;

    public String getGymOwnerId() {
        return gymOwnerId;
    }

    /**
     *
     * @param gymOwnerId
     */
    public void setGymOwnerId(String gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
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

    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
}
