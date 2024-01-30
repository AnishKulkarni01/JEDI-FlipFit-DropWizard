package com.flipkart.exceptions;

public class SlotFullException extends Exception{
    public SlotFullException()
    {
        super("All seats full");
    }
}
