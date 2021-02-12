package com.example.ifsc;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private Api(){
        build();
    }
    private static class SingletonHelper{
        private static final Api INSTANCE = new Api();
    }
    public static Api getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public void build() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myifscapp.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    public JsonPlaceHolderApi getJsonPlaceHolderApi() {
        return jsonPlaceHolderApi;
    }
}
