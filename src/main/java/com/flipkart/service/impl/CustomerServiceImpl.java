package com.flipkart.service.impl;

import com.flipkart.bean.Customer;
import com.flipkart.dao.CustomerDAO;
import com.flipkart.dao.UserDAO;
import com.flipkart.exceptions.CustomerDneException;
import com.flipkart.exceptions.CustomerRegistrationFailedException;
import com.flipkart.service.CustomerServiceInterface;

import java.util.List;
import static com.flipkart.constants.Constants.*;

public class CustomerServiceImpl implements CustomerServiceInterface {
    CustomerDAO customerDAO = CustomerDAO.getInstance();
    UserDAO userDAO = UserDAO.getInstance();

    public void updateCustomerDetails(String updatedVal, String attr, String customerId) {
        try {
            customerDAO.updateCustomerDetails(updatedVal, attr, customerId);
        } catch (CustomerDneException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean register(String name, String password, String email, String contact) {
        try {
            customerDAO.registerCustomer(name, password, email, contact);
        } catch (CustomerRegistrationFailedException e) {
            System.out.println(e.getMessage());
            return false;
        }
        userDAO.addUser(name, password, ROLE_GYM_CUSTOMER);
        return true;
    }

    public void viewProfile() {
        List<String> userDetails = userDAO.getCurrentUser();
        System.out.println(PURPLE_COLOR + "\nUsername : " + RESET_COLOR + userDetails.get(0) + "\n" +
                PURPLE_COLOR + "Role : " + RESET_COLOR + userDetails.get(1) + "\n" +
                PURPLE_COLOR + "Password : " + RESET_COLOR + userDetails.get(2) + "\n" +
                PURPLE_COLOR + "Contact : " + RESET_COLOR + userDetails.get(3) + "\n" +
                PURPLE_COLOR + "Email : " + RESET_COLOR + userDetails.get(4) + "\n");
    }
    public Customer getCustomerFromId(String customerId)
    {
        return customerDAO.getCustomer(customerId);
    }

    public String getCustomerIdFromUsername(String username) {
        return customerDAO.getIdFromName(username);
    }
}
