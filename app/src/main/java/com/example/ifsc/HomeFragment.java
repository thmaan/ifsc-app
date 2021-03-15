package com.example.ifsc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    TextView welcomeTv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        welcomeTv = view.findViewById(R.id.welcomeTv);
        welcomeTv.setText("Bem vindo(a) "/*+getArguments().get("username")*/+" ao myIFSC!");
        Fragment cityFragment = new CityFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "Cidade");
        cityFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,
                cityFragment).commit();


        Fragment courseFragment = new CoursesFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("key", "Cursos");
        courseFragment.setArguments(bundle1);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,
                courseFragment).commit();

        Fragment opportunitiesFragment = new OportunitiesFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("key", "Oportunidades");
        opportunitiesFragment.setArguments(bundle2);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment3,
                opportunitiesFragment).commit();

        return view;


    }
}
