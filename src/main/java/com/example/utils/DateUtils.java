package com.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date createDate(String date) {
        try {
            new SimpleDateFormat("yyyy년MM월dd일").parse(date);
        } catch (ParseException e) {
            return null;
        }
        return null;
    }

    public static Date createNextDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add( Calendar.DATE, 1 );
        return cal.getTime();
    }

}
