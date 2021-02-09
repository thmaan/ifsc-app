package com.example.ifsc;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    //@Header("Authorization"
    @POST("get-specific-news-api/")
    Call<List<News>> getNews(@Body News slug);

    @GET("get-tags-api/")
    Call<List<Category>> getCategories();
}
