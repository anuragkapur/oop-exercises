package com.imdb.dao.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class DateHelper {

    public static Date getDate(String dateStr) throws ParseException {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMMM yyyy");
        return simpleDateFormat.parse(dateStr);
    }

    public static String getDateString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMMM yyyy");
        return simpleDateFormat.format(date);
    }
}
