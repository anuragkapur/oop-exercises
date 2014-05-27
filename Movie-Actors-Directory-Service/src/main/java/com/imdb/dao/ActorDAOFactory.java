package com.imdb.dao;

import com.imdb.dao.exception.DBException;
import com.imdb.dao.impl.FileStoreImpl;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class ActorDAOFactory {

    private static final String DB_FILE_NAME = "Movie-Actors-Directory-Service/actorsDirectory.db";
    private static ActorDAO actorDAO;

    public static ActorDAO getDAO() {
        if (actorDAO == null) {
            try {
                actorDAO = new FileStoreImpl(DB_FILE_NAME);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
        return actorDAO;
    }
}
