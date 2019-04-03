package com.example.diplomadmin.interfaces;

import com.example.diplomadmin.requestBody.RequestBodyAddPoint;
import com.example.diplomadmin.requestBody.RequestBodyAuth;
import com.example.diplomadmin.requestBody.RequestDeletePoint;
import com.example.diplomadmin.requestBody.RequestUpdatePoint;
import com.example.diplomadmin.responseBody.ResponseAddPoint;
import com.example.diplomadmin.responseBody.ResponseBodyAuth;
import com.example.diplomadmin.responseBody.ResponseDeletePoint;
import com.example.diplomadmin.responseBody.ResponseUpdatePoint;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    @POST("/api/login")
    Call<ResponseBodyAuth> loginUser(@Body RequestBodyAuth requestBodyAuth);

    @POST("api/addPoint")
    Call<ResponseAddPoint> addPoint(@Query("token") String token, @Body RequestBodyAddPoint requestBodyAddPoint);

    @POST("api/updatePoint")
    Call<ResponseUpdatePoint> updatePoint(@Query("token") String token, @Body RequestUpdatePoint requestUpdatePoint);

    @POST("api/deletePoint")
    Call<ResponseDeletePoint> deletePoint(@Query("token") String token, @Body RequestDeletePoint requestDeletePoint);
}
