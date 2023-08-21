package org.example;

import java.text.SimpleDateFormat;


public class DateFormatter extends SimpleDateFormat{
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public DateFormatter() {
        super(DATE_FORMAT);
        this.setLenient(false);
    }
}

