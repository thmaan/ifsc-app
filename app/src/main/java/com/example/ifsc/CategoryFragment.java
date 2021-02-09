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

import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment implements View.OnClickListener {
    private FragmentCommunicator fragmentCommunicator;
    private View view;
    //public ArrayList<Category> categories;
    //public Map<String,String> categoriesMap;
    public HashSet<String> categories;
    CategoryAdapter myAdapter;
    private Api apiConnection;

    public interface FragmentCommunicator {
        void fragmentContactActivity(int a);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        apiConnection = Api.getInstance();

        view = inflater.inflate(R.layout.fragment_categories ,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.categoryList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);

        categories = new HashSet<>();
        getCategories();
        myAdapter = new CategoryAdapter(categories);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickedListener(new CategoryAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int pos, String item) {
                Fragment fragment = new ListFragments();
                Bundle bundle = new Bundle();
                bundle.putString("key", item);
                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                //getActivity().getSupportFragmentManager().beginTransaction().
                        //replace(R.id.fragment_container, new ListFragments()).commit();

            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                      //new ListFragments()).commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void getCategories(){
        Call<List<Category>> call =  apiConnection.getJsonPlaceHolderApi().getCategories();

        int test = apiConnection.hashCode();
        Toast.makeText(getContext(), "" + test, Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code: " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Category> categoriesResponse = response.body();
                for (Category category : categoriesResponse) {
                    for (String tag : category.getTags()) {
                        categories.add(tag);
                    }
                    myAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed bro", Toast.LENGTH_SHORT).show();
            }
        });
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
