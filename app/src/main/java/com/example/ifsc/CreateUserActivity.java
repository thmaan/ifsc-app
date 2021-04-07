package com.example.ifsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateUserActivity extends AppCompatActivity {
    private TextView usernameTextView;
    private TextView passwordTextView;
    private Api apiConnection;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getApplication().setTheme(R.style.Theme_IFSC);
        setTheme(R.style.Theme_IFSC);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        apiConnection = Api.getInstance();
        Intent intent = getIntent();
        String token = intent.getStringExtra(LoginActivity.AUTH_TOKEN);
        usernameTextView = findViewById(R.id.add_username);
        passwordTextView = findViewById(R.id.add_password);

        relativeLayout = findViewById(R.id.relativeLayout);
        relativeLayout.setOnClickListener(v -> createUser());

    }
    private void callLogin(){
        Intent intent = new Intent(CreateUserActivity.this, LoginActivity.class);
        intent.putExtra("FROM_CREATE","sim");
        intent.putExtra("NEW_USERNAME",usernameTextView.getText().toString());
        intent.putExtra("NEW_PASSWORD",passwordTextView.getText().toString());
        startActivity(intent);
    }
    private void createUser() {
        String username = usernameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        Login login = new Login(username, password);
        Call<ResponseBody> call = apiConnection.getJsonPlaceHolderApi().createUser(login);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CreateUserActivity.this, "Code: " + response.code() +" "+ response.message(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(CreateUserActivity.this, "Something happened, try again.", Toast.LENGTH_SHORT).show();
                    callLogin();
                    return;
                }
                String content = response.message();
                Toast.makeText(CreateUserActivity.this, content, Toast.LENGTH_SHORT).show();
                callLogin();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CreateUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
