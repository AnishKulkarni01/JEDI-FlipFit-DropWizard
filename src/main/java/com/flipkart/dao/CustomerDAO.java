package com.flipkart.dao;

import com.flipkart.bean.Customer;

import com.flipkart.exceptions.CustomerDneException;
import com.flipkart.exceptions.CustomerRegistrationFailedException;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flipkart.constants.Constants.GREEN_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;
import static com.flipkart.constants.SQLConstants.*;

public class CustomerDAO {
    static CustomerDAO customerDAO = null;

    public static synchronized CustomerDAO getInstance() {
        if(customerDAO == null) {
            customerDAO = new CustomerDAO();
        }

        return customerDAO;
    }

    /**
     *
     * @param username
     * @param password
     * @param email
     * @param contact
     * @throws CustomerRegistrationFailedException
     */
    public void registerCustomer(String username, String password, String email, String contact) throws CustomerRegistrationFailedException {
        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_CUSTOMER);

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, contact);

            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e) {
            throw new CustomerRegistrationFailedException(username);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param customerId
     * @return
     */
    public Customer getCustomer(String customerId){
        Customer customer = new Customer();

        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_ID);

            stmt.setString(1, customerId);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            customer.setEmail(rs.getString("email"));
            customer.setCustomerId(rs.getString("customerId"));
            customer.setPassword(rs.getString("password"));
            customer.setUsername(rs.getString("username"));
            customer.setContact(rs.getString("contact"));

            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }

        return customer;
    }

    /**
     *
     * @param newValue
     * @param updateColumn
     * @param customerId
     * @throws CustomerDneException
     */
    public void updateCustomerDetails(String newValue, String updateColumn, String customerId) throws CustomerDneException {
        try{
            Connection conn = DBUtils.connect();
            PreparedStatement stmt1 = conn.prepareStatement(UPDATE_CUSTOMER_DETAILS_EMAIL);
            PreparedStatement stmt2 = conn.prepareStatement(UPDATE_CUSTOMER_DETAILS_CONTACT);

            if(updateColumn.equals("email")) {
                stmt1.setString(1, newValue);
                stmt1.setString(2, customerId);
                stmt1.executeUpdate();
                stmt1.close();

            }
            else if(updateColumn.equals("contact")){
                stmt2.setString(1, newValue);
                stmt2.setString(2, customerId);
                stmt2.executeUpdate();
                stmt2.close();
            }
            System.out.println(GREEN_COLOR + "Customer Details with CustomerId " + customerId + " have been successfully updated." + RESET_COLOR);

        } catch (SQLException e) {
            throw new CustomerDneException();
        }
    }

    /**
     *
     * @param username
     * @return
     */
    public String getIdFromName(String username){
        String customerId = "";

        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_USERNAME);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            customerId = rs.getString("customerId");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerId;
    }
}
