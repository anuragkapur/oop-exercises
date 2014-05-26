package com.imdb.dao.exception;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class DBException extends Exception {

    public DBException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DBException(final String message) {
        super(message);
    }
}
