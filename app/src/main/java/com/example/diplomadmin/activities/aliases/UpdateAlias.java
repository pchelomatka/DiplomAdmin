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
import com.example.diplomadmin.request_body.RequestUpdateAlias;
import com.example.diplomadmin.response_body.ResponseBodyAliases;
import com.example.diplomadmin.response_body.ResponseGetPoints;
import com.example.diplomadmin.response_body.ResponseUpdateAlias;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateAlias extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextAliasId;
    private EditText editTextTitleAlias;
    private EditText editTextPointIdSearch;
    TextView textViewGetPoints;
    TextView textViewGetAliases;
    private Button updateAlias;
    private Button searchAlias;
    public static String pointsForAliaseses;
    String buildingIdHard = "4";
    public static String pointsAliasForUp;
    private static Boolean updateAliasStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_alias);

        editTextAliasId = findViewById(R.id.editText28);
        editTextTitleAlias = findViewById(R.id.editText29);
        editTextPointIdSearch = findViewById(R.id.editText30);
        updateAlias = findViewById(R.id.button25);
        searchAlias = findViewById(R.id.button26);
        updateAlias.setOnClickListener(this);
        searchAlias.setOnClickListener(this);
        textViewGetPoints = findViewById(R.id.textView36);
        textViewGetAliases = findViewById(R.id.textView39);
        points(buildingIdHard);
    }

    @Override
    public void onClick(View v) {
        String aliasId = editTextAliasId.getText().toString().trim();
        String title = editTextTitleAlias.getText().toString().trim();
        String pointIdAlias = editTextPointIdSearch.getText().toString().trim();

        switch (v.getId()) {
            case R.id.button25:
                if (!aliasId.equals("") & !title.equals("")) {
                    if(Integer.parseInt(aliasId) >0) {
                        updateAlias(aliasId, title);
                        break;
                    }else {
                        Toast.makeText(getApplicationContext(), "Id псевдонима не может быть отрицательным", Toast.LENGTH_LONG).show();
                        break;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Данные не введены", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.button26:
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

    private void updateAlias(String aliasId, String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestUpdateAlias requestUpdateAlias = new RequestUpdateAlias(aliasId, title);

        API api = retrofit.create(API.class);
        api.updateAlias(LoginActivity.token, requestUpdateAlias);

        Call<ResponseUpdateAlias> call = api.updateAlias(LoginActivity.token, requestUpdateAlias);

        call.enqueue(new Callback<ResponseUpdateAlias>() {
            @Override
            public void onResponse(Call<ResponseUpdateAlias> call, Response<ResponseUpdateAlias> response) {
                if (response.isSuccessful()) {
                    updateAliasStatus = true;
                    Toast.makeText(getApplicationContext(), "Псевдоним изменён", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateAlias> call, Throwable t) {

            }
        });
        if (updateAliasStatus == true) {
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
                    pointsAliasForUp = "";
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        pointsAliasForUp += response.body().getResponse().get(i).getId() + " ";
                        pointsAliasForUp += response.body().getResponse().get(i).getDeviceId() + " ";
                        pointsAliasForUp += response.body().getResponse().get(i).getTitle() + " ";
                        pointsAliasForUp += "\n";
                    }
                    textViewGetPoints.setText(pointsAliasForUp.replace("null", ""));
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
                    pointsForAliaseses = "";
                    for (int i = 0; i < response.body().getResponse().size(); i++) {
                        pointsForAliaseses += response.body().getResponse().get(i).getId() + " ";
                        pointsForAliaseses += response.body().getResponse().get(i).getTitle() + " ";
                        pointsForAliaseses += response.body().getResponse().get(i).getPointId() + " ";
                        pointsForAliaseses += "\n";
                    }
                    textViewGetAliases.setText(pointsForAliaseses.replace("null", ""));
                }
            }

            @Override
            public void onFailure(Call<ResponseBodyAliases> call, Throwable t) {

            }
        });
    }
}
