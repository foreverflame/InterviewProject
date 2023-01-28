package com.example.android.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkhttpTest {


    private void getRetrofitData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        retrofit2.Call<String> result = apiService.getResult("huang");


        try {
            String body = result.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }


        result.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {

            }
        });

    }

    private void getRequest() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("http://baidu.com")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);

        try {
            //同步调用
            Response execute = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //异步调用
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
