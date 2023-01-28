package com.example.android.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {

    @GET("users/{user}/repos")
    Call<String> getResult(@Path("user") String user);
}
