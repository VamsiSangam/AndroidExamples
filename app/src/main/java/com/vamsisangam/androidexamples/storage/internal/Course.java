package com.vamsisangam.androidexamples.storage.internal;

import java.util.HashMap;

/**
 * Created by Vamsi on 04-06-2017.
 */

public class Course {
    private String name, fee, duration;

    public Course(String name, String fee, String duration) {
        this.name = name;
        this.fee = fee;
        this.duration = duration;
    }

    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();

        map.put("name", getName());
        map.put("fee", getFee());
        map.put("duration", getDuration());

        return map;
    }

    @Override
    public String toString() {
        return name + "," + fee + "," + duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return "Rs. " + fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDuration() {
        return duration + " sessions";
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRawDuration() {
        return duration;
    }

    public String getRawFee() {
        return fee;
    }
}
