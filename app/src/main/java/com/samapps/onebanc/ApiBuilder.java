package com.samapps.onebanc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.samapps.onebanc.response.OneBancResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    static final String BASE_URL = "https://dev.onebanc.ai/assignment.asmx/";

    public void start(Callback<OneBancResponse> callback) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gitApi gerritAPI = retrofit.create(gitApi.class);

        Call<OneBancResponse> call = gerritAPI.loadChanges("1","2");
        call.enqueue(callback);
}
}
