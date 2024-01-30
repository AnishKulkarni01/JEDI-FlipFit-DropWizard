package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.exceptions.BookingDneException;
import com.flipkart.exceptions.CustomerDneException;
import com.flipkart.exceptions.SlotDneException;
import com.flipkart.utils.DBUtils;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.flipkart.constants.Constants.*;
import static com.flipkart.constants.SQLConstants.*;

public class BookingDAO {
    static BookingDAO bookingDAO = null;
    GymDAO gymDao= GymDAO.getInstance();

    public static synchronized BookingDAO getInstance() {
        if(bookingDAO ==null) {
            bookingDAO =new BookingDAO();
        }

        return bookingDAO;
    }

    /**
     *
     * @param customerId
     * @param slotId
     * @throws SlotDneException
     */
    public void addBooking(String customerId,String slotId) throws SlotDneException {
        try{
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_BOOKING);

            stmt.setString(1,customerId);
            stmt.setString(2,slotId);

            stmt.executeUpdate();
            stmt.close();
            System.out.println(GREEN_COLOR + "Booking has been successfully added." + RESET_COLOR);

        } catch(SQLException e) {
            throw new SlotDneException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param customerId
     * @return
     * @throws CustomerDneException
     */
    public List<Booking> getBookingbyCustId(String customerId) throws CustomerDneException {
        List<Booking> bookingList = new ArrayList<>();

        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_BOOKING_BY_CUSTOMER_ID);

            stmt.setString(1, customerId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Booking booking = new Booking();

                booking.setBookingId(rs.getString("bookingId"));
                booking.setCustomerId(rs.getString("customerId"));
                booking.setSlotId(rs.getString("slotId"));

                bookingList.add(booking);
            }
        } catch(SQLException e) {
            throw new CustomerDneException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    /**
     *
     * @param bookingId
     * @throws BookingDneException
     */
    public void deleteBookingId(String bookingId) throws BookingDneException {
        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(CANCEL_BOOKING_BY_ID);

            stmt.setString(1, bookingId);

            stmt.executeUpdate();
            System.out.println(GREEN_COLOR + "Booking has been deleted." + RESET_COLOR);
        } catch(SQLException e) {
            throw new BookingDneException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean canBook(String slotId,String gymId)
    {
        try {
            Connection conn = DBUtils.connect();
            PreparedStatement stmt = conn.prepareStatement(COUNT_BOOKINGS_BY_SLOTID);

            stmt.setString(1, slotId);
            ResultSet rs= stmt.executeQuery();
            int numBookedSeats=0;
            while(rs.next()) {
                numBookedSeats=Integer.parseInt(rs.getString("NumBookedSeats"));
                break;
            }
            int totalSeats=gymDao.getGymById(gymId).getSeats();
            if(numBookedSeats>=totalSeats) return false;
            return true;

        } catch(SQLException e)
        {
            System.out.println(RED_COLOR+"Slot already full"+RESET_COLOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}