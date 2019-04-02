package com.example.diplomadmin.interfaces;

import com.example.diplomadmin.activities.login.LoginActivity;
import com.example.diplomadmin.requestBody.RequestBodyAddPoint;
import com.example.diplomadmin.requestBody.RequestBodyAuth;
import com.example.diplomadmin.responseBody.ResponseAddPoint;
import com.example.diplomadmin.responseBody.ResponseBodyAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @POST("/api/login")
    Call<ResponseBodyAuth> loginUser(@Body RequestBodyAuth requestBodyAuth);

    @POST("api/addPoint?token=access461b9bed5f7d6f9c39faf6b986605cf3")
    Call<ResponseAddPoint> addPoint(@Body RequestBodyAddPoint requestBodyAddPoint);
}
