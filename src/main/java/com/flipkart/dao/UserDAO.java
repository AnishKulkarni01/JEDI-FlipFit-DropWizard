package com.flipkart.dao;

import com.flipkart.exceptions.AuthenticationFailedException;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.flipkart.constants.Constants.*;
import static com.flipkart.constants.SQLConstants.*;

public class UserDAO {
    static UserDAO userDAO = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private String currentUsername;

    /**
     *
     * @return
     */
    public static synchronized UserDAO getInstance() {
        if(userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    /**
     *
     * @param username
     * @param password
     * @param role
     * @return
     */
    public boolean check(String username, String password, String role)throws AuthenticationFailedException {
        boolean isRegisteredUser = true;
        GymOwnerDAO gymOwnerDAO = GymOwnerDAO.getInstance();

        //System.out.println(role + " " + gymOwnerDAO.getIdFromName(username).get(1));

        if(Objects.equals(role, ROLE_GYM_OWNER) && Objects.equals(gymOwnerDAO.getIdFromName(username).get(1), "false")){
            return false;
        }

        try{
            conn = DBUtils.connect();
            stmt = conn.prepareStatement(AUTHENTICATE_USER);

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();
            if(!rs.next()) isRegisteredUser = false;

            stmt.close();
        }catch(SQLException e) {
throw new AuthenticationFailedException();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return isRegisteredUser;
    }

    /**
     *
     * @param username
     * @param password
     * @param role
     */
    public void addUser(String username, String password, String role) {
        try{
            conn = DBUtils.connect();
            stmt = conn.prepareStatement(ADD_USER);

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e)
        {
            System.out.println("Type mismatch");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param username
     */
    public void setCurrentUser(String username){
        this.currentUsername = username;
    }

    /**
     *
     * @return
     */
    public List<String> getCurrentUser() {
        if(currentUsername == null) {
            System.out.println(RED_COLOR + "Please login to the system" + RESET_COLOR);
            return null;
        } else{
            List<String> userDetailList = new ArrayList<>();

            try{
                conn = DBUtils.connect();
                stmt = conn.prepareStatement(GET_USER);

                stmt.setString(1, this.currentUsername);

                ResultSet rs = stmt.executeQuery();
                rs.next();
                String role=rs.getString("role");
                userDetailList.add(rs.getString("username"));
                userDetailList.add(rs.getString("role"));
                if(role.equals("GYM_CUSTOMER")) {
                    stmt = conn.prepareStatement(GET_CUSTOMER_BY_USERNAME);
                }
                else if(role.equals("GYM_OWNER"))
                {
                    stmt = conn.prepareStatement(GET_GYM_OWNER_BY_USERNAME);
                }
                stmt.setString(1, this.currentUsername);
                rs=stmt.executeQuery();
                rs.next();
                userDetailList.add(rs.getString("password"));
                userDetailList.add(rs.getString("contact"));
                userDetailList.add(rs.getString("email"));
            } catch(Exception e){
                e.printStackTrace();;
            }

            return userDetailList;
        }
    }

    /**
     *
     * @param newPassword
     */
    public void updatePassword(String newPassword){
        try {
            conn = DBUtils.connect();
            stmt = conn.prepareStatement(UPDATE_USER_PASSWORD);

            stmt.setString(1, newPassword);
            stmt.setString(2, this.currentUsername);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
