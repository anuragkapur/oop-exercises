package com.imdb.service;

import com.imdb.dao.ActorDAO;
import com.imdb.dao.ActorDAOFactory;
import com.imdb.dao.exception.DBException;
import com.imdb.model.Actor;

import java.util.Date;
import java.util.List;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class ActorDirectoryService {

    private ActorDAO actorDAO = ActorDAOFactory.getDAO();

    public List<Actor> getActorByName(String name) {
        List<Actor> actors = null;

        try {
            actors = actorDAO.getActorByName(name);
        } catch (DBException e) {
            e.printStackTrace();
        }

        return actors;
    }

    public Actor getActorById(String actorId) {
        try {
            return actorDAO.getActorById(actorId);
        } catch (DBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addActorInfo(String name, float height, Date dateOfBirth) {
        Actor actor = new Actor(name, height, dateOfBirth);
        try {
            actorDAO.upsertActor(actor);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void updateActorInfo(Actor actor) {
        try {
            actorDAO.upsertActor(actor);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
