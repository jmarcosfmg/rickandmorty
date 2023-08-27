package com.jmarcosfmg.rickandmorty.application.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public final class DateUtils {

    private static Logger logger = Logger.getLogger(DateUtils.class.getName());

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toDate(String date) {
        try{
            return formatter.parse(date);
        } catch (ParseException e){
            logger.info("Failed to parse date "+ date);
            throw new RuntimeException("Could not parse data");
        }
    }
}
