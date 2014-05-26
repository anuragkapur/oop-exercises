package com.imdb.dao.helpers;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DateHelperTest {

    @Test
    public void testGetDate() throws Exception {
        String dateStr = "26 July 1964";
        Date date = DateHelper.getDate(dateStr);
        Assert.assertEquals("Sun Jul 26 00:00:00 BST 1964", date.toString());
    }
}