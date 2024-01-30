package com.flipkart.exceptions;

import com.flipkart.bean.Customer;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class CustomerDneException extends  Exception{
    public CustomerDneException()
    {
        super(RED_COLOR+"CustomerId does not exist"+RESET_COLOR);

    }
}
