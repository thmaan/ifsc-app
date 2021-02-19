package com.example.ifsc;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    //@Header("Authorization"
    @POST("get-specific-news-api/")
    Call<List<News>> getNews(@Header("Authorization") String tokenHeader, @Body Tags tag);

    @GET("get-tags-api/")
    Call<List<Category>> getCategories(@Header("Authorization") String tokenHeader);

    @POST("api-token-auth/")
    Call<User> login(@Body Login login);

    @POST("create-user-api/")
    Call<ResponseBody> createUser(@Body Login login);
}
