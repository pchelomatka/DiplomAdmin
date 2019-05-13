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
import com.example.diplomadmin.request_body.RequestAddAlias;
import com.example.diplomadmin.response_body.ResponseAddAlias;
import com.example.diplomadmin.response_body.ResponseBodyAliases;
import com.example.diplomadmin.response_body.ResponseGetPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddAlias extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextPointId;
    private EditText editTextTitleAlias;
    private EditText editTextPointIdSearch;
    TextView textViewGetPoints;
    TextView textViewGetAliases;
    private Button addAlias;
    private Button searchAlias;
    public static String pointsForAliases;
    String buildingIdHard = "4";
    public static String pointsAlias;
    private static Boolean addAliasStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alias);

        editTextPointId = findViewById(R.id.editText23);
        editTextTitleAlias = findViewById(R.id.editText24);
        editTextPointIdSearch = findViewById(R.id.editText25);
        addAlias = findViewById(R.id.button21);
        searchAlias = findViewById(R.id.button22);
        addAlias.setOnClickListener(this);
        searchAlias.setOnClickListener(this);
        textViewGetPoints = findViewById(R.id.textView29);
        textViewGetAliases = findViewById(R.id.textView28);
        points(buildingIdHard);
    }

    @Override
    public void onClick(View v) {
        String pointId = editTextPointId.getText().toString().trim();
        String title = editTextTitleAlias.getText().toString().trim();
        String pointIdAlias = editTextPointIdSearch.getText().toString().trim();


        switch (v.getId()) {
            case R.id.button21:
                if (!pointId.equals("") && !title.equals("")) {
                    if (Integer.parseInt(pointId) > 0) {
                        addAlias(pointId, title);
                        break;
                    } else {
                        Toast.makeText(getApplicationContext(), "Id точки не может быть отрицательным", Toast.LENGTH_LONG).show();
                        break;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Данные не введены", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.button22:
                if (!pointIdAlias.equals("")) {
                    if (Integer.parseInt(pointIdAlias) > 0) {
                        aliases(pointIdAlias);
                        break;
                    } else {
                        Toast.makeText(getApplicationContext(), "Id точки не может быть отрицательным", Toast.LENGTH_LONG).show();
                        break;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Данные не введены", Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }


    private void addAlias(String pointId, String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestAddAlias requestAddAlias = new RequestAddAlias(pointId, title);

        API api = retrofit.create(API.class);
        api.addAlias(LoginActivity.token, requestAddAlias);

        Call<ResponseAddAlias> call = api.addAlias(LoginActivity.token, requestAddAlias);

        call.enqueue(new Callback<ResponseAddAlias>() {
            @Override
            public void onResponse(Call<ResponseAddAlias> call, Response<ResponseAddAlias> response) {
                if (response.isSuccessful()) {
                    addAliasStatus = true;
                    Toast.makeText(getApplicationContext(), "Псевдоним добавлен", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAddAlias> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
            }
        });
        if (addAliasStatus == true) {
            Intent intent = new Intent(this, MenuAlias.class);
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
                    pointsAlias = "";
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        pointsAlias += response.body().getResponse().get(i).getId() + " ";
                        pointsAlias += response.body().getResponse().get(i).getDeviceId() + " ";
                        pointsAlias += response.body().getResponse().get(i).getTitle() + " ";
                        pointsAlias += "\n";
                    }
                    textViewGetPoints.setText(pointsAlias.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPoints> call, Throwable t) {

            }
        });
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
                    pointsForAliases = "";
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        pointsForAliases += response.body().getResponse().get(i).getId() + " ";
                        pointsForAliases += response.body().getResponse().get(i).getTitle() + " ";
                        pointsForAliases += response.body().getResponse().get(i).getPointId() + " ";
                        pointsForAliases += "\n";
                    }
                    textViewGetAliases.setText(pointsForAliases.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseBodyAliases> call, Throwable t) {

            }
        });
    }
}
