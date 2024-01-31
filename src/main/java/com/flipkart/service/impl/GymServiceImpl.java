package com.flipkart.service.impl;

import com.flipkart.bean.Gym;
import com.flipkart.dao.GymDAO;
import com.flipkart.exceptions.GymAreaDneException;
import com.flipkart.exceptions.GymDneException;
import com.flipkart.exceptions.GymOwnerDneException;
import com.flipkart.service.GymServiceInterface;

import java.util.List;

import static com.flipkart.constants.Constants.PURPLE_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class GymServiceImpl implements GymServiceInterface {

    GymDAO gymDao= GymDAO.getInstance();

    public List<String> getAreas(){
        return gymDao.getAllAreas();
    }
    public boolean sendOnboardReq(String name, String gstin, String city, int seats,String gymOwnerId)
    {
        return gymDao.sendOnboardReq(name, gstin, city, seats, gymOwnerId);
    }
    public Gym getGymById(String gymId){
        try {
            return gymDao.getGymById(gymId);
        } catch (GymDneException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Gym> getGymsByOwnerId(String gymOwnerId){
        try {
            return gymDao.getGymsByOwner(gymOwnerId);
        } catch (GymOwnerDneException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Gym> getGymByAreas(String area){
        try {
            return gymDao.getGymsByArea(area);
        } catch (GymAreaDneException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void viewGymRequests() {
        List<Gym> requestDetails = gymDao.viewRequests();
        String formatSpecifier = "%-20s";

        String[] headers = {PURPLE_COLOR + "       GymId        " + RESET_COLOR,
                PURPLE_COLOR + "        Name        " + RESET_COLOR,
                PURPLE_COLOR + "        City        " + RESET_COLOR,
                PURPLE_COLOR + "       GSTIN        " + RESET_COLOR,
                PURPLE_COLOR + "       Seats        " + RESET_COLOR};
        for(String header: headers) System.out.printf("| " + formatSpecifier, header);
        System.out.println(" |");

        for(Gym gym : requestDetails){
            String output = gym.toString();

            String[] keyValuePairs = output
                    .replace("{", "")
                    .replace("}", "")
                    .split(",\\s*");

            for (String pair : keyValuePairs) {
                String[] entry = pair.split("=");
                System.out.printf("| " + formatSpecifier, entry[1].replace("'", ""));
            }
            System.out.println(" |");
        }
        System.out.println();
    }

    public List<Gym> viewPendingRequests(String gymOwnerId){
        try {
            return gymDao.viewPendingRequests(gymOwnerId);
        } catch (GymOwnerDneException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Gym> viewAllPendingRequests(){
        try {
            return gymDao.viewAllPendingRequests();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean onBoardGym(String gymId){
        try {
            gymDao.onBoardGym(String.valueOf(gymId));
        } catch (GymDneException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void sendGymRequest(String name, String gstin, String city, int seats, String gymOwnerId){
        gymDao.sendOnboardReq(name, gstin, city, seats, gymOwnerId);
    }

    public void updateGymDetails(String newValue, String updateColumn, String gymId){
        try {
            gymDao.updateGym(newValue, updateColumn, gymId);
        } catch (GymDneException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteGymRequest(String gymId){
        gymDao.deleteGymRequest(String.valueOf(gymId));
    }
}