package com.example.diplomadmin.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diplomadmin.R;
import com.example.diplomadmin.activities.menu.Menu;
import com.example.diplomadmin.interfaces.API;
import com.example.diplomadmin.requestBody.RequestBodyAuth;
import com.example.diplomadmin.responseBody.ResponseBodyAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLogin;
    EditText editTextLogin;
    EditText editTextPass;
    public static String token;
    private static Boolean authStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.button);
        buttonLogin.setOnClickListener(this);
        editTextLogin = findViewById(R.id.editText);
        editTextPass = findViewById(R.id.editText2);
    }

    @Override
    public void onClick(View v) {
        String login = editTextLogin.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();

        if (!login.isEmpty() & !password.isEmpty()) {
            login(login, password);
        }
    }

    @Nullable
    private String MD5(String md5) {
        try {
            md5 = "ufo" + md5 + "ufo";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    private void login(String login, String pass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.81.2.251")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestBodyAuth requestBodyAuth = new RequestBodyAuth(login, pass = MD5(pass));

        API api = retrofit.create(API.class);
        api.loginUser(requestBodyAuth);

        Call<ResponseBodyAuth> call = api.loginUser(requestBodyAuth);

        call.enqueue(new Callback<ResponseBodyAuth>() {
            @Override
            public void onResponse(Call<ResponseBodyAuth> call, Response<ResponseBodyAuth> response) {
                if (response.isSuccessful()) {
                    authStatus = true;
                    token = response.body().getResponse().getToken();
                    Log.i("TOKEN", token);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBodyAuth> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server is down", Toast.LENGTH_LONG).show();
            }
        });
        if (authStatus == true) {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
            finish();
        }

    }
}
