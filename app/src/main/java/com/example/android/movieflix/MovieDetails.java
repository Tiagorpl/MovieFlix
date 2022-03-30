package com.example.android.movieflix;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.android.movieflix.adaptors.OnTrailerListener;
import com.example.android.movieflix.adaptors.ReviewAdaptor;
import com.example.android.movieflix.adaptors.TrailerAdapter;
import com.example.android.movieflix.models.Movie;

import com.example.android.movieflix.viewmodels.DetailsViewModel;


public class MovieDetails extends AppCompatActivity implements OnTrailerListener {

    private RecyclerView recyclerView_trailer;
    private TrailerAdapter trailerAdapter;

    private RecyclerView recyclerView_reviews;
    private ReviewAdaptor reviewAdapter;

    private ImageView backGroundImg;
    private TextView title;
    private TextView overview;
    private TextView language;
    private TextView release_date;
    private RatingBar ratingBar;
    private ToggleButton toggleButton;
    private int id;
    private TextView reviews;

    private DetailsViewModel detailsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        toggleButton = findViewById(R.id.toggle_favorite);
        configureRecyclerView();

        backGroundImg = findViewById(R.id.background_img);
        title = findViewById(R.id.movie_title);
        overview = findViewById(R.id.overview);
        language = findViewById(R.id.movie_language);
        release_date = findViewById(R.id.movie_language);
        ratingBar = findViewById(R.id.rating_bar);
        reviews = findViewById(R.id.movie_reviews);


        GetDataFromIntent();


        initial_toggle(id);
        toggle_favorite();
        searchForReviews();
        searchForTrailers();

    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchForReviews() {
        detailsViewModel.searchForReviews(id);
        detailsViewModel.getReviews().observe(this, reviews1 -> {
            reviewAdapter.setmReviews(reviews1);
            reviewAdapter.notifyDataSetChanged();
        });


    }

    public void searchForTrailers() {
        detailsViewModel.searchForTrailers(id);
        detailsViewModel.getTrailers().observe(this, trailers -> {
            trailerAdapter.setmTrailers(trailers);
            recyclerView_trailer.setAdapter(trailerAdapter);
        });

    }


    private void configureRecyclerView() {
        trailerAdapter = new TrailerAdapter(this);
        recyclerView_trailer = findViewById(R.id.recyclerView_trailer);
        recyclerView_trailer.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_trailer.setItemAnimator(new DefaultItemAnimator());
        recyclerView_trailer.setAdapter(trailerAdapter);

        reviewAdapter = new ReviewAdaptor();
        recyclerView_reviews = findViewById(R.id.recyclerView_review);
        recyclerView_reviews.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_reviews.setItemAnimator(new DefaultItemAnimator());
        recyclerView_reviews.setAdapter(reviewAdapter);

    }


    private void initial_toggle(int id) {
        if (detailsViewModel.movieExists(id)) {
            toggleButton.setChecked(true);
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favorite_star));
        } else {
            toggleButton.setChecked(false);
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favorite_empty_star));
        }
    }

    private void toggle_favorite() {
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Context context = getApplicationContext();
                if (isChecked) {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favorite_star));
                    savingFavorite();
                    Toast.makeText(context, "Added to favorites!", Toast.LENGTH_SHORT).show();
                } else {
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favorite_empty_star));
                    removeFavorite();
                    Toast.makeText(context, "Removed from favorites!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            Movie movie = getIntent().getParcelableExtra("movie");
            id = movie.getId();
            title.setText(movie.getOriginal_title());
            overview.setText(movie.getOverview());
            language.setText(movie.getOriginal_language());
            release_date.setText(movie.getRelease_date());
            ratingBar.setRating(movie.getVote_average());

            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.getBackdrop_path()).into(backGroundImg);

        }
    }

    private void removeFavorite() {
        Movie movie = getIntent().getParcelableExtra("movie");

        detailsViewModel.delete(movie.getId());
    }

    private void savingFavorite() {
        Movie movie = getIntent().getParcelableExtra("movie");

        detailsViewModel.insert(movie);
    }

    @Override
    public void OnTrailerClick(int position) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerAdapter.getTrailerKey(position)));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + trailerAdapter.getTrailerKey(position)));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}