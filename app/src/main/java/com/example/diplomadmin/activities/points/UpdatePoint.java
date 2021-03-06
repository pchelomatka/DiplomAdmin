package com.example.diplomadmin.activities.points;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diplomadmin.R;
import com.example.diplomadmin.activities.login.LoginActivity;
import com.example.diplomadmin.interfaces.API;
import com.example.diplomadmin.request_body.RequestUpdatePoint;
import com.example.diplomadmin.response_body.ResponseGetPoints;
import com.example.diplomadmin.response_body.ResponseUpdatePoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdatePoint extends AppCompatActivity implements View.OnClickListener {

    Button buttonUpdatePoint;
    EditText editTextId;
    EditText editTextDeviceId;
    EditText editTextTitle;
    EditText editTextBuildingId;
    TextView textViewGetPoints;
    private static Boolean updatePointStatus = false;
    //    String token;
    public static String pointsForUpdate;
    String building_id = "4"; //да-да хардкод

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_point);

        buttonUpdatePoint = findViewById(R.id.button9);
        buttonUpdatePoint.setOnClickListener(this);
        editTextId = findViewById(R.id.editText3);
        editTextDeviceId = findViewById(R.id.editText7);
        editTextTitle = findViewById(R.id.editText8);
        textViewGetPoints = findViewById(R.id.textView4);
        points(building_id);
    }

    @Override
    public void onClick(View v) {
        String id = editTextId.getText().toString().trim();
        String deviceId = editTextDeviceId.getText().toString().trim();
        String title = editTextTitle.getText().toString().trim();

        if (!id.isEmpty() & !deviceId.isEmpty() & !title.isEmpty()) {
            if (Integer.parseInt(id) > 0) {
                updatePoint(id, deviceId, title, building_id);
            } else {
                Toast.makeText(getApplicationContext(), "Id не могут быть отрицательными", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Данные не введены", Toast.LENGTH_LONG).show();
        }
    }

    private void updatePoint(String id, String deviceId, String title, String buildingId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestUpdatePoint requestUpdatePoint = new RequestUpdatePoint(id, deviceId, title, buildingId);

        API api = retrofit.create(API.class);
        api.updatePoint(LoginActivity.token, requestUpdatePoint);

        Call<ResponseUpdatePoint> call = api.updatePoint(LoginActivity.token, requestUpdatePoint);

        call.enqueue(new Callback<ResponseUpdatePoint>() {
            @Override
            public void onResponse(Call<ResponseUpdatePoint> call, Response<ResponseUpdatePoint> response) {
                if (response.isSuccessful()) {
                    updatePointStatus = true;
                } else {
                    Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdatePoint> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server is down", Toast.LENGTH_LONG).show();
//                Log.i("ERROR", response.body().getStatus().toString());
            }
        });
        if (updatePointStatus == true) {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        }
    }

    private void points(String building_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        api.points(LoginActivity.token, building_id);

        Call<ResponseGetPoints> call = api.points(LoginActivity.token, building_id);

        call.enqueue(new Callback<ResponseGetPoints>() {
            @Override
            public void onResponse(Call<ResponseGetPoints> call, Response<ResponseGetPoints> response) {
                if (response.isSuccessful()) {
                    pointsForUpdate = "";
                    //Log.i("GET POINTS", response.body().getResponse().get(0).getTitle());
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        pointsForUpdate += response.body().getResponse().get(i).getId() + " ";
                        pointsForUpdate += response.body().getResponse().get(i).getDeviceId() + " ";
                        pointsForUpdate += response.body().getResponse().get(i).getTitle() + " ";
                        pointsForUpdate += "\n";
                    }
                    textViewGetPoints.setText(pointsForUpdate.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPoints> call, Throwable t) {

            }
        });
    }
}
