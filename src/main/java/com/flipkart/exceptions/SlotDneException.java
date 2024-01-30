package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class SlotDneException extends Exception{
    public SlotDneException()
    {
        super(RED_COLOR+"SlotId does not exist"+RESET_COLOR);
    }
}
