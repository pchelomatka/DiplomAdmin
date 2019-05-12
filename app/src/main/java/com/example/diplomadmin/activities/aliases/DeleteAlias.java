package com.example.diplomadmin.activities.aliases;

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
import com.example.diplomadmin.activities.menu.MenuAlias;
import com.example.diplomadmin.interfaces.API;
import com.example.diplomadmin.requestBody.RequestAddAlias;
import com.example.diplomadmin.requestBody.RequestDeleteAlias;
import com.example.diplomadmin.responseBody.ResponseAddAlias;
import com.example.diplomadmin.responseBody.ResponseBodyAliases;
import com.example.diplomadmin.responseBody.ResponseDeleteAlias;
import com.example.diplomadmin.responseBody.ResponseGetPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteAlias extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextAliasId;
    private EditText editTextPointIdSearch;
    TextView textViewGetPoints;
    TextView textViewGetAliases;
    private Button deleteAlias;
    private Button searchAlias;
    public static String pointsForAlias;
    String buildingIdHard = "4";
    public static String pointsAliasForDel;
    private static Boolean deleteAliasStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_alias);

        editTextAliasId = findViewById(R.id.editText26);
        editTextPointIdSearch = findViewById(R.id.editText27);
        deleteAlias = findViewById(R.id.button23);
        searchAlias = findViewById(R.id.button24);
        deleteAlias.setOnClickListener(this);
        searchAlias.setOnClickListener(this);
        textViewGetPoints = findViewById(R.id.textView30);
        textViewGetAliases = findViewById(R.id.textView32);
        points(buildingIdHard);
    }


    @Override
    public void onClick(View v) {
        String aliasId = editTextAliasId.getText().toString().trim();
        String pointIdAlias = editTextPointIdSearch.getText().toString().trim();

        switch (v.getId()) {
            case R.id.button23:
                deleteAlias(aliasId);
                break;
            case R.id.button24:
                aliases(pointIdAlias);
                break;
        }
    }

    private void deleteAlias(String aliasId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestDeleteAlias requestDeleteAlias = new RequestDeleteAlias(aliasId);

        API api = retrofit.create(API.class);
        api.deleteAlias(LoginActivity.token, requestDeleteAlias);

        Call<ResponseDeleteAlias> call = api.deleteAlias(LoginActivity.token, requestDeleteAlias);

        call.enqueue(new Callback<ResponseDeleteAlias>() {
            @Override
            public void onResponse(Call<ResponseDeleteAlias> call, Response<ResponseDeleteAlias> response) {
                if (response.isSuccessful()) {
                    deleteAliasStatus = true;
                    Toast.makeText(getApplicationContext(), "Псевдоним удалён", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteAlias> call, Throwable t) {

            }
        });
        if (deleteAliasStatus == true) {
            Intent intent = new Intent(this, MenuAlias.class);
            startActivity(intent);
        }
    }

    private void aliases(String pointIdAlias) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        api.aliases(LoginActivity.token, pointIdAlias);

        Call<ResponseBodyAliases> call = api.aliases(LoginActivity.token, pointIdAlias);

        call.enqueue(new Callback<ResponseBodyAliases>() {
            @Override
            public void onResponse(Call<ResponseBodyAliases> call, Response<ResponseBodyAliases> response) {
                if (response.isSuccessful()) {
                    pointsForAlias = "";
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        pointsForAlias += response.body().getResponse().get(i).getId() + " ";
                        pointsForAlias += response.body().getResponse().get(i).getTitle() + " ";
                        pointsForAlias += response.body().getResponse().get(i).getPointId() + " ";
                        pointsForAlias += "\n";
                    }
                    textViewGetAliases.setText(pointsForAlias.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseBodyAliases> call, Throwable t) {

            }
        });
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
                    pointsAliasForDel = "";
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        pointsAliasForDel += response.body().getResponse().get(i).getId() + " ";
                        pointsAliasForDel += response.body().getResponse().get(i).getDeviceId() + " ";
                        pointsAliasForDel += response.body().getResponse().get(i).getTitle() + " ";
                        pointsAliasForDel += "\n";
                    }
                    textViewGetPoints.setText(pointsAliasForDel.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPoints> call, Throwable t) {

            }
        });
    }
}
