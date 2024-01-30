package com.flipkart.service;

import com.flipkart.bean.GymOwner;

import java.util.List;

public interface GymOwnerServiceInterface {
    boolean register(String username, String password, String email, String contact);
    List<String> getOwnerIdByUsername(String username);
    void viewProfile();
    void updateGymOwnerDetails(String updatedVal,String attr,String customerId);
    void viewGymOwnerRequests();
    void approveGymOwner(String gymOwnerId);
    void rejectGymOwner(String gymOwnerId);
    public GymOwner getGymOwnerFromId(String gymOwnerId);
    public List<GymOwner>getPendingGymOwners();

}
