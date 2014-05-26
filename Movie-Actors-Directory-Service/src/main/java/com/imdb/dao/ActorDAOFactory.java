package com.imdb.dao;

import com.imdb.dao.impl.FileStoreImpl;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class ActorDAOFactory {

    private static ActorDAO actorDAO = new FileStoreImpl();

    public static ActorDAO getDAO() {
        return actorDAO;
    }
}
