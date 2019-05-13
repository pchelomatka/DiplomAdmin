package com.example.diplomadmin.activities.vectors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diplomadmin.R;
import com.example.diplomadmin.activities.login.LoginActivity;
import com.example.diplomadmin.activities.menu.MenuVector;
import com.example.diplomadmin.interfaces.API;
import com.example.diplomadmin.request_body.RequestAddVector;
import com.example.diplomadmin.response_body.ResponseAddVector;
import com.example.diplomadmin.response_body.ResponseGetPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddVector extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextBuildingId;
    private EditText editTextStartPoint;
    private EditText editTextEndPoint;
    private EditText editTextDistance;
    private EditText editTextDirection;
    private Button addButton;
    public TextView textViewForVector;
    public boolean addVectorStatus = false;
    public static String pointsForVectors;
    String buildingIdHard = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vector);
        editTextBuildingId = findViewById(R.id.editText10);
        editTextStartPoint = findViewById(R.id.editText12);
        editTextEndPoint = findViewById(R.id.editText13);
        editTextDistance = findViewById(R.id.editText14);
        editTextDirection = findViewById(R.id.editText15);
        addButton = findViewById(R.id.button13);
        addButton.setOnClickListener(this);
        textViewForVector = findViewById(R.id.textView14);
        points(buildingIdHard);
    }

    @Override
    public void onClick(View v) {
        String building_id = editTextBuildingId.getText().toString().trim();
        String startPoint = editTextStartPoint.getText().toString().trim();
        String endPoint = editTextEndPoint.getText().toString().trim();
        String distance = editTextDistance.getText().toString().trim();
        String direction = editTextDirection.getText().toString().trim();

        if (!building_id.isEmpty() & !startPoint.isEmpty() & !endPoint.isEmpty() & !distance.isEmpty() & !direction.isEmpty()) {
            if (Integer.parseInt(building_id) > 0 & Integer.parseInt(startPoint) > 0 & Integer.parseInt(endPoint) > 0 & Integer.parseInt(distance) > 0 & Integer.parseInt(direction) > 0) {
                addVector(building_id, startPoint, endPoint, distance, direction);
            } else {
                Toast.makeText(getApplicationContext(), "Данные не могут быть отрицательными", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Данные не введены", Toast.LENGTH_LONG).show();
        }

    }

    private void addVector(String building_id, String startPoint, String endPoint, String
            distance, String direction) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestAddVector requestAddVector = new RequestAddVector(building_id, startPoint, endPoint, distance, direction);

        API api = retrofit.create(API.class);
        api.addVector(LoginActivity.token, requestAddVector);

        Call<ResponseAddVector> call = api.addVector(LoginActivity.token, requestAddVector);

        call.enqueue(new Callback<ResponseAddVector>() {
            @Override
            public void onResponse(Call<ResponseAddVector> call, Response<ResponseAddVector> response) {
                if (response.isSuccessful()) {
                    addVectorStatus = true;
                    Toast.makeText(getApplicationContext(), "Вектор добавлен", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddVector> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
            }
        });
        if (addVectorStatus == true) {
            Intent intent = new Intent(this, MenuVector.class);
            startActivity(intent);
        }
    }

    private void points(String buildingIdHard) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        api.points(LoginActivity.token, buildingIdHard);

        Call<ResponseGetPoints> call = api.points(LoginActivity.token, buildingIdHard);

        call.enqueue(new Callback<ResponseGetPoints>() {
            @Override
            public void onResponse(Call<ResponseGetPoints> call, Response<ResponseGetPoints> response) {
                if (response.isSuccessful()) {
                    pointsForVectors = "";
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        pointsForVectors += response.body().getResponse().get(i).getId() + " ";
                        pointsForVectors += response.body().getResponse().get(i).getDeviceId() + " ";
                        pointsForVectors += response.body().getResponse().get(i).getTitle() + " ";
                        pointsForVectors += "\n";
                    }
                    textViewForVector.setText(pointsForVectors.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPoints> call, Throwable t) {

            }
        });
    }
}
