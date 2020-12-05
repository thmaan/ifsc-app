package com.example.ifsc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryFragment extends Fragment implements View.OnClickListener {
    private FragmentCommunicator fragmentCommunicator;
    private View view;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    public ArrayList<Category> categories;

    public interface FragmentCommunicator {
        void fragmentContactActivity(int a);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categories ,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categories = new ArrayList<>();

        api();
        getCategories();
    }
    public void show(){
        RecyclerView recyclerView;
        RecyclerView.Adapter myAdapter;
        RecyclerView.LayoutManager layoutManager;

        recyclerView = view.findViewById(R.id.categoryList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this.getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new CategoryAdapter(categories);
        recyclerView.setAdapter(myAdapter);
    }
    public void getCategories(){
        Call<List<Category>> call = jsonPlaceHolderApi.getCategorias();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code: " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Category> categoriesResponse = response.body();
                for (Category category: categoriesResponse){
                    categories.add(new Category(category.getId(),category.getName(),category.getDescription()));
                }
                show();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed bro", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void api(){
        //Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://twomoods.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }
    @Override
    public void onAttach(@NonNull Context activity) {
        super.onAttach(activity);
        try {
            fragmentCommunicator = (FragmentCommunicator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentCommunicator");
        }
    }
    @Override
    public void onClick(View v) {
    }
}
