package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class BookingDneException extends Exception{
    public BookingDneException()
    {
        super(RED_COLOR+"Booking does not exist"+RESET_COLOR);

    }
}