package com.flipkart.service.impl;

import com.flipkart.bean.Booking;
import com.flipkart.dao.BookingDAO;
import com.flipkart.exceptions.BookingDneException;
import com.flipkart.exceptions.CustomerDneException;
import com.flipkart.exceptions.SlotDneException;
import com.flipkart.service.BookingServiceInterface;

import java.util.List;

public class BookingServiceImpl implements BookingServiceInterface {
    BookingDAO bookingDAO = BookingDAO.getInstance();

    public List<Booking> getBookingByCustomerId (String customerId){
        try {
            return bookingDAO.getBookingbyCustId(customerId);
        } catch (CustomerDneException e) {
            System.out.println(e.getMessage());        }
        return null;
    }

    public void addBooking(String customerId, String slotId){
        try {
            bookingDAO.addBooking(customerId, slotId);
        } catch (SlotDneException e) {
            System.out.println(e.getMessage());        }
    }

    public void deleteBooking(String bookingId){
        try {
            bookingDAO.deleteBookingId(bookingId);
        } catch (BookingDneException e) {
            System.out.println(e.getMessage());  }
    }
    public boolean canBook(String slotId,String gymId)
    {
        return bookingDAO.canBook(slotId, gymId);
    }
}