package com.example.ifsc;

import java.util.ArrayList;

public class Category {
   private ArrayList<String> tags;

    public Category(ArrayList<String> categories) {
        this.tags = categories;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
