package com.imdb.dao.impl;

import com.imdb.model.Actor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class FileStoreImplTest {

    @Test
    public void testGetActorByName() throws Exception {
        FileStoreImpl fileStore = new FileStoreImpl();
        List<Actor> actorList = fileStore.getActorByName("Sandra Bullock");
        Actor actor = actorList.get(0);
        Assert.assertEquals("Sandra Bullock", actor.getName());
    }

    @Test
    public void testGetActorById() throws Exception {
        FileStoreImpl fileStore = new FileStoreImpl();
        Actor actor = fileStore.getActorById("1005");
        Assert.assertEquals("Jeff Bridges", actor.getName());
        Assert.assertEquals("1005", actor.getActorId());
    }

    @Test
    public void testUpdateActor() throws Exception {
        FileStoreImpl fileStore = new FileStoreImpl();
        Actor actor = new Actor("Bla", 1, new Date());
        actor.setActorId(String.valueOf(1004));
        fileStore.upsertActor(actor);

        Actor updatedActor = fileStore.getActorById("1004");
        Assert.assertEquals("Johnny Depp", updatedActor.getName());
    }

    @Test
    public void testAddActor() throws Exception {
        FileStoreImpl fileStore = new FileStoreImpl();
        Actor actor = new Actor("Bla", 1, new Date());
        fileStore.upsertActor(actor);
    }
}