package com.example.ifsc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

public class GuideFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fragment ingressoFragment = new IngressoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "Sobre o IF");
        ingressoFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ingressoFragment,
                ingressoFragment).commit();

        Fragment housingFragment = new HousingFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("key", "Moradia");
        housingFragment.setArguments(bundle1);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.housingFragment,
                housingFragment).commit();

        Fragment auxiliosFragment = new AuxiliosFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("key", "Auxilios");
        auxiliosFragment.setArguments(bundle2);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.auxiliosFragment,
                auxiliosFragment).commit();

        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        return view;
    }
}
