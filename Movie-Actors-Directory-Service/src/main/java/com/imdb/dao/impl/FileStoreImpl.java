package com.imdb.dao.impl;

import com.imdb.dao.ActorDAO;
import com.imdb.dao.exception.DBException;
import com.imdb.dao.helpers.ActorIdGenerator;
import com.imdb.dao.helpers.DateHelper;
import com.imdb.model.Actor;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class FileStoreImpl implements ActorDAO {

    private static List<Actor> actors = null;
    private String fileName;

    public FileStoreImpl(String fileName) throws DBException {
        this.fileName = fileName;
    }

    @Override
    public synchronized List<Actor> getActorByName(String name) throws DBException {

        if (actors == null) {
            actors = getActorsFromFile();
        }

        List<Actor> actorList = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor.getName().equalsIgnoreCase(name)) {
                actorList.add(actor);
            }
        }
        return actorList;
    }

    @Override
    public synchronized Actor getActorById(String actorId) throws DBException {

        if (actors == null) {
            actors = getActorsFromFile();
        }

        for (Actor actor : actors) {
            if (actor.getActorId().equals(actorId)) {
                return actor;
            }
        }
        return null;
    }

    @Override
    public synchronized void upsertActor(Actor actor) throws DBException {

        System.out.println("upsert called");

        if (actors == null) {
            actors = getActorsFromFile();
        }

        System.out.println("last actor height :: " + actors.get(actors.size()-1).getHeight());

        if (actor.getActorId() == null) {
            int nextActorId = ActorIdGenerator.getGetNextId();
            actor.setActorId(String.valueOf(nextActorId));
            actors.add(actor);
        } else {
            actors.remove(actor);
            actors.add(actor);
        }

        updateActorsFile(actors);
    }

    private void updateActorsFile(List<Actor> actors) throws DBException {

        FileWriter writer = null;

        try {
            writer = new FileWriter(this.fileName);
            for (Actor actor : actors) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(actor.getName()).append(",");
                stringBuilder.append(actor.getActorId()).append(",");
                stringBuilder.append(actor.getHeight()).append(",");
                stringBuilder.append(DateHelper.getDateString(actor.getDateOfBirth())).append("\n");
                writer.write(stringBuilder.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // refresh in memory store
        FileStoreImpl.actors = getActorsFromFile();
    }

    private List<Actor> getActorsFromFile() throws DBException {

        List<Actor> actors = new ArrayList<>();
        int currentLargestId = -1;

        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(this.fileName), StandardCharsets.UTF_8);
            String strLine;
            while ((strLine = reader.readLine()) != null) {
                String actorRecordElements[] = strLine.split(",");
                if (actorRecordElements.length != 4) {
                    throw new DBException("Corrupt record found in DB");
                }
                String actorName = actorRecordElements[0];
                String height = actorRecordElements[2];
                String dateOfBirth = actorRecordElements[3];

                Actor actor = new Actor(actorName, Float.valueOf(height), DateHelper.getDate(dateOfBirth));
                actor.setActorId(actorRecordElements[1]);

                actors.add(actor);

                // id generation
                int actorId = Integer.valueOf(actorRecordElements[1]);
                if (actorId > currentLargestId) {
                    currentLargestId = actorId;
                }
            }

            ActorIdGenerator.setCurrentLargestId(currentLargestId);

        } catch (IOException e) {
            throw new DBException("Error while reading DB file", e);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return actors;
    }
}
