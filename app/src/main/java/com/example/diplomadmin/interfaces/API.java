package com.example.diplomadmin.interfaces;

import com.example.diplomadmin.requestBody.RequestBodyAddPoint;
import com.example.diplomadmin.requestBody.RequestBodyAuth;
import com.example.diplomadmin.requestBody.RequestUpdatePoint;
import com.example.diplomadmin.responseBody.ResponseAddPoint;
import com.example.diplomadmin.responseBody.ResponseBodyAuth;
import com.example.diplomadmin.responseBody.ResponseUpdatePoint;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("/api/login")
    Call<ResponseBodyAuth> loginUser(@Body RequestBodyAuth requestBodyAuth);

    @POST("api/addPoint")
    Call<ResponseAddPoint> addPoint(@Body RequestBodyAddPoint requestBodyAddPoint);

    @POST("api/updatePoint")
    Call<ResponseUpdatePoint> updatePoint(@Body RequestUpdatePoint requestUpdatePoint);
}
