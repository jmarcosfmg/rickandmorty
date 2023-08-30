package com.jmarcosfmg.rickandmorty.application.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

public final class DateUtils {
    private static Logger logger = Logger.getLogger(DateUtils.class.getName());
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate toDate(String date) {
        try{
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            logger.info("Failed to parse date "+ date);
            throw new RuntimeException("Could not parse data");
        }
    }
}
