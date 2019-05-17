package com.challenge.ecommarcechallangeapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrfitClient {

    private static final String BASE_URL = "https://mobilebackend.turing.com/";

    private static Retrofit retrofit ;

    public static Retrofit getRetrofitClient(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
