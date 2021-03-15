package com.example.ifsc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    public static final String AUTH_TOKEN = "com.example.retrofit.AUTH_TOKEN";
    public static final String AUTO_LOGIN = "com.example.retrofit.AUTO_LOGIN";
    private TextView usernameTextView;
    private TextView passwordTextView;
    private TextView registerTextView;
    private RelativeLayout relativeLayout;
    private String token = "";
    private Intent intent;
    private Switch switch1;
    private String username = "";
    private String password = "";
    private boolean switchOnOff;
    private String autoLogin = "autoLogin";
    private String fromCreate ="";
    public static final String SHARED_PREFS = "SHAREDpREFS";
    public static final String USERNAME = "User";
    public static final String PASSWORD = "Password";
    public static final String TOKEN = "token";
    public static final String SWITCH1 = "switch";
    private Api apiConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiConnection = Api.getInstance();
        usernameTextView = findViewById(R.id.usernameTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        switch1 = findViewById(R.id.switch1);
        relativeLayout = findViewById(R.id.relativeLayout);
        registerTextView = findViewById(R.id.register);
        intent = getIntent();
        loadData();
        updateViews();
        autoLogin();

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });
    }
    private void autoLogin() {
        String logout = "nao";
        String create = "nao";

        if (intent.getStringExtra("LOGOUT") == null) {

        } else
            logout = intent.getStringExtra("LOGOUT");

        if (intent.getStringExtra("FROM_CREATE") == null) {

        } else
            create = intent.getStringExtra("FROM_CREATE");

        if (autoLogin.equals("sim") && logout.equals("nao") && create.equals("nao")) {
            login();
            return;
        } else if (logout.equals("sim") && create.equals("nao")) {
            updateViews();
        } else if (create.equals("sim")) {
            try {
                fromCreate = intent.getStringExtra("FROM_CREATE");
                username = intent.getStringExtra("NEW_USERNAME");
                password = intent.getStringExtra("NEW_PASSWORD");
                updateViews();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
    public String getUsername(){
        return usernameTextView.getText().toString();
    }
    public void openMainActivity(){
        login();
    }
    private void login() {
         username = usernameTextView.getText().toString();
         password = passwordTextView.getText().toString();
         Login login = new Login(username, password);
         Call<User> call = apiConnection.getJsonPlaceHolderApi().login(login);

         call.enqueue(new Callback<User>() {
             @Override
             public void onResponse(Call<User> call, Response<User> response) {
                 if (!response.isSuccessful()) {
                     Toast.makeText(LoginActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                     return;
                 }
                 User responseFromServer = response.body();
                 token = "Token " + responseFromServer.getToken();
                 Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                 saveData();
                 intent = new Intent(LoginActivity.this, MainActivity.class);
                 intent.putExtra(AUTH_TOKEN, token);
                 intent.putExtra(USERNAME,username);
                 LoginActivity.this.startActivity(intent);
             }

             @Override
             public void onFailure(Call<User> call, Throwable t) {
                 Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });
    }
    public void saveData(){
        if (switch1.isChecked()){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(USERNAME, usernameTextView.getText().toString());
            editor.putString(PASSWORD, passwordTextView.getText().toString());
            editor.putString(AUTO_LOGIN, "sim");

            editor.putBoolean(SWITCH1,switch1.isChecked());

            editor.apply();

            Toast.makeText(this, "Remember me activated, Data saved", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(USERNAME, "");
            editor.putString(PASSWORD, "");
            editor.putString(TOKEN, "");
            editor.putString(AUTO_LOGIN, "nao");
            editor.putBoolean(SWITCH1,switch1.isChecked());

            editor.apply();
        }
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        username = sharedPreferences.getString(USERNAME,"");
        password = sharedPreferences.getString(PASSWORD,"");
        token = sharedPreferences.getString(TOKEN," ");
        autoLogin = sharedPreferences.getString(AUTO_LOGIN,"sem auto login");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }

    public void updateViews(){
        usernameTextView.setText(username);
        passwordTextView.setText(password);
        switch1.setChecked(switchOnOff);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
    }
}