package com.example.ifsc;

import java.util.ArrayList;
import java.util.Date;

public class News {
    private String title;
    private ArrayList<String> tags;
    private String description;
    private String slug;
    private String published;

    public News(String title, String description, String published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public News(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public News(String title, ArrayList<String> tags, String description, String slug, String published) {
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.slug = slug;
        this.published = published;
    }
    public News(String slug){
        this.slug= slug;
    }
    public String getTitle() {
        return title;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public String getSlug() {
        return slug;
    }

    public String getPublished() {
        return published;
    }
}
