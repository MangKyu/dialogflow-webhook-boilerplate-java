package com.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date createDate(String stringDate) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy년MM월dd일").parse(stringDate);
        } catch (ParseException e) {
            date =  null;
        }
        return date;
    }

    public static Date createNextDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add( Calendar.DATE, 1 );
        return cal.getTime();
    }

}
