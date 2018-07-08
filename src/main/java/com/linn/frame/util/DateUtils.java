package com.linn.frame.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期格式化
 * @Author:LNN 2018-01-03 下午 3:49
 */
public class DateUtils {
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static  SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMM" );
    /**
     * 字符串日期yyyyMMdd转为日期
     * @return
     */
    public static Date str2Date(String str) throws ParseException {
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    public static Date firstDayByMonth (String str) throws ParseException {

        Date date = str2Date(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();

        return firstDayOfMonth;
    }

    public static Date lastDayByMonth (String str) throws ParseException {
        Date date = firstDayByMonth(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();
        return lastDayOfMonth;
    }
}
