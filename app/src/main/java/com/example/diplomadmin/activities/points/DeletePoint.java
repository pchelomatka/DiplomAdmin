package com.example.diplomadmin.activities.points;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diplomadmin.R;
import com.example.diplomadmin.activities.login.LoginActivity;
import com.example.diplomadmin.interfaces.API;
import com.example.diplomadmin.requestBody.RequestDeletePoint;
import com.example.diplomadmin.responseBody.ResponseDeletePoint;
import com.example.diplomadmin.responseBody.ResponseGetPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeletePoint extends AppCompatActivity implements View.OnClickListener {

    Button buttonDeletePoint;
    EditText editTextDeletePoint;
    TextView textViewGetPoints;
    private static Boolean deletePointStatus = false;
//    String token;
    String building_id = "4"; //да-да хардкод
    public static String points;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_point);
        buttonDeletePoint = findViewById(R.id.button10);
        buttonDeletePoint.setOnClickListener(this);
        editTextDeletePoint = findViewById(R.id.editText11);
        textViewGetPoints = findViewById(R.id.textView3);
        points(building_id);

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
                .baseUrl(LoginActivity.baseUrl)
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
                    Toast.makeText(getApplicationContext(), "Точка удалена", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDeletePoint> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server is down", Toast.LENGTH_LONG).show();
            }
        });
        if (deletePointStatus == true) {
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
                    points = "";
                    //Log.i("GET POINTS", response.body().getResponse().get(0).getTitle());
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        points += response.body().getResponse().get(i).getId() + " ";
                        points += response.body().getResponse().get(i).getDeviceId() + " ";
                        points += response.body().getResponse().get(i).getTitle() + " ";
                        points += "\n";
                    }
                    textViewGetPoints.setText(points.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPoints> call, Throwable t) {

            }
        });
    }
}
