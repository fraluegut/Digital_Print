package com.example.digitalprint1.retrofit;

import com.example.digitalprint1.RequestLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface interdaz {

    @POST("/datos/")
    Call<ResponseAuth> doSomething(@Body RequestLogin requestLogin);
}
