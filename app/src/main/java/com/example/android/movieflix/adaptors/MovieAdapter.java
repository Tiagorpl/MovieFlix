package com.example.android.movieflix.adaptors;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.movieflix.R;
import com.example.android.movieflix.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> mMovies;
    private final OnMovieListener onMovieListener;



    public MovieAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
            return new MovieViewHolder(view, onMovieListener);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //Glide
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path()).into(((MovieViewHolder) holder).imageView);

    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmMovies(List<Movie> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    public Movie getSelectedMovie(int position) {
        if (mMovies != null) {
            if (!mMovies.isEmpty()) {
                return mMovies.get(position);
            }
        }
        return null;
    }

}
