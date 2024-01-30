package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class GymDneException extends Exception{
    public GymDneException(){
        super(RED_COLOR+"GymId does not exist."+RESET_COLOR);
    }
}
