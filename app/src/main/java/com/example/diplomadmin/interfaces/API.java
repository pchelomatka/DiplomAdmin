package com.example.diplomadmin.interfaces;

import com.example.diplomadmin.request_body.RequestAddAlias;
import com.example.diplomadmin.request_body.RequestAddVector;
import com.example.diplomadmin.request_body.RequestBodyAddPoint;
import com.example.diplomadmin.request_body.RequestBodyAuth;
import com.example.diplomadmin.request_body.RequestDeleteAlias;
import com.example.diplomadmin.request_body.RequestDeletePoint;
import com.example.diplomadmin.request_body.RequestDeleteVector;
import com.example.diplomadmin.request_body.RequestUpdateAlias;
import com.example.diplomadmin.request_body.RequestUpdatePoint;
import com.example.diplomadmin.request_body.RequestUpdateVector;
import com.example.diplomadmin.response_body.ResponseAddAlias;
import com.example.diplomadmin.response_body.ResponseAddPoint;
import com.example.diplomadmin.response_body.ResponseAddVector;
import com.example.diplomadmin.response_body.ResponseBodyAliases;
import com.example.diplomadmin.response_body.ResponseBodyAuth;
import com.example.diplomadmin.response_body.ResponseDeleteAlias;
import com.example.diplomadmin.response_body.ResponseDeletePoint;
import com.example.diplomadmin.response_body.ResponseDeleteVector;
import com.example.diplomadmin.response_body.ResponseGetPoints;
import com.example.diplomadmin.response_body.ResponseGetVectors;
import com.example.diplomadmin.response_body.ResponseUpdateAlias;
import com.example.diplomadmin.response_body.ResponseUpdatePoint;
import com.example.diplomadmin.response_body.ResponseUpdateVector;

import retrofit2.Call;
import retrofit2.http.Body;
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
