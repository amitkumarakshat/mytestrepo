package com.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPrototypeBean {

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
    private String dateTimeString = dateFormat.format(date);

    public String getDateTime() {
        return dateTimeString;
    }
}