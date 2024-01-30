package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class GymAreaDneException extends Exception {
    public GymAreaDneException()
    {
        super(RED_COLOR+"No gyms in the area"+RESET_COLOR);
    }
}
