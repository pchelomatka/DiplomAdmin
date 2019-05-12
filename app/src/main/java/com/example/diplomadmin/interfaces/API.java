package com.example.diplomadmin.interfaces;

import com.example.diplomadmin.requestBody.RequestAddAlias;
import com.example.diplomadmin.requestBody.RequestAddVector;
import com.example.diplomadmin.requestBody.RequestBodyAddPoint;
import com.example.diplomadmin.requestBody.RequestBodyAuth;
import com.example.diplomadmin.requestBody.RequestDeleteAlias;
import com.example.diplomadmin.requestBody.RequestDeletePoint;
import com.example.diplomadmin.requestBody.RequestDeleteVector;
import com.example.diplomadmin.requestBody.RequestUpdateAlias;
import com.example.diplomadmin.requestBody.RequestUpdatePoint;
import com.example.diplomadmin.requestBody.RequestUpdateVector;
import com.example.diplomadmin.responseBody.ResponseAddAlias;
import com.example.diplomadmin.responseBody.ResponseAddPoint;
import com.example.diplomadmin.responseBody.ResponseAddVector;
import com.example.diplomadmin.responseBody.ResponseBodyAliases;
import com.example.diplomadmin.responseBody.ResponseBodyAuth;
import com.example.diplomadmin.responseBody.ResponseDeleteAlias;
import com.example.diplomadmin.responseBody.ResponseDeletePoint;
import com.example.diplomadmin.responseBody.ResponseDeleteVector;
import com.example.diplomadmin.responseBody.ResponseGetPoints;
import com.example.diplomadmin.responseBody.ResponseGetVectors;
import com.example.diplomadmin.responseBody.ResponseUpdateAlias;
import com.example.diplomadmin.responseBody.ResponseUpdatePoint;
import com.example.diplomadmin.responseBody.ResponseUpdateVector;

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

    @Headers({"User-Agent: " + userAgent})
    @POST("api/addVector")
    Call<ResponseAddVector> addVector(@Query("token") String token, @Body RequestAddVector requestAddVector);

    @Headers({"User-Agent: " + userAgent})
    @POST("api/updateVector")
    Call<ResponseUpdateVector> updateVector(@Query("token") String token, @Body RequestUpdateVector requestUpdateVector);

    @Headers({"User-Agent: " + userAgent})
    @GET("api/vectors")
    Call<ResponseGetVectors> vectors(@Query("token") String token, @Query("building_id") String building_id);

    @Headers({"User-Agent: " + userAgent})
    @POST("api/deleteVector")
    Call<ResponseDeleteVector> deleteVector(@Query("token") String token, @Body RequestDeleteVector requestDeleteVector);

    @Headers({"User-Agent: " + userAgent})
    @POST("api/addAlias")
    Call<ResponseAddAlias> addAlias(@Query("token") String token, @Body RequestAddAlias requestAddAlias);

    @Headers({"User-Agent: " + userAgent})
    @GET("api/aliases")
    Call<ResponseBodyAliases> aliases(@Query("token") String token, @Query("point_id") String point_id);

    @Headers({"User-Agent: " + userAgent})
    @POST("api/deleteAlias")
    Call<ResponseDeleteAlias> deleteAlias(@Query("token") String token, @Body RequestDeleteAlias requestDeleteAlias);

    @Headers({"User-Agent: " + userAgent})
    @POST("api/updateAlias")
    Call<ResponseUpdateAlias> updateAlias(@Query("token") String token, @Body RequestUpdateAlias requestUpdateAlias);
}
