package com.example.ifsc;

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

import org.jetbrains.annotations.NotNull;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HousingFragment extends Fragment implements View.OnClickListener {
    private Api apiConnection;
    HousingFragmentAdapter myAdapter;
    private List<News> newsList;
    String tagId;
    private String token;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        apiConnection = Api.getInstance();
        Bundle bundle = this.getArguments();
        tagId = bundle.getString("key");

        View view = inflater.inflate(R.layout.fragment_housing, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.housingList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getActivity(),2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        newsList = new ArrayList<>();
        getNews();
        myAdapter = new HousingFragmentAdapter(newsList);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickedListener((pos, item) -> {
            Fragment fragment = new WebViewFragment();
            bundle.putString("url", item);
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment).addToBackStack("fragment").commit();
        });

        return view;
    }
    public String normalizeString(String string){
        return Normalizer
                .normalize(string, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }
    public void getNews(){
        Tags t= new Tags(normalizeString(tagId));
        Call<List<News>> call = apiConnection.getJsonPlaceHolderApi().getNews(MainActivity.token,t);

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NotNull Call<List<News>> call, @NotNull Response<List<News>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code: " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<News> newsResponse = response.body();
                //no lugar do for each
                newsList.addAll(newsResponse);
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(@NotNull Call<List<News>> call, @NotNull Throwable t) {
                Toast.makeText(getActivity(), "failed bro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onClick(View v) {

    }

}
