package com.example.ifsc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment implements View.OnClickListener {
    public HashSet<String> categories;
    CategoryAdapter myAdapter;
    private Api apiConnection;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        apiConnection = Api.getInstance();

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.categoryList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getActivity(),2, LinearLayoutManager.VERTICAL ,false);
        recyclerView.setLayoutManager(layoutManager);

        categories = new HashSet<>();
        getCategories();
        myAdapter = new CategoryAdapter(categories);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickedListener((pos, item) -> {
            Fragment fragment = new ListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", normalizeString(item));
            fragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack("fragment").commit();

        });

        return view;
    }
    public String normalizeString(String string){
        return Normalizer
                .normalize(string, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll("[ \\t\\n\\x0B\\f\\r]","-").toLowerCase();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void getCategories() {
        Call<List<Category>> call = apiConnection.getJsonPlaceHolderApi().getCategories(MainActivity.token);

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NotNull Call<List<Category>> call,@NotNull Response<List<Category>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code: " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Category> categoriesResponse = response.body();
                for (Category category : categoriesResponse) {
                    //NO LUGAR DO FOREACH
                    categories.addAll(category.getTags());
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NotNull Call<List<Category>> call, @NotNull Throwable t) {
                Toast.makeText(getActivity(), "failed bro", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {

    }
}
