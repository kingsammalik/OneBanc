package com.samapps.onebanc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samapps.onebanc.response.OneBancResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<OneBancResponse> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiBuilder controller = new ApiBuilder();
        controller.start(this);
    }

    @Override
    public void onResponse(Call<OneBancResponse> call, Response<OneBancResponse> response) {

            if(response.isSuccessful()) {
                OneBancResponse changesList = response.body();
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                Adapter adapter = new Adapter(changesList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
            } else {
                System.out.println(response.errorBody());
            }
    }

    @Override
    public void onFailure(Call<OneBancResponse> call, Throwable t) {
        t.printStackTrace();
    }
}