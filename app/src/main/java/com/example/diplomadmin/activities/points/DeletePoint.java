package com.example.diplomadmin.activities.points;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diplomadmin.R;
import com.example.diplomadmin.activities.login.LoginActivity;
import com.example.diplomadmin.interfaces.API;
import com.example.diplomadmin.requestBody.RequestDeletePoint;
import com.example.diplomadmin.requestBody.RequestUpdatePoint;
import com.example.diplomadmin.responseBody.ResponseDeletePoint;
import com.example.diplomadmin.responseBody.ResponseUpdatePoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeletePoint extends AppCompatActivity implements View.OnClickListener{

    Button buttonDeletePoint;
    EditText editTextDeletePoint;
    private static Boolean deletePointStatus = false;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_point);

        buttonDeletePoint = findViewById(R.id.button10);
        buttonDeletePoint.setOnClickListener(this);
        editTextDeletePoint = findViewById(R.id.editText11);
    }

    @Override
    public void onClick(View v) {
        String id = editTextDeletePoint.getText().toString().trim();

        if (!id.isEmpty()) {
            deletePoint(id);
        }
    }

    private void deletePoint(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://128.69.250.53")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestDeletePoint requestDeletePoint = new RequestDeletePoint(id);

        API api = retrofit.create(API.class);
        api.deletePoint(LoginActivity.token, requestDeletePoint);

        Call<ResponseDeletePoint> call = api.deletePoint(LoginActivity.token, requestDeletePoint);

        call.enqueue(new Callback<ResponseDeletePoint>() {
            @Override
            public void onResponse(Call<ResponseDeletePoint> call, Response<ResponseDeletePoint> response) {
                if (response.isSuccessful()) {
                    deletePointStatus = true;
                    Log.i("ADD POINT", response.body().getResponse());
                } else {
                    Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDeletePoint> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server is down", Toast.LENGTH_LONG).show();
//                Log.i("ERROR", response.body().getStatus().toString());
            }
        });
        if (deletePointStatus == true) {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        }
    }
}
