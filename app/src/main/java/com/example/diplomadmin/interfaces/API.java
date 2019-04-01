package com.example.diplomadmin.interfaces;

import com.example.diplomadmin.requestBody.RequestBodyAuth;
import com.example.diplomadmin.responseBody.ResponseBodyAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("/api/login")
    Call<ResponseBodyAuth> loginUser(@Body RequestBodyAuth requestBodyAuth);
}
