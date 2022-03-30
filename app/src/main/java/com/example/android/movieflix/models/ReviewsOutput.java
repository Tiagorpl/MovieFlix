package com.example.android.movieflix.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsOutput {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    @Expose
    private List<Review> reviewList;

    public List<Review> getResults() {
        return reviewList;
    }

    public int getPage() {
        return page;
    }


}
