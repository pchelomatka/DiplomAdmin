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
import com.example.diplomadmin.requestBody.RequestBodyAddPoint;
import com.example.diplomadmin.responseBody.ResponseAddPoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPoint extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddPoint;
    EditText editTextDeviceId;
    EditText editTextTitle;
    EditText editTextBuildingId;
    private static Boolean addPointStatus = false;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point);

        buttonAddPoint = findViewById(R.id.button8);
        buttonAddPoint.setOnClickListener(this);
        editTextDeviceId = findViewById(R.id.editText4);
        editTextTitle = findViewById(R.id.editText5);
        editTextBuildingId = findViewById(R.id.editText6);
    }

    @Override
    public void onClick(View v) {
        String deviceId = editTextDeviceId.getText().toString().trim();
        String title = editTextTitle.getText().toString().trim();
        String buildingId = editTextBuildingId.getText().toString().trim();

        if(!deviceId.isEmpty() & !title.isEmpty() & !buildingId.isEmpty()) {
            addPoint(deviceId, title, buildingId);
        }


    }

    private void addPoint(String deviceId, String title, String buildingId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://10.81.2.251")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestBodyAddPoint requestBodyAddPoint = new RequestBodyAddPoint(deviceId, title, buildingId);

        API api = retrofit.create(API.class);
        api.addPoint(requestBodyAddPoint);

        Call<ResponseAddPoint> call = api.addPoint(requestBodyAddPoint);

        call.enqueue(new Callback<ResponseAddPoint>() {
            @Override
            public void onResponse(Call<ResponseAddPoint> call, Response<ResponseAddPoint> response) {
                if (response.isSuccessful()) {
                    addPointStatus = true;
                    Log.i("ADD POINT", response.body().getResponse());
                } else {
                    Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddPoint> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server is down", Toast.LENGTH_LONG).show();
//                Log.i("ERROR", response.body().getStatus().toString());
            }
        });
        if (addPointStatus == true) {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        }
    }
}
