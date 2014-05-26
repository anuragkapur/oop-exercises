package com.imdb.model;

import java.util.Date;

/**
 * @author: anuragkapur
 * @since: 18/05/2014
 */

public class Actor {

    private String name;
    private String actorId;
    private double height;
    private Date dateOfBirth;

    public Actor(String name, float height, Date dateOfBirth) {
        this.name = name;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        return actorId.equals(actor.actorId);

    }

    @Override
    public int hashCode() {
        return actorId.hashCode();
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", actorId='" + actorId + '\'' +
                ", height=" + height +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
