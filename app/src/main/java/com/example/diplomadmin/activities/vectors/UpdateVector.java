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
import com.example.diplomadmin.requestBody.RequestUpdateVector;
import com.example.diplomadmin.responseBody.ResponseAddVector;
import com.example.diplomadmin.responseBody.ResponseGetPoints;
import com.example.diplomadmin.responseBody.ResponseGetVectors;
import com.example.diplomadmin.responseBody.ResponseUpdateVector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateVector extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextId;
    private EditText editTextBuildingId;
    private EditText editTextStartPoint;
    private EditText editTextEndPoint;
    private EditText editTextDistance;
    private EditText editTextDirection;
    private Button addButton;
    public TextView textViewForVector;
    public boolean updateVectorStatus = false;
    public static String vectors;
    String buildingIdHard = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vector);

        editTextId = findViewById(R.id.editText16);
        editTextBuildingId = findViewById(R.id.editText17);
        editTextStartPoint = findViewById(R.id.editText18);
        editTextEndPoint = findViewById(R.id.editText20);
        editTextDistance = findViewById(R.id.editText21);
        editTextDirection = findViewById(R.id.editText22);
        addButton = findViewById(R.id.button15);
        addButton.setOnClickListener(this);
        textViewForVector = findViewById(R.id.textView23);
        vectors(buildingIdHard);
    }

    @Override
    public void onClick(View v) {
        String id = editTextId.getText().toString().trim();
        String building_id = editTextBuildingId.getText().toString().trim();
        String startPoint = editTextStartPoint.getText().toString().trim();
        String endPoint = editTextEndPoint.getText().toString().trim();
        String distance = editTextDistance.getText().toString().trim();
        String direction = editTextDirection.getText().toString().trim();
        updateVector(id, building_id, startPoint, endPoint, distance, direction);
    }

    private void updateVector(String id, String building_id, String startPoint, String endPoint, String distance, String direction) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestUpdateVector requestUpdateVector = new RequestUpdateVector(id, building_id, startPoint, endPoint, distance, direction);

        API api = retrofit.create(API.class);
        api.updateVector(LoginActivity.token, requestUpdateVector);

        Call<ResponseUpdateVector> call = api.updateVector(LoginActivity.token, requestUpdateVector);

        call.enqueue(new Callback<ResponseUpdateVector>() {
            @Override
            public void onResponse(Call<ResponseUpdateVector> call, Response<ResponseUpdateVector> response) {
                if (response.isSuccessful()) {
                    updateVectorStatus = true;
                    Toast.makeText(getApplicationContext(), "Вектор обновлён", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateVector> call, Throwable t) {

            }
        });
        if (updateVectorStatus == true) {
            Intent intent = new Intent(this, MenuVector.class);
            startActivity(intent);
        }
    }

    private void vectors(String buildingIdHard) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        api.vectors(LoginActivity.token, buildingIdHard);

        Call<ResponseGetVectors> call = api.vectors(LoginActivity.token, buildingIdHard);

        call.enqueue(new Callback<ResponseGetVectors>() {
            @Override
            public void onResponse(Call<ResponseGetVectors> call, Response<ResponseGetVectors> response) {
                if (response.isSuccessful()) {
                    vectors = "";
                    //Log.i("GET POINTS", response.body().getResponse().get(0).getTitle());
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        vectors += response.body().getResponse().get(i).getId() + " ";
                        vectors += response.body().getResponse().get(i).getBuildingId() + " ";
                        vectors += response.body().getResponse().get(i).getStartPoint() + " ";
                        vectors += response.body().getResponse().get(i).getEndPoint() + " ";
                        vectors += response.body().getResponse().get(i).getDistance() + " ";
                        vectors += response.body().getResponse().get(i).getDirection() + " ";
                        vectors += "\n";
                    }
                    textViewForVector.setText(vectors.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseGetVectors> call, Throwable t) {

            }
        });
    }


}
