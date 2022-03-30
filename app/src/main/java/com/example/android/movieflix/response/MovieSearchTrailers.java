package com.example.android.movieflix.response;

import com.example.android.movieflix.models.Review;
import com.example.android.movieflix.models.Trailer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchTrailers {


    @SerializedName("results")
    @Expose()
    private List<Trailer> trailers;

    public List<Trailer> getTrailers(){
        return trailers;
    }


    @Override
    public String toString() {
        return "MovieSearchTrailers{" +
                "trailers=" + trailers +
                '}';
    }
}
