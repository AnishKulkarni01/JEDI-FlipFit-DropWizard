package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.dao.*;
import com.flipkart.service.impl.GymOwnerServiceImpl;
import com.flipkart.service.impl.GymServiceImpl;
import com.flipkart.service.impl.SlotServiceImpl;
import com.flipkart.service.impl.UserServiceImpl;
import com.flipkart.validator.Validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class GymOwnerFlipFitMenu {
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    SlotServiceImpl slotServiceImpl = new SlotServiceImpl();
    GymServiceImpl gymServiceImpl = new GymServiceImpl();
    GymOwnerServiceImpl gymOwnerServiceImpl = new GymOwnerServiceImpl();
    Validators validators = new Validators();
    String gymOwnerId = getGymOwnerId();
    Scanner scanner= new Scanner(System.in).useDelimiter("\\n");

    public String getGymOwnerId(){
        return gymOwnerServiceImpl.getOwnerIdByUsername(userServiceImpl.getCurrentUsername()).get(0);
    }

    private void showMenuOptions(){
        System.out.println("1. " + YELLOW_COLOR + "Request Gym Onboarding\n" + RESET_COLOR +
                "2. " + YELLOW_COLOR + "Gym Details Update\n" + RESET_COLOR  +
                "3. " + YELLOW_COLOR + "Add Slot\n" + RESET_COLOR +
                "4. " + YELLOW_COLOR + "Update Slot\n" + RESET_COLOR +
                "5. " + YELLOW_COLOR + "View Slots\n" + RESET_COLOR +
                "6. " + YELLOW_COLOR + "Delete Slot\n" + RESET_COLOR +
                "7. " + YELLOW_COLOR + "View Profile\n" + RESET_COLOR +
                "8. " + YELLOW_COLOR + "Edit Profile\n" + RESET_COLOR +
                "9. " + YELLOW_COLOR + "View Gyms\n" + RESET_COLOR +
                "10. " + YELLOW_COLOR + "View Pending Requests\n" + RESET_COLOR +
                "11. " + YELLOW_COLOR + "Log Out\n" + RESET_COLOR +
                "12. " + YELLOW_COLOR + "Back" + RESET_COLOR);
    }

    private void sendOnboardingRequest(){
        System.out.println("Enter Gym Details - ");

        System.out.println("Enter Gym Name : ");
        String gymName = scanner.next();
        System.out.println("Enter GSTIN : ");
        String gstin = scanner.next();
        System.out.println("Enter Gym City : ");
        String city = scanner.next();
        System.out.println("Enter Seats : ");
        int seats = scanner.nextInt();

        gymServiceImpl.sendGymRequest(gymName, gstin, city, seats, gymOwnerId);
        System.out.println(GREEN_COLOR + "Request Sent Successfully" + RESET_COLOR);
    }

    /**
     *
     * @param gymId
     */
    private void updateGym(String gymId){
        while(true) {
            System.out.println("Select Option to Update - ");
            System.out.println("1. " + YELLOW_COLOR + "Name\n" + RESET_COLOR +
                    "2. " + YELLOW_COLOR + "City\n" + RESET_COLOR +
                    "3. " + YELLOW_COLOR + "Seats\n" + RESET_COLOR +
                    "4. " + YELLOW_COLOR + "GSTIN" + RESET_COLOR);
            int updateColumn = scanner.nextInt();

            System.out.println("Enter new value");
            String newValue = scanner.next();

            switch (updateColumn) {
                case 1:
                    gymServiceImpl.updateGymDetails(newValue, "name", gymId);
                    return;
                case 2:
                    gymServiceImpl.updateGymDetails(newValue, "city", gymId);
                    return;
                case 3:
                    gymServiceImpl.updateGymDetails(newValue, "seats", gymId);
                    return;
                case 4:
                    gymServiceImpl.updateGymDetails(newValue, "gstin", gymId);
                    return;
                default:
                    System.out.println(RED_COLOR + "Please select a valid option" + RESET_COLOR);
            }
        }
    }
    private void viewProfile(){
        System.out.println("Profile details are as follows - ");
        gymOwnerServiceImpl.viewProfile();
    }
    private void editProfile() {
        while(true) {
            System.out.println("Choose an appropriate option to update the value - ");
            System.out.println("1. " + YELLOW_COLOR + "Email\n" + RESET_COLOR +
                    "2. " + YELLOW_COLOR + "Contact" + RESET_COLOR);
            int updateColumn = scanner.nextInt();

            switch (updateColumn) {
                case 1:
                    String newValue;
                    while(true) {
                        System.out.println("\nEnter the updated email - ");
                        newValue = scanner.next();
                        if(!validators.isEmailValid(newValue)){
                            System.out.println("\nIncorrect email id format. Please try again.");
                        }
                        else break;
                    }
                    gymOwnerServiceImpl.updateGymOwnerDetails(newValue, "email", gymOwnerId);
                    System.out.println(GREEN_COLOR + "Email has been updated successfully." + RESET_COLOR);
                    return;
                case 2:
                    String newValue1;
                    while(true) {
                        System.out.println("\nEnter the updated contact number - ");
                        newValue1 = scanner.next();
                        if(!validators.isPhoneValid(newValue1)){
                            System.out.println("\nIncorrect phone number format. Please try again.");
                        }
                        else break;
                    }
                    gymOwnerServiceImpl.updateGymOwnerDetails(newValue1, "contact", gymOwnerId);
                    System.out.println(GREEN_COLOR + "Contact number has been updated successfully." + RESET_COLOR);
                    return;
                default:
                    System.out.println(RED_COLOR + "Please select a valid option" + RESET_COLOR);
            }
        }
    }
    private void addSlot(){
        System.out.println("Add Slots");

        System.out.println(BLUE_COLOR + "Enter the following details - " + RESET_COLOR);
        System.out.println("GymId : ");
        String date, time, gymId = scanner.next();

        while(true) {
            System.out.println("Date (Enter in DD/MM/YYYY format) : ");
            date = scanner.next();
            if(!validators.isFutureDate(date)){
                System.out.println("Please enter a date in the future in the right format.");
            }
            else break;
        }

        while(true) {
            System.out.println("StartTime (Enter in 24-hour format as HH:MM) : ");
            time = scanner.next();
            if(!validators.isTimeValid(time)){
                System.out.println("Please enter the time in the right format.");
            }
            else break;
        }

        slotServiceImpl.createSlot(date, time, gymId);
    }

    private void updateSlot(){
        while(true) {
            System.out.println("Slot Details Update");
            System.out.println("Enter SlotId");
            String updateSlotId = scanner.next();

            System.out.println("Select Option to Update - ");
            System.out.println("1. " + YELLOW_COLOR + "Date (Enter in DD/MM/YYYY format) : \n" + RESET_COLOR +
                    "2. " + YELLOW_COLOR + "Start Time (Enter in 24-hour format as HH:MM) : " + RESET_COLOR);
            int updateSlotColumn = scanner.nextInt();

            switch(updateSlotColumn){
                case 1:
                    String date;
                    while(true) {
                        System.out.println("Date (Enter in DD/MM/YYYY format) : ");
                        date = scanner.next();
                        if(!validators.isFutureDate(date)){
                            System.out.println("Please enter a date in the future in the right format.");
                        }
                        else break;
                    }
                    slotServiceImpl.updateSlot(date, "date", updateSlotId);
                    return;
                case 2:
                    String time;
                    while(true) {
                        System.out.println("StartTime (Enter in 24-hour format as HH:MM) : ");
                        time = scanner.next();
                        if(!validators.isTimeValid(time)){
                            System.out.println("Please enter the time in the right format.");
                        }
                        else break;
                    }
                    slotServiceImpl.updateSlot(time, "startTime", updateSlotId);
                    return;
                default:
                    System.out.println(RED_COLOR + "Please select valid option." + RESET_COLOR);
            }
        }
    }

    private void viewSlots(){
        System.out.println("View Slots");
        System.out.println("Enter GymId");
        int viewSlotGymId= scanner.nextInt();

        List<Slot> slotList=slotServiceImpl.getSlotsByGymId(Integer.toString(viewSlotGymId));
        if(slotList.isEmpty())
        {
            System.out.println("Enter correct gymId");
            return;
        }
        String[] headers = {PURPLE_COLOR + " Slot Id " + RESET_COLOR, PURPLE_COLOR + " Start Time " + RESET_COLOR, PURPLE_COLOR + " Date " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(Slot slot : slotList) {
            ArrayList<String> details = new ArrayList<>();
            details.add(slot.getSlotId());
            details.add(slot.getStartTime()+":00");
            details.add(slot.getDate());
            String formatSpecifier;
            for(int i=0; i< details.size(); i++){
                if(i==0){
                    formatSpecifier = "%-10s";
                }
                else if(i==1){
                    formatSpecifier = "%-13s";
                }
                else{
                    formatSpecifier = "%-6s";
                }
                System.out.printf("| " + formatSpecifier, details.get(i));
            }
            System.out.println(" |");
        }
    }
    private void deleteSlot()
    {
        System.out.println("Enter SlotId : ");
        int delSlotId=scanner.nextInt();
        slotServiceImpl.deleteSlotById(Integer.toString(delSlotId));
    }

    private void viewGyms(){
        System.out.println("Viewing Gyms");
        String[] headers = {PURPLE_COLOR + " Gym Id " + RESET_COLOR, PURPLE_COLOR + " Gym Name " + RESET_COLOR,PURPLE_COLOR + " City " + RESET_COLOR,PURPLE_COLOR + " GSTIN " + RESET_COLOR,PURPLE_COLOR + " Seats " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(Gym gym : gymServiceImpl.getGymsByOwnerId(gymOwnerId)){
            ArrayList<String> details = new ArrayList<String>();
            details.add(gym.getGymId());
            details.add(gym.getName());
            details.add(gym.getCity());
            details.add(gym.getGstin());
            details.add(Integer.toString(gym.getSeats()));


            String formatSpecifier;
            for (String detail: details){
                formatSpecifier = "%-9s";
                System.out.printf("| " + formatSpecifier, detail);
            }
            System.out.println(" |");
        }
    }

    private void viewPendingRequests(){
        System.out.println("Showing Pending Requests");
        String[] headers = {PURPLE_COLOR + " Gym Id " + RESET_COLOR, PURPLE_COLOR + " Gym Name " + RESET_COLOR,PURPLE_COLOR + " City " + RESET_COLOR,PURPLE_COLOR + " GSTIN " + RESET_COLOR,PURPLE_COLOR + " Seats " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(Gym gym : gymServiceImpl.viewPendingRequests(gymOwnerId)){
            ArrayList<String> details = new ArrayList<String>();
            details.add(gym.getGymId());
            details.add(gym.getName());
            details.add(gym.getCity());
            details.add(gym.getGstin());
            details.add(Integer.toString(gym.getSeats()));


            String formatSpecifier;
            for (String detail: details){
                formatSpecifier = "%-9s";
                System.out.printf("| " + formatSpecifier, detail);
            }
            System.out.println(" |");
            //System.out.println(gym.toString())
        }
    }

    public void showGymOwnerFlipMenu() {
        while(true) {
            showMenuOptions();

            int opt=scanner.nextInt();
            switch(opt) {
                case 1:
                    sendOnboardingRequest();
                    break;
                case 2:
                    System.out.println("Gym Details Update");
                    System.out.println("Enter GymId");
                    String updateGymId = scanner.next();
                    updateGym(updateGymId);
                    break;
                case 3:
                    addSlot();
                    break;
                case 4:
                    updateSlot();
                    break;
                case 5:
                    viewSlots();
                    break;
                case 6:
                    deleteSlot();
                    break;
                case 7:
                    viewProfile();
                    break;
                case 8:
                    editProfile();
                    break;
                case 9:
                    viewGyms();
                    break;
                case 10:
                    viewPendingRequests();
                    break;
                case 11:
                    System.out.println(GREEN_COLOR + "Logged out successfully." + RESET_COLOR);
                    return;
                case 12:
                    return;
                default:
                    throw new IllegalStateException(RED_COLOR + "Unexpected value: " + opt + RESET_COLOR);
            }
        }
    }
}
