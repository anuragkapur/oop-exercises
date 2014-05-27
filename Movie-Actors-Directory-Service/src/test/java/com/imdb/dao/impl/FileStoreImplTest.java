package com.imdb.dao.impl;

import com.imdb.dao.exception.DBException;
import com.imdb.model.Actor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class FileStoreImplTest {

    private static final String DB_FILE_NAME = "Movie-Actors-Directory-Service/test-actorsDirectory.db";

    @Test
    public void testGetActorByName() throws Exception {
        FileStoreImpl fileStore = new FileStoreImpl(DB_FILE_NAME);
        List<Actor> actorList = fileStore.getActorByName("Sandra Bullock");
        Actor actor = actorList.get(0);
        Assert.assertEquals("Sandra Bullock", actor.getName());
    }

    @Test
    public void testGetActorById() throws Exception {
        FileStoreImpl fileStore = new FileStoreImpl(DB_FILE_NAME);
        Actor actor = fileStore.getActorById("1005");
        Assert.assertEquals("Jeff Bridges", actor.getName());
        Assert.assertEquals("1005", actor.getActorId());
    }

    @Test
    public void testUpdateActor() throws Exception {
        FileStoreImpl fileStore = new FileStoreImpl(DB_FILE_NAME);
        Actor actor = new Actor("Bla", 1, new Date());
        actor.setActorId(String.valueOf(1004));
        fileStore.upsertActor(actor);

        Actor updatedActor = fileStore.getActorById("1004");
        Assert.assertEquals("Bla", updatedActor.getName());

        actor.setName("Johnny Depp");
        actor.setActorId(String.valueOf(1004));
        fileStore.upsertActor(actor);

        updatedActor = fileStore.getActorById("1004");
        Assert.assertEquals("Johnny Depp", updatedActor.getName());
    }

    @Test
    public void testAddActor() throws Exception {
        FileStoreImpl fileStore = new FileStoreImpl(DB_FILE_NAME);
        Actor actor = new Actor("Bla", 1, new Date());
        fileStore.upsertActor(actor);
    }

    @Test
    public void testUpdateActorMultiThreaded() throws Exception {

        final String uniqueActorName = "actor"+System.currentTimeMillis();
        final FileStoreImpl fileStore = new FileStoreImpl(DB_FILE_NAME);

        new Thread() {
            public void run() {
                Actor actor = new Actor(uniqueActorName, 1.3, new Date());
                try {
                    fileStore.upsertActor(actor);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                Actor actor = new Actor(uniqueActorName, 1.4, new Date());
                try {
                    fileStore.upsertActor(actor);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Assert.assertEquals(2, fileStore.getActorByName(uniqueActorName).size());

        Thread.sleep(3000);
    }
}