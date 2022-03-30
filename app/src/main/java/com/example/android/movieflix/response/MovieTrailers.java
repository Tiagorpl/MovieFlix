package com.example.android.movieflix.response;


import com.example.android.movieflix.models.Review;
import com.example.android.movieflix.models.Trailer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieTrailers {

    @SerializedName("results")
    @Expose
    private Trailer trailer;

    public Trailer getTrailer(){
        return trailer;
    }

    @Override
    public String toString() {
        return "MovieTrailers{" +
                "trailer=" + trailer +
                '}';
    }
}
