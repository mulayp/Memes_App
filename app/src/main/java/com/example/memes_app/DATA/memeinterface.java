package com.example.memes_app.DATA;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface memeinterface {
    @GET("images/")
    Call<ArrayList<DataClass>> getMemes();

}
