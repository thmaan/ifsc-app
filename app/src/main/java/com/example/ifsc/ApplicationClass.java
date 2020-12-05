package com.example.ifsc;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Category> categories;

    @Override
    public void onCreate() {
        super.onCreate();

        categories = new ArrayList<>();

        Category category = new Category("-1","Categoria","Categoria inicial");
        categories.add(category);
        Category category1 = new Category("-1","Categoria","Categoria inicial");
        categories.add(category1);
        Category category2 = new Category("-1","Categoria","Categoria inicial");
        categories.add(category2);
    }
}
