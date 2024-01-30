package com.flipkart.service;

import com.flipkart.bean.Booking;

import java.util.List;

public interface BookingServiceInterface {
    List<Booking> getBookingByCustomerId (String customerId);
    boolean addBooking(String customerId, String slotId);
    void deleteBooking(String bookingId);
    boolean canBook(String slotId,String gymId);
}
