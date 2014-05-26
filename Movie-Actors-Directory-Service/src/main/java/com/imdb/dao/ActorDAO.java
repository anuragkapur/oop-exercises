package com.imdb.dao;

import com.imdb.dao.exception.DBException;
import com.imdb.model.Actor;

import java.util.List;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */
public interface ActorDAO {

    public List<Actor> getActorByName(String name) throws DBException;
    public Actor getActorById(String actorId) throws DBException;
    public void upsertActor(Actor actor) throws DBException;
}
