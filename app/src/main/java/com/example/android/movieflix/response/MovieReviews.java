package com.example.android.movieflix.response;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.models.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieReviews {

    @SerializedName("results")
    @Expose
    private Review review;

    public Review getReview(){
        return review;
    }

    @Override
    public String toString() {
        return "MovieReviews{" +
                "review=" + review +
                '}';
    }
}
