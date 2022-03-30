package com.example.android.movieflix.adaptors;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieflix.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    TextView author;
    TextView content;


    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.review_author);
        content = itemView.findViewById(R.id.review_content);


    }
}
