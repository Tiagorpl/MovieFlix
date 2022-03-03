package com.example.android.movieflix.response;

import com.example.android.movieflix.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("page")
    @Expose()
    private int page;

    @SerializedName("results")
    @Expose()
    private List<Movie> movies;

    public int getPage(){
        return page;
    }

    public List<Movie> getMovies(){
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "page=" + page +
                ", movies=" + movies +
                '}';
    }
}
