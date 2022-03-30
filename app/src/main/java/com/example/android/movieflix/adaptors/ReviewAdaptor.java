package com.example.android.movieflix.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieflix.R;
import com.example.android.movieflix.models.Review;

import java.util.List;

public class ReviewAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Review> mReviews;


    public ReviewAdaptor() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_review_item, parent, false);
            return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ReviewViewHolder)holder).author.setText(mReviews.get(position).getAuthor());
        ((ReviewViewHolder)holder).content.setText(mReviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        if (mReviews != null){
            return mReviews.size();
        }
        return 0;
    }

    public void setmReviews(List<Review> mReviews) {
        this.mReviews = mReviews;

    }

}
