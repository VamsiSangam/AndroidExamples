package com.vamsisangam.androidexamples;

/**
 * Created by Vamsi on 18-05-2017.
 */

public class HomeActivityListItem {
    private String title, description;

    public HomeActivityListItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
