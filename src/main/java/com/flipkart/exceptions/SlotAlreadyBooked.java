package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class SlotAlreadyBooked extends Exception{
    public SlotAlreadyBooked()
    {
        super(RED_COLOR+"Slot already booked by you."+RESET_COLOR);
    }
}
