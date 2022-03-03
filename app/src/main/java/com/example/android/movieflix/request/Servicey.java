package com.example.android.movieflix.request;

import com.example.android.movieflix.utils.Configs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(Configs.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());


    private static Retrofit retrofit = retrofitBuilder.build();

    private static MoviesApi moviesApi = retrofit.create(MoviesApi.class);

    public static MoviesApi getMoviesApi(){
        return moviesApi;
    }

}
