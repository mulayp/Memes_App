package com.example.memes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.memes_app.DATA.DataClass;
import com.example.memes_app.DATA.memeinterface;
import com.example.memes_app.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainActivity context;
        context = this;

        RecyclerView recyclerView = binding.recyclerview;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        //code for retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.memegen.link/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        memeinterface inter = retrofit.create(memeinterface.class);
        try {

            Call<ArrayList<DataClass>> call = inter.getMemes();

            call.enqueue(new Callback<ArrayList<DataClass>>() {
                             @Override
                             public void onResponse(Call<ArrayList<DataClass>> call, Response<ArrayList<DataClass>> response) {
                                 Log.d("dps", response.body().get(0).getUrl());
                                 RecyclerView.Adapter adapter = new MemeAdapter(response.body(), context);

                                 recyclerView.setAdapter(adapter);
                             }

                             @Override
                             public void onFailure(Call<ArrayList<DataClass>> call, Throwable t) {
                             }
                         }
            );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}