package com.vamsisangam.androidexamples.storage.database;

/**
 * Created by Vamsi on 09-06-2017.
 */

public class Topic {
    String id, course, name, duration;

    public Topic(String id, String course, String name, String duration) {
        this.id = id;
        this.course = course;
        this.name = name;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
