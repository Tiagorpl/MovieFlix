package com.example.android.movieflix.response;

import com.example.android.movieflix.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("results")
    @Expose
    private Movie movie;

    public Movie getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
