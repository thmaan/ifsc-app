package com.example.ifsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String AUTH_TOKEN = "com.example.ifsc.AUTH_TOKEN";
    public static String token = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Intent intent = getIntent();
        token = intent.getStringExtra(LoginActivity.AUTH_TOKEN);
        Bundle bundle = new Bundle();
        bundle.putString("token",token);
        Fragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new CategoryFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        break;
                    case R.id.nav_category:
                        selectedFragment = new CategoriasFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        break;
                    case R.id.nav_logout:
                        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                        intent1.putExtra("LOGOUT","sim");
                        startActivity(intent1);
                        break;
                    case R.id.nav_map:
                        selectedFragment = new MapFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        break;
                }
                //removeFragments();
                return true;
            };
}