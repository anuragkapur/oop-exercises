package com.imdb.dao.helpers;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class ActorIdGenerator {

    private static int currentLargestId;
    private static int getNextId;

    public static void setCurrentLargestId(int currentLargestId) {
        ActorIdGenerator.currentLargestId = currentLargestId;
    }

    public static int getGetNextId() {
        getNextId = ++currentLargestId;
        return getNextId;
    }
}
