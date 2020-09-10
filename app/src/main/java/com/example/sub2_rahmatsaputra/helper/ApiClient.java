package com.example.sub2_rahmatsaputra.helper;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String API_GITHUB = "https://api.github.com/";
    public static final String BASE_URL = API_GITHUB;
    public static Retrofit retrofit;

    public static ApiService getService(Context context) {
        return getApiClient(context).create(ApiService.class);
    }

    public static Retrofit getApiClient(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
