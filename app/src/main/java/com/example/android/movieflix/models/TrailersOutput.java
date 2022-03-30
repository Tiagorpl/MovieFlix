package com.example.android.movieflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersOutput {


    @SerializedName("results")
    @Expose
    private List<Trailer> trailerList;

    public List<Trailer> getResults() {
        return trailerList;
    }



}
