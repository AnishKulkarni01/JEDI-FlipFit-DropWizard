package com.flipkart.bean;

public class Booking {
    private String customerId;
    private String bookingId;
    private String slotId;

    /**
     *
     * @return
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBookingId() {
        return bookingId;
    }

    /**
     *
     * @param bookingId
     */
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getSlotId() {
        return slotId;
    }

    /**
     *
     * @param slotId
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }
}
