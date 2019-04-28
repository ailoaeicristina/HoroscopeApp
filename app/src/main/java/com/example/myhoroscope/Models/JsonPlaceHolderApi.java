package com.example.myhoroscope.Models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("/qod.json")
    Call<WResponse> getDailyQuote();
}
