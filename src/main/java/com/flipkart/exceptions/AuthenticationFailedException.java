package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

public class AuthenticationFailedException extends Exception{
   public AuthenticationFailedException(){ super(RED_COLOR+"Authentication failed."+RESET_COLOR);}
}
