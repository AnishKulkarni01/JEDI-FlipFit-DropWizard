package com.flipkart.bean;

public class Slot {
    private String gymId;
    private String slotId;
    private String date;
    private String startTime;
    private boolean availabilityStatus = true;

    public String getGymId() {
        return gymId;
    }

    /**
     *
     * @param gymId
     */
    public void setGymId(String gymId) {
        this.gymId = gymId;
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

    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    /**
     *
     * @param availabilityStatus
     */
    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

}
