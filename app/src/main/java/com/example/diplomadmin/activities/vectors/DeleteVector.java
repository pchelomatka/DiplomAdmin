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
import com.example.diplomadmin.request_body.RequestDeleteVector;
import com.example.diplomadmin.response_body.ResponseDeleteVector;
import com.example.diplomadmin.response_body.ResponseGetVectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteVector extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId;
    private Button addButton;
    public TextView textViewForVector;
    public boolean deleteVectorStatus = false;
    public static String vectors;
    String buildingIdHard = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_vector);
        editTextId = findViewById(R.id.editText19);
        addButton = findViewById(R.id.button17);
        textViewForVector = findViewById(R.id.textView26);
        addButton.setOnClickListener(this);
        vectors(buildingIdHard);
    }

    @Override
    public void onClick(View v) {
        String id = editTextId.getText().toString().trim();

        if (!id.isEmpty()) {
            if(Integer.parseInt(id) > 0) {
                deleteVector(id);
            } else {
                Toast.makeText(getApplicationContext(), "Id не может быть отрицательным", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Данные не введены", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteVector(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestDeleteVector requestDeleteVector = new RequestDeleteVector(id);

        API api = retrofit.create(API.class);
        api.deleteVector(LoginActivity.token, requestDeleteVector);

        Call<ResponseDeleteVector> call = api.deleteVector(LoginActivity.token, requestDeleteVector);

        call.enqueue(new Callback<ResponseDeleteVector>() {
            @Override
            public void onResponse(Call<ResponseDeleteVector> call, Response<ResponseDeleteVector> response) {
                if (response.isSuccessful()) {
                    deleteVectorStatus = true;
                    Toast.makeText(getApplicationContext(), "Вектор удалён", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteVector> call, Throwable t) {

            }
        });
        if (deleteVectorStatus == true) {
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
