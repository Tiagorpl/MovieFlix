package com.example.android.movieflix.response;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.models.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchReviews {

    @SerializedName("page")
    @Expose()
    private int page;

    @SerializedName("results")
    @Expose()
    private List<Review> reviews;

    public int getPage(){
        return page;
    }

    public List<Review> getReviews(){
        return reviews;
    }


    @Override
    public String toString() {
        return "MovieSearchReviews{" +
                "page=" + page +
                ", reviews=" + reviews +
                '}';
    }
}
