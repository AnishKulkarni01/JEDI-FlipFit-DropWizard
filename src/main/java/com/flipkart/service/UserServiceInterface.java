package com.flipkart.service;

public interface UserServiceInterface {
    boolean authenticate(String username, String password, String role);

    void login(String username);

    void updatePassword();
    String getCurrentUsername();
}
