package com.flipkart.service;

import com.flipkart.bean.Gym;

import java.util.List;

public interface GymServiceInterface {
    public void viewGymRequests();
    public boolean onBoardGym(String gymId);
    public void deleteGymRequest(String gymId);
    List<Gym> viewPendingRequests(String gymOwnerId);
    public List<Gym> getGymByAreas(String area);
    void sendGymRequest(String name, String gstin, String city, int seats, String gymOwnerId);
    void updateGymDetails(String newValue, String updateColumn, String gymId);
    public List<Gym> getGymsByOwnerId(String gymOwnerId);
    public Gym getGymById(String gymId);
    public List<Gym> viewAllPendingRequests();
    List<String> getAreas();
    public boolean sendOnboardReq(String name, String gstin, String city, int seats,String gymOwnerId);
}
