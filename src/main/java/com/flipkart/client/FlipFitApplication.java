package com.flipkart.client;

import com.flipkart.service.impl.CustomerServiceImpl;
import com.flipkart.service.impl.GymOwnerServiceImpl;
import com.flipkart.service.impl.UserServiceImpl;
import com.flipkart.validator.Validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

public class FlipFitApplication {

    private static final CustomerServiceImpl customerService = new CustomerServiceImpl();
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static final GymOwnerServiceImpl gymOwnerServiceImpl = new GymOwnerServiceImpl();
    private static final Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    private static final Validators validators = new Validators();

    private static void showMenuOptions(){
        String WELCOME_MESSAGE = BOLD + GREEN_COLOR + "\n<-----Welcome to FlipFit Application----->" + RESET_COLOR;
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PURPLE_COLOR + "<-Choice Menu->" + RESET_COLOR);
        System.out.println("1. " + GREEN_COLOR  +  "Login" + RESET_COLOR);
        System.out.println("2. " + BLUE_COLOR + "Registration of Gym Customer" + RESET_COLOR);
        System.out.println("3. " + BLUE_COLOR + "Registration of Gym Owner" + RESET_COLOR);
        System.out.println("4. " + BLUE_COLOR + "Update Password" + RESET_COLOR);
        System.out.println("5. " + RED_COLOR + "Exit\n" + RESET_COLOR);
    }

    private static String chooseRole(){
        System.out.println("\nChoose role : \n" + "1. "+ YELLOW_COLOR + "Gym Customer\n" + RESET_COLOR +
                "2. " + YELLOW_COLOR + "Gym Owner\n" + RESET_COLOR +
                "3. " + YELLOW_COLOR + "Gym Admin\n" + RESET_COLOR);

        int role;
        //try catch for error
        role = scanner.nextInt();

        switch(role){
            case 1:
                return ROLE_GYM_CUSTOMER;
            case 2:
                return ROLE_GYM_OWNER;
            case 3:
                return ROLE_ADMIN;
            default:
                System.out.println(RED_COLOR + "\nPlease select a valid role.\n" + RESET_COLOR);
                return chooseRole();
        }
    }

    /**
     *
     * @param role
     */
    private static void showClientMenu(String role){
        switch(role) {
            case ROLE_GYM_CUSTOMER:
                System.out.println(BLUE_COLOR + "\n<-Gym Customer Menu->" + RESET_COLOR);
                GymCustomerFlipFitMenu customerMenu = new GymCustomerFlipFitMenu();
                customerMenu.showCustomerMenu();
                break;
            case ROLE_GYM_OWNER:
                System.out.println(BLUE_COLOR + "\n<-Gym Owner Menu->" + RESET_COLOR);
                GymOwnerFlipFitMenu ownerMenu = new GymOwnerFlipFitMenu();
                ownerMenu.showGymOwnerFlipMenu();
                break;
            case ROLE_ADMIN:
                System.out.println(BLUE_COLOR + "\n<-Gym Admin Menu->" + RESET_COLOR);
                AdminFlipFitMenu adminMenu = new AdminFlipFitMenu();
                adminMenu.showAdminFlipFitMenu();
                break;
            default:
                throw new IllegalStateException(RED_COLOR + "\nUnexpected value: " + role + RESET_COLOR);
        }
    }

    private static void registerGymCustomer(){
        String username, passcode, email, contact;
        System.out.println(GREEN_COLOR + "\nRegistering Gym Customer." + RESET_COLOR + "\n");

        System.out.println("Enter customer name:");
        username = scanner.next();
        System.out.println("Enter customer passcode:");
        passcode = scanner.next();

        while(true) {
            System.out.println("Enter email:");
            email = scanner.next();
            if(!validators.isEmailValid(email)){
                System.out.println("\nIncorrect email id format. Please try again.");
            }
            else break;
        }

        while(true) {
            System.out.println("Enter contact:");
            contact = scanner.next();
            if(!validators.isPhoneValid(contact)){
                System.out.println("\nIncorrect phone number format. Please try again.");
            }
            else break;
        }

        customerService.register(username, passcode, email, contact);
    }

    private static void registerGymOwner(){
        String username, passcode, email, contact;
        System.out.println(GREEN_COLOR + "\nRegistering Gym Owner." + RESET_COLOR + "\n");

        System.out.println("Enter gym owner name: ");
        username = scanner.next();
        System.out.println("Enter gym owner passcode: ");
        passcode = scanner.next();

        while(true) {
            System.out.println("Enter email:");
            email = scanner.next();
            if(!validators.isEmailValid(email)){
                System.out.println("\nIncorrect email id format. Please try again.\n");
            }
            else break;
        }

        while(true) {
            System.out.println("Enter contact:");
            contact = scanner.next();
            if(!validators.isPhoneValid(contact)){
                System.out.println("\nIncorrect phone number format. Please try again.\n");
            }
            else break;
        }

        gymOwnerServiceImpl.register(username, passcode, email, contact);
    }

    private static void mainPage(){
        while(true){
            showMenuOptions();

            int option = scanner.nextInt();
            String username, passcode, role;

            switch(option) {
                case 1:
                    role = chooseRole();

                    System.out.println("\nEnter Username : ");
                    username = scanner.next();
                    System.out.println("Enter Passcode : ");
                    passcode = scanner.next();

                    if (!userService.authenticate(username, passcode, role)) {
                        System.out.println(RED_COLOR + "\nWrong credentials.\n" + RESET_COLOR);
                        break;
                    } else {
                        System.out.println(GREEN_COLOR + "\nSuccessfully logged in as " + role + " at "
                                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"))
                                + "\n" + RESET_COLOR);
                    }
                    userService.login(username);
                    showClientMenu(role);
                    break;
                case 2:
                    registerGymCustomer();
                    break;
                case 3:
                    registerGymOwner();
                    break;
                case 4:
                    userService.updatePassword();
                    break;
                case 5:
                    return;
                default:
                    throw new IllegalStateException(RED_COLOR + "\nUnexpected value: " + option + RESET_COLOR);
            }
        }
    }

    public static void main(String[] args) {
        mainPage();
    }
}