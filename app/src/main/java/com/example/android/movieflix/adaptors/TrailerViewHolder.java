package com.example.android.movieflix.adaptors;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieflix.R;

public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Button button_play;
    TextView textView;

    OnTrailerListener onTrailerListener;

    public TrailerViewHolder(@NonNull View itemView, OnTrailerListener onTrailerListener) {
        super(itemView);

        this.onTrailerListener = onTrailerListener;

        button_play = itemView.findViewById(R.id.trailer_button);
        textView = itemView.findViewById(R.id.trailer_id);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        onTrailerListener.OnTrailerClick(getAdapterPosition());
    }
}
