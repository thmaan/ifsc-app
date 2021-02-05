package com.example.ifsc;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Category> categories;

    @Override
    public void onCreate() {
        super.onCreate();

        categories = new ArrayList<>();
    }
}
