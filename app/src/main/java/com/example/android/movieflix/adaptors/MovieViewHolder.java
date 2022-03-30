package com.example.android.movieflix.adaptors;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieflix.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView title;
    ImageView imageView;

    OnMovieListener onMovieListener;


    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        title = itemView.findViewById(R.id.movie_title);
        imageView = itemView.findViewById(R.id.movie_img);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.OnMovieClick(getAdapterPosition());
    }
}
