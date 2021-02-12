package com.example.ifsc;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    //@Header("Authorization"
    @POST("get-specific-news-api/")
    Call<List<News>> getNews(@Body Tags tag);

    @GET("get-tags-api/")
    Call<List<Category>> getCategories();
}
