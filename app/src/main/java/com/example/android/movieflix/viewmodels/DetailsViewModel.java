package com.example.android.movieflix.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.models.Review;
import com.example.android.movieflix.models.Trailer;
import com.example.android.movieflix.repositories.MovieDbRepository;
import com.example.android.movieflix.repositories.MovieRepository;


import java.util.List;

public class DetailsViewModel extends AndroidViewModel {

    MovieRepository movieRepository;
    MovieDbRepository movieDbRepository;
    LiveData<List<Movie>> allMovies;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        movieDbRepository = MovieDbRepository.getInstance(application);
        movieRepository = MovieRepository.getInstance();
        allMovies = movieDbRepository.getAllFavoriteMovies();
    }


    public void delete(int id) {
        movieDbRepository.delete(id);
    }

    public void insert(Movie movieFavorite) {
        movieDbRepository.insert(movieFavorite);
    }

    public boolean movieExists(int id) {
        return movieDbRepository.movieExists(id);
    }

    public void searchForReviews(int id) {
        movieRepository.searchForReviews(id);
    }

    public MutableLiveData<List<Review>> getReviews() {
        return movieRepository.getReviews();
    }

    public void searchForTrailers(int id) {
        movieRepository.searchForTrailers(id);
    }

    public MutableLiveData<List<Trailer>> getTrailers() {
        return movieRepository.getTrailers();
    }
}
