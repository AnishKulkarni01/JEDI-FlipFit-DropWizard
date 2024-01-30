package com.flipkart.client;
import com.flipkart.bean.Booking;
import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.service.impl.*;
import com.flipkart.validator.Validators;

import java.util.*;

import static com.flipkart.constants.Constants.*;

public class GymCustomerFlipFitMenu {
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    BookingServiceImpl bookingServiceImpl = new BookingServiceImpl();
    SlotServiceImpl slotServiceImpl = new SlotServiceImpl();
    GymServiceImpl gymServiceImpl = new GymServiceImpl();
    Validators validators = new Validators();
    String customerId = getCustomerId();
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    private void showMenuOptions() {
        System.out.println("1. " + YELLOW_COLOR + "\nEdit Profile\n" + RESET_COLOR +
                "2. " + YELLOW_COLOR + "View Profile\n" + RESET_COLOR +
                "3. " + YELLOW_COLOR + "View Bookings\n" + RESET_COLOR +
                "4. " + YELLOW_COLOR + "Book Slot\n" + RESET_COLOR +
                "5. " + YELLOW_COLOR + "Cancel Booking\n" + RESET_COLOR +
                "6. " + YELLOW_COLOR + "Log Out\n" + RESET_COLOR +
                "7. " + YELLOW_COLOR + "Back\n" + RESET_COLOR);
    }

    private String getCustomerId(){
        return customerServiceImpl.getCustomerIdFromUsername(userServiceImpl.getCurrentUsername());
    }

