package com.flipkart.dao;

import com.flipkart.bean.GymOwner;
import com.flipkart.exceptions.GymOwnerDneException;
import com.flipkart.exceptions.GymOwnerRegistrationFailedException;
import com.flipkart.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.Constants.GREEN_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;
import static com.flipkart.constants.SQLConstants.*;

public class GymOwnerDAO {
    private static GymOwnerDAO gymOwnerDAO = null;

    public static synchronized GymOwnerDAO getInstance() {
        if (gymOwnerDAO == null) {
            gymOwnerDAO = new GymOwnerDAO();
        }
        return gymOwnerDAO;
    }

    /**
     *
     * @param name
     * @param password
     * @param email
     * @param contact
     */
    public void registerGymOwner(String name, String password,String email,String contact) throws GymOwnerRegistrationFailedException {
        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_GYM_OWNER);

            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, contact);

            stmt.executeUpdate();
            stmt.close();
            System.out.println(GREEN_COLOR + "Gym Owner Registration Request for " + name + " sent." + RESET_COLOR);

        }catch (SQLException e)
        {
            throw new GymOwnerRegistrationFailedException(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param gymOwnerId
     * @return
     * @throws GymOwnerDneException
     */
    public GymOwner getGymOwner(String gymOwnerId) throws GymOwnerDneException {
        GymOwner gymOwner = new GymOwner();

        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_GYM_OWNER_BY_ID);

            stmt.setString(1, (gymOwnerId));

            ResultSet rs = stmt.executeQuery();
            rs.next();

            gymOwner.setEmail(rs.getString("email"));
            gymOwner.setGymOwnerId(rs.getString("gymOwnerId"));
            gymOwner.setPassword(rs.getString("password"));
            gymOwner.setName(rs.getString("username"));
            gymOwner.setContact(rs.getString("contact"));

            stmt.close();
        }catch (SQLException e)
        {
            throw new GymOwnerDneException();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return gymOwner;
    }

    /**
     *
     * @param newValue
     * @param updateColumn
     * @param gymOwnerId
     * @throws GymOwnerDneException
     */
    public void updateGymOwnerDetails(String newValue, String updateColumn, String gymOwnerId) throws GymOwnerDneException {
        try{
            Connection conn = DBUtils.connect();

            if(updateColumn.equals("email")) {
                PreparedStatement stmt1 = conn.prepareStatement(UPDATE_GYM_OWNER_DETAILS_EMAIL);

                stmt1.setString(1, newValue);
                stmt1.setString(2, gymOwnerId);

                stmt1.executeUpdate();
                stmt1.close();

            } else if(updateColumn.equals("contact")){
                PreparedStatement stmt2 = conn.prepareStatement(UPDATE_GYM_OWNER_DETAILS_CONTACT);

                stmt2.setString(1, newValue);
                stmt2.setString(2, gymOwnerId);

                stmt2.executeUpdate();
                stmt2.close();
            }
            System.out.println(GREEN_COLOR + "Gym Owner Details with GymOwnerId " + gymOwnerId + " have been successfully updated." + RESET_COLOR);

        } catch (SQLException e) {
            throw new GymOwnerDneException();
           // e.printStackTrace();
        }
    }

    /**
     *
     * @param username
     * @return
     */
    public List<String> getIdFromName(String username){
        List<String> gymOwnerDetails = new ArrayList<>();

        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_GYM_OWNER_BY_USERNAME);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            gymOwnerDetails.add(rs.getString("gymOwnerId"));
            gymOwnerDetails.add(rs.getString("isApproved"));

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gymOwnerDetails;
    }

    /**
     *
     * @return
     */
    public List<GymOwner> getPendingGymOwners(){
        List<GymOwner> gymOwnerList = new ArrayList<>();

        try{
            System.out.println("\nFetching gym owner requests..");

            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(FETCH_ALL_PENDING_GYM_OWNERS_QUERY);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                GymOwner gymOwner = new GymOwner();

                gymOwner.setGymOwnerId(rs.getString("gymOwnerId"));
                gymOwner.setName(rs.getString("username"));
                gymOwner.setContact(rs.getString("contact"));
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setPassword("*******");

                gymOwnerList.add(gymOwner);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return gymOwnerList;
    }

    /**
     *
     * @param gymOwnerId
     * @throws GymOwnerDneException
     */
    public void approveGymOwner(String gymOwnerId) throws GymOwnerDneException{
        try{
//            System.out.println("Approving...");
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL_APPROVE_GYM_OWNER_BY_ID_QUERY);

            stmt.setString(1, gymOwnerId);

            stmt.executeUpdate();
            stmt.close();
            System.out.println(GREEN_COLOR + "\nGym Owner Request with GymOwnerId " + gymOwnerId + " has been successfully approved.\n" + RESET_COLOR);
        }catch(SQLException e)
        {
            throw new GymOwnerDneException();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param gymOwnerId
     * @throws GymOwnerDneException
     */
    public void rejectGymOwner(String gymOwnerId) throws GymOwnerDneException{
        try{
//            System.out.println("Rejecting...");
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL_REJECT_GYM_OWNER_BY_ID_QUERY);

            stmt.setString(1, gymOwnerId);

            stmt.executeUpdate();
            stmt.close();
            System.out.println(GREEN_COLOR + "\nGym Owner Request with GymOwnerId " + gymOwnerId + " has been successfully rejected.\n" + RESET_COLOR);
        }
        catch (SQLException e)
        {
            throw new GymOwnerDneException();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}