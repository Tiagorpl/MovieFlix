package com.example.android.movieflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieOutput {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    @Expose
    private List<Movie> moviesList;

    public List<Movie> getResults() {
        return moviesList;
    }

    public int getPage() {
        return page;
    }



}
