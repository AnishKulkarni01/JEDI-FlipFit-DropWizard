
package com.flipkart.dao;

import com.flipkart.bean.Gym;
import com.flipkart.bean.Slot;
import com.flipkart.exceptions.CustomerDneException;
import com.flipkart.exceptions.GymDneException;
import com.flipkart.exceptions.SlotDneException;
import com.flipkart.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.flipkart.constants.Constants.*;
import static com.flipkart.constants.SQLConstants.*;

public class SlotDAO {
    static SlotDAO slotDAO = null;

    /**
     *
     * @return
     */
    public static synchronized SlotDAO getInstance() {
        if(slotDAO == null) {
            slotDAO = new SlotDAO();
        }
        return slotDAO;
    }

    /**
     *
     * @param date
     * @param startTime
     * @param gymId
     * @throws GymDneException
     */
    public void createSlot(String date, String startTime, String gymId) throws GymDneException {
        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_SLOT);

            stmt.setString(1, gymId);
            stmt.setString(2, date);
            stmt.setString(3, startTime);

            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e)
        {
            throw new GymDneException();
            //System.out.println("GymId does not exist");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param gymId
     * @return
     * @throws GymDneException
     */
    public List<Slot> getSlotsByGymId(String gymId) throws GymDneException {
        List<Slot> slotList = new ArrayList<>();

        try {
            Connection conn = DBUtils.connect();
            PreparedStatement ps = conn.prepareStatement(FETCH_SLOT_BY_GYMID);

            ps.setString(1, gymId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slot slt = new Slot();

                slt.setGymId(gymId);
                slt.setSlotId(rs.getString("slotId"));
                slt.setDate(rs.getString("date"));
                slt.setStartTime(rs.getString("startTime"));

                slotList.add(slt);
            }
        } catch (SQLException e) {
            throw new GymDneException();

            //System.out.println("GymId does not exist.");
            //throw new RuntimeException(e);
        }

        return slotList;
    }

    /**
     *
     * @param customerId
     * @return
     * @throws CustomerDneException
     */
    public Set<String> getSlotsByCustomerId(String customerId) throws CustomerDneException {
        Set<String> slotIdList = new HashSet<>();

        try {
            Connection conn = DBUtils.connect();
            PreparedStatement ps = conn.prepareStatement(GET_BOOKING_BY_CUSTOMER_ID);

            ps.setString(1, customerId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                slotIdList.add(rs.getString("slotId"));
            }
        } catch (SQLException e) {
            throw new CustomerDneException();
            //System.out.println("No bookings found for the above CustomerId");
            //throw new RuntimeException(e);
        }

        return slotIdList;
    }

    /**
     *
     * @param slotId
     * @return
     * @throws SlotDneException
     */
    public Slot getSlotBySlotId(String slotId) throws SlotDneException {
        Slot slot = new Slot();
        try{
            Connection conn = DBUtils.connect();
            PreparedStatement ps = conn.prepareStatement(FETCH_SLOT_BY_ID);

            ps.setString(1, slotId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                boolean stat = rs.getString("availabilityStatus").equals("true");

                slot.setDate(rs.getString("date"));
                slot.setStartTime(rs.getString("startTime"));
                slot.setGymId(rs.getString("gymId"));
                slot.setAvailabilityStatus(Boolean.parseBoolean(rs.getString("availabilityStatus")));

                return slot;
            }

        } catch(SQLException e)
        {
            throw new SlotDneException();
            //System.out.println("SlotId does not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return slot;
    }

    /**
     *
     * @param updatedVal
     * @param attr
     * @param slotId
     * @throws SlotDneException
     */
    public void updateSlot(String updatedVal, String attr, String slotId) throws SlotDneException{
        try{
            Connection conn = DBUtils.connect();

            if(attr.equals("date")) {
                PreparedStatement stmt1 = conn.prepareStatement(UPDATE_SLOT_DETAILS_DATE);

                stmt1.setString(1, updatedVal);
                stmt1.setString(2, slotId);
                stmt1.executeUpdate();
                stmt1.close();

            }
            else if(attr.equals("startTime")){
                PreparedStatement stmt2 = conn.prepareStatement(UPDATE_SLOT_DETAILS_STARTTIME);
                stmt2.setString(1, updatedVal);
                stmt2.setString(2, slotId);
                stmt2.executeUpdate();
                stmt2.close();
            }
        }catch (SQLException e)
        {
            System.out.println("SlotId does not exist");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param slotId
     * @throws SlotDneException
     */
    public void deleteSlotById(String slotId) throws SlotDneException
    {
        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(DELETE_SLOT_BY_ID);
            stmt.setString(1, slotId);
            stmt.executeUpdate();
            System.out.println(GREEN_COLOR + "Slot Deleted Successfully." + RESET_COLOR);
        }catch(SQLIntegrityConstraintViolationException e) {
            System.out.println(YELLOW_COLOR + "Slot already booked by Customers" +  RESET_COLOR);
            System.out.println(RED_COLOR + "Delete Failed" + RESET_COLOR);
        }
        catch(SQLException e)
        {
            throw new SlotDneException();
            //System.out.println("SlotId does not exist.");
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println(RED_COLOR + "Oops! An error occurred. Try again later." + RESET_COLOR);
        }
    }
}
