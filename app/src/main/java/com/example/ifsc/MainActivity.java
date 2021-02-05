package com.example.ifsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements CategoryFragment.FragmentCommunicator {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Api apiConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CategoryFragment()).commit();

    }

    private void removeFragments() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container1) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container1))
                    .commit();
        }
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container2) != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container2))
                    .commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new CategoryFragment();
                        break;
                    case R.id.nav_category:
                        selectedFragment = new CategoriasFragment();
                        break;
                    case R.id.nav_info:
                        selectedFragment = new InfoFragment();
                        break;
                    case R.id.nav_map:
                        selectedFragment = new MapFragment();
                        break;
                }
                removeFragments();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            };

    @Override
    public void fragmentContactActivity(int a) {

    }
}