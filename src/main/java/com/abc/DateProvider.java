package com.abc;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    @Test
    public void output(){
    	System.out.println(Calendar.getInstance().getTime());
    	Calendar lowerLimit = Calendar.getInstance();
    	lowerLimit.add(Calendar.DATE, -7);
    	System.out.println(lowerLimit.getTime());
    }
    
}
