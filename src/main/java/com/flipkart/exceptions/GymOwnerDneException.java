package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class GymOwnerDneException extends Exception{
   public GymOwnerDneException()
   {
       super(RED_COLOR+"GymOwner does not exist"+RESET_COLOR);

   }
}
