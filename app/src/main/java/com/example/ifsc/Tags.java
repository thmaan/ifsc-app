package com.example.ifsc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Tags {

    @SerializedName("tags")
    @Expose
    private String tags;

    public Tags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