    private void listSlots(){
        List<String> areas = gymServiceImpl.getAreas();
        for(String area : areas){
            System.out.println("    " + Integer.toString(areas.indexOf(area)+1) + ". " + area);
        }
        int areaOption = scanner.nextInt();
        String selectedArea = areas.get(areaOption-1);
        List<Gym>gymList = gymServiceImpl.getGymByAreas(selectedArea);
        String[] headers = {PURPLE_COLOR + " Gym Id " + RESET_COLOR, PURPLE_COLOR + " Gym Name " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(int i=0;i<gymList.size();i++)
        {
            ArrayList<String> details = new ArrayList<String>();
            details.add(gymList.get(i).getGymId());
            details.add(gymList.get(i).getName());


            String formatSpecifier;
            for (String detail: details){
                formatSpecifier = "%-9s";
                System.out.printf("| " + formatSpecifier, detail);
            }
            System.out.println(" |");
        }
        System.out.println("Enter GymId :");
        String gymId=scanner.next();
        Set<String>st=slotServiceImpl.getSlotsByCustomerId(customerId);

        System.out.println("Choose a slot at " + selectedArea);
        List<Slot> slotList = slotServiceImpl.getSlotsByGymId(gymId);
        String[] headers1 = {PURPLE_COLOR + " Slot Id " + RESET_COLOR, PURPLE_COLOR + " Date " + RESET_COLOR,PURPLE_COLOR + " StartTime " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(Slot slot: slotList) {
            if(!st.contains(slot.getSlotId()) && bookingServiceImpl.canBook(slot.getSlotId(),gymId))

            {
                ArrayList<String> details = new ArrayList<String>();
                details.add(slot.getSlotId());
                details.add(slot.getDate());
                details.add(slot.getStartTime());


                String formatSpecifier;
                for (String detail: details){
                    formatSpecifier = "%-9s";
                    System.out.printf("| " + formatSpecifier, detail);
                }
                System.out.println(" |");
                //System.out.println("Slot Id : " + slot.getSlotId() +" Date : "+slot.getDate()+" Time : "+slot.getStartTime() +":00");
            }
        }
    }

    private void viewBookings(){
        System.out.println(BLUE_COLOR + "The bookings are as follows - " + RESET_COLOR);

        //booking.toString() later
        String[] headers = {PURPLE_COLOR + " Booking Id " + RESET_COLOR, PURPLE_COLOR + " Gym " + RESET_COLOR, PURPLE_COLOR + " Time " + RESET_COLOR, PURPLE_COLOR + " Date " + RESET_COLOR};
        System.out.println("| " + String.join(" | ", headers) + " |");
        for(Booking booking : bookingServiceImpl.getBookingByCustomerId(customerId)){
            Slot slot = slotServiceImpl.getSlotBySlotId(booking.getSlotId());

            ArrayList<String> details = new ArrayList<>();
            details.add(booking.getBookingId());
            details.add(gymServiceImpl.getGymById(slot.getGymId()).getName());
            details.add(slot.getStartTime()+":00");
            details.add(slot.getDate());
            String formatSpecifier;
            for(int i=0; i< details.size(); i++){
                if(i==0){
                    formatSpecifier = "%-13s";
                }
                else if(i==1){
                    formatSpecifier = "%-6s";
                }
                else if(i==2){
                    formatSpecifier = "%-7s";
                }
                else{
                    formatSpecifier = "%-6s";
                }
                System.out.printf("| " + formatSpecifier, details.get(i));
            }
            System.out.println(" |"); }
    }
    private void bookSlot(){
        System.out.println(BLUE_COLOR + "Book your Slot :)" + RESET_COLOR);
        listSlots();
        System.out.println("Select an area where you'd like to book a slot.");
        String slotId = scanner.next();

        bookingServiceImpl.addBooking(customerId, slotId);
        System.out.println(GREEN_COLOR + "Booking successfully added." + RESET_COLOR);
    }

    private void cancelSlot(){
        for(Booking booking : bookingServiceImpl.getBookingByCustomerId(customerId)){
            Slot slot = slotServiceImpl.getSlotBySlotId(booking.getSlotId());
            String[] headers = {PURPLE_COLOR + " Booking Id " + RESET_COLOR, PURPLE_COLOR + " Date " + RESET_COLOR, PURPLE_COLOR + " Time " + RESET_COLOR};
            System.out.println("| " + String.join(" | ", headers) + " |");
            ArrayList<String> details = new ArrayList<>();
            details.add(booking.getBookingId());
            details.add(slot.getDate());
            details.add(slot.getStartTime()+":00");
            String formatSpecifier;
            for(int i=0; i< details.size(); i++){
                if(i==0){
                    formatSpecifier = "%-13s";
                }
                else if(i==1){
                    formatSpecifier = "%-7s";
                }
                else{
                    formatSpecifier = "%-6s";
                }
                System.out.printf("| " + formatSpecifier, details.get(i));
            }
            System.out.println(" |");
        }
        System.out.println("Enter BookingId - ");
        String deleteBookingId = scanner.next();
        bookingServiceImpl.deleteBooking(deleteBookingId);
    }

    private void viewProfile(){
        System.out.println("Profile details are as follows - ");
        customerServiceImpl.viewProfile();
    }

    private void editProfile() {
        while(true) {
            System.out.println("\nChoose an appropriate option to update the value - ");
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
                    customerServiceImpl.updateCustomerDetails(newValue, "email", customerId);
                    System.out.println(GREEN_COLOR + "\nEmail has been updated successfully.\n" + RESET_COLOR);
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
                    customerServiceImpl.updateCustomerDetails(newValue1, "contact", customerId);
                    System.out.println(GREEN_COLOR + "\nContact number has been updated successfully.\n" + RESET_COLOR);
                    return;
                default:
                    System.out.println(RED_COLOR + "\nPlease select a valid option\n" + RESET_COLOR);
            }
        }
    }

    public void showCustomerMenu(){
        while(true){
            showMenuOptions();
            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    editProfile();
                    break;
                case 2:
                    viewProfile();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    bookSlot();
                    break;
                case 5:
                    cancelSlot();
                    break;
                case 6:
                    System.out.println(GREEN_COLOR + "Logged out successfully" + RESET_COLOR);
                    return;
                case 7:
                    return;
                default:
                    throw new IllegalStateException(RED_COLOR + "Unexpected value: " + choice + RESET_COLOR);
            }
        }
    }
}