package com.itheima.ssm.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils implements Serializable {
    public static String date2String(Date date, String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    public static Date string2Date(String str, String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }


}
