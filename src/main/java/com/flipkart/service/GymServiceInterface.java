package com.flipkart.service;

import com.flipkart.bean.Gym;

import java.util.List;

public interface GymServiceInterface {
    public void viewGymRequests();
    public void onBoardGym(String gymId);
    public void deleteGymRequest(String gymId);
    List<Gym> viewPendingRequests(String gymOwnerId);
    void sendGymRequest(String name, String gstin, String city, int seats, String gymOwnerId);
    void updateGymDetails(String newValue, String updateColumn, String gymId);
}
