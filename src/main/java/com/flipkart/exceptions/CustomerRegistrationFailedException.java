package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class CustomerRegistrationFailedException extends Exception {
    /**
     *
     * @param customerName
     */
    public CustomerRegistrationFailedException(String customerName) {
        super(RED_COLOR+"Failed to register Gym Customer with name: " + customerName + RESET_COLOR);
    }
}