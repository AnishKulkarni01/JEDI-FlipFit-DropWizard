package com.flipkart.client;

import com.flipkart.service.impl.GymOwnerServiceImpl;
import com.flipkart.service.impl.GymServiceImpl;

import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class AdminFlipFitMenu {
    GymServiceImpl gymServiceImpl = new GymServiceImpl();
    GymOwnerServiceImpl gymOwnerServiceImpl = new GymOwnerServiceImpl();
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    public void showMenuOptions(){
        System.out.println("1. " + YELLOW_COLOR + "View Pending Requests.\n" + RESET_COLOR +
                "2. " + YELLOW_COLOR + "Approve Gym Onboarding Requests.\n" + RESET_COLOR +
                "3. " + YELLOW_COLOR + "Decline Gym Onboarding Requests.\n" + RESET_COLOR +
                "4. " + YELLOW_COLOR + "Approve Gym Owner Requests.\n" + RESET_COLOR +
                "5. " + YELLOW_COLOR + "Decline Gym Owner Requests.\n" + RESET_COLOR +
                "6. " + YELLOW_COLOR +"Log Out\n" + RESET_COLOR + "7. " + YELLOW_COLOR +
                "Back\n" + RESET_COLOR);
    }

    private void printGymRequests(){
        gymServiceImpl.viewGymRequests();
    }

    private void printGymOwnerRequests(){
        gymOwnerServiceImpl.viewGymOwnerRequests();
    }

    public void showAdminFlipFitMenu(){
        while(true) {
            showMenuOptions();

            int action = scanner.nextInt();
            switch(action){
                case 1:
                    System.out.println(BLUE_COLOR + "\nViewing Pending Requests\n" + RESET_COLOR);
                    printGymRequests();
                    break;
                case 2:
                    printGymRequests();
                    System.out.println(BLUE_COLOR + "Select GymId to Approve Request - " + RESET_COLOR);
                    int approvedGymId= scanner.nextInt();
                    gymServiceImpl.onBoardGym(String.valueOf(approvedGymId));
                    break;
                case 3:
                    printGymRequests();
                    System.out.println(BLUE_COLOR + "Select GymId to Reject Request - " + RESET_COLOR);
                    int rejectGymId= scanner.nextInt();
                    gymServiceImpl.deleteGymRequest(String.valueOf(rejectGymId));
                    break;
                case 4:
                    printGymOwnerRequests();
                    System.out.println(BLUE_COLOR + "\nSelect GymOwnerId to approve request - " + RESET_COLOR);
                    int approveGymOwnerId = scanner.nextInt();
                    gymOwnerServiceImpl.approveGymOwner(String.valueOf(approveGymOwnerId));
                    break;
                case 5:
                    printGymOwnerRequests();
                    System.out.println(BLUE_COLOR + "\nSelect GymOwnerId to reject request - " + RESET_COLOR);
                    int rejectGymOwnerId = scanner.nextInt();
                    gymOwnerServiceImpl.rejectGymOwner(String.valueOf(rejectGymOwnerId));
                    break;
                case 6:
                    System.out.println(GREEN_COLOR + "\nLogged out successfully." + RESET_COLOR);
                    return;
                case 7:
                    return;
                default:
                    System.out.println(RED_COLOR + "\nPlease select an appropriate option from the menu." + RESET_COLOR);
                    break;
            }
        }
    }
}
