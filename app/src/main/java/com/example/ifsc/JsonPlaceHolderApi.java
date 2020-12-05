package com.example.ifsc;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    //@Header("Authorization"
    @GET("create-category-api/")
    Call<List<Category>> getCategorias();

}
