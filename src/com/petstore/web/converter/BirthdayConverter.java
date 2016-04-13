package com.petstore.web.converter;

import org.apache.struts2.util.StrutsTypeConverter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

public class BirthdayConverter extends StrutsTypeConverter {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {

        return Timestamp.valueOf(strings[0] + " 00:00:00");
    }

    @Override
    public String convertToString(Map map, Object o) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(o);
    }
}
