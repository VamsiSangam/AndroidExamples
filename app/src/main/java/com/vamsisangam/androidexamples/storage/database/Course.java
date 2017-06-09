package com.vamsisangam.androidexamples.storage.database;

/**
 * Created by Vamsi on 09-06-2017.
 */

public class Course {
    String id, name, duration, fee;

    public Course(String id, String name, String duration, String fee) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getFee() {
        return fee;
    }
}
