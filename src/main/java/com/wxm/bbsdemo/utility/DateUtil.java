package com.wxm.bbsdemo.utility;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static long ONE_SECOND=1000;
    public static long ONE_MIN=60*1000;
    public static long ONE_HOUR=60*60*1000;
    public static long ONE_DAY=12*60*60*1000;
    public static long ONE_WEEK=7*12*60*60*1000;
    public static final String DATE_TIME_FORMAT_YYYY_M_D_HH_MI_SS = "yyyy-M-d HH:mm:ss";
    public static String dateReform(Date date){
        Date now=new Date();
        long diff=Math.abs(now.getTime()-date.getTime());
        String suffix=diff>0?"前":"后";
        if(now.getTime()-date.getTime()<ONE_MIN) {
            return diff/ONE_SECOND+" 秒"+suffix;
        }else if(now.getTime()-date.getTime()<ONE_HOUR){
            return diff/ONE_MIN+" 分钟"+suffix;
        }
        else if(now.getTime()-date.getTime()<ONE_DAY){
            return diff/ONE_HOUR+" 小时"+suffix;
        }
        else if(now.getTime()-date.getTime()<ONE_WEEK){
            return diff/ONE_DAY+" 天"+suffix;
        }else {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_M_D_HH_MI_SS);
            return simpleDateFormat.format(date);
        }

    }
}
