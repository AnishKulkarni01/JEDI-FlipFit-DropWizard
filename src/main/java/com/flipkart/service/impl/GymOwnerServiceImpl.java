package com.flipkart.service.impl;

import com.flipkart.bean.GymOwner;
import com.flipkart.dao.GymOwnerDAO;
import com.flipkart.dao.UserDAO;
import com.flipkart.exceptions.GymOwnerDneException;
import com.flipkart.exceptions.GymOwnerRegistrationFailedException;
import com.flipkart.service.GymOwnerServiceInterface;

import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.Constants.PURPLE_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class GymOwnerServiceImpl implements GymOwnerServiceInterface {
    GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();
    UserDAO userDAO = UserDAO.getInstance();

    public void register(String username, String password, String email, String contact) {
        try {
            gymOwnerDAO.registerGymOwner(username, password,email,contact);
        } catch (GymOwnerRegistrationFailedException e) {
            System.out.println(e.getMessage());
        }
        userDAO.addUser(username, password, "GYM_OWNER");
    }

    public List<String> getOwnerIdByUsername(String username){
        return gymOwnerDAO.getIdFromName(username);
    }

    public void viewProfile() {
        List<String> userDetails = userDAO.getCurrentUser();
        System.out.println(PURPLE_COLOR + "Username : " + RESET_COLOR + userDetails.get(0) + "\n" + PURPLE_COLOR + "Role : " + RESET_COLOR + userDetails.get(1) + "\n" + PURPLE_COLOR + "Password : " + RESET_COLOR + userDetails.get(2) + "\n" + PURPLE_COLOR +"Contact : " + RESET_COLOR + userDetails.get(3) + "\n" + PURPLE_COLOR + "Email : " + RESET_COLOR + userDetails.get(4) + "\n");
    }

    public void updateGymOwnerDetails(String updatedVal,String attr,String customerId) {
        try {
            gymOwnerDAO.updateGymOwnerDetails(updatedVal, attr, customerId);
        } catch (GymOwnerDneException e) {
            System.out.println(e.getMessage());
        }

    }

    public void viewGymOwnerRequests(){
        List<GymOwner> gymOwnerList = gymOwnerDAO.getPendingGymOwners();
        String[] headers = {PURPLE_COLOR + " Gym Owner Id " + RESET_COLOR,
                PURPLE_COLOR + " Gym Owner Name " + RESET_COLOR,
                PURPLE_COLOR + " Gym Owner Contact " + RESET_COLOR,
                PURPLE_COLOR + "     Gym Owner Email     " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(GymOwner gymOwner : gymOwnerList){
            ArrayList<String> details = new ArrayList<>();
            details.add(gymOwner.getGymOwnerId());
            details.add(gymOwner.getName());
            details.add(gymOwner.getContact());
            details.add(gymOwner.getEmail());
            String formatSpecifier;
            for(int i=0; i< details.size(); i++){
                if(i==0){
                    formatSpecifier = "%-15s";
                }
                else if(i==1){
                    formatSpecifier = "%-17s";
                }
                else if(i==2){
                    formatSpecifier = "%-20s";
                }
                else{
                    formatSpecifier = "%-25s";
                }
                System.out.printf("| " + formatSpecifier, details.get(i));
            }
            System.out.println(" |");
        }
    }

    public void approveGymOwner(String gymOwnerId){
        try {
            gymOwnerDAO.approveGymOwner(String.valueOf(gymOwnerId));
        } catch (GymOwnerDneException e) {
            System.out.println(e.getMessage());
        }
    }

    public void rejectGymOwner(String gymOwnerId){
        try {
            gymOwnerDAO.rejectGymOwner(String.valueOf(gymOwnerId));
        } catch (GymOwnerDneException e) {
            System.out.println(e.getMessage());        }
    }
}
