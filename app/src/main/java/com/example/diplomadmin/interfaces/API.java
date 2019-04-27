package com.example.diplomadmin.interfaces;

import com.example.diplomadmin.requestBody.RequestBodyAddPoint;
import com.example.diplomadmin.requestBody.RequestBodyAuth;
import com.example.diplomadmin.requestBody.RequestDeletePoint;
import com.example.diplomadmin.requestBody.RequestUpdatePoint;
import com.example.diplomadmin.responseBody.ResponseAddPoint;
import com.example.diplomadmin.responseBody.ResponseBodyAuth;
import com.example.diplomadmin.responseBody.ResponseDeletePoint;
import com.example.diplomadmin.responseBody.ResponseGetPoints;
import com.example.diplomadmin.responseBody.ResponseUpdatePoint;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    
    static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36";

    @Headers({"User-Agent: " + userAgent})
    @POST("/api/login")
    Call<ResponseBodyAuth> loginUser(@Body RequestBodyAuth requestBodyAuth);

    @Headers({"User-Agent: " + userAgent})
    @POST("api/addPoint")
    Call<ResponseAddPoint> addPoint(@Query("token") String token, @Body RequestBodyAddPoint requestBodyAddPoint);

    @Headers({"User-Agent: " + userAgent})
    @POST("api/updatePoint")
    Call<ResponseUpdatePoint> updatePoint(@Query("token") String token, @Body RequestUpdatePoint requestUpdatePoint);

    @Headers({"User-Agent: " + userAgent})
    @POST("api/deletePoint")
    Call<ResponseDeletePoint> deletePoint(@Query("token") String token, @Body RequestDeletePoint requestDeletePoint);

    @Headers({"User-Agent: " + userAgent})
    @GET("api/points")
    Call<ResponseGetPoints> points(@Query("token") String token, @Query("building_id") String building_id);
}
