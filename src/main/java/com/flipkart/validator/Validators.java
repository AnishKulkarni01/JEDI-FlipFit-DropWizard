package com.flipkart.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public boolean isValidDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Disable lenient parsing

        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean isFutureDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date inputDate = dateFormat.parse(date);
            Date currentDate = new Date();
            return inputDate.after(currentDate);
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean isTimeValid(String time){
        String regex = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public boolean isEmailValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPhoneValid(String phone) {
        if(phone.length() != 10) return false;
        String regex = "(0|91)?[6-9][0-9]{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}

