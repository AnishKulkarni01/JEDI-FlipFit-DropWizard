package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class GymOwnerRegistrationFailedException extends Exception {
    /**
     *
     * @param gymOwnerName
     */
    public GymOwnerRegistrationFailedException(String gymOwnerName) {
        super(RED_COLOR+"Failed to register Gym Customer with name: " + gymOwnerName + RESET_COLOR);
    }
}