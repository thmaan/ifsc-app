package com.example.ifsc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragments extends Fragment implements View.OnClickListener {
    private Api apiConnection;
    private View view;
    ListFragmentAdapter myAdapter;
    private List<News> newsList;
    String slug;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        apiConnection = Api.getInstance();
        Bundle bundle = this.getArguments();
        slug = bundle.getString("key");


        view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.newsList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        newsList = new ArrayList<>();
        getNews();
        myAdapter = new ListFragmentAdapter(newsList);
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    public void getNews(){
        News n = new News(slug.toLowerCase());

        Call<List<News>> call = apiConnection.getJsonPlaceHolderApi().getNews(n);

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code: " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<News> newsResponse = response.body();
                for (News news : newsResponse) {
                    newsList.add(news);
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
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
