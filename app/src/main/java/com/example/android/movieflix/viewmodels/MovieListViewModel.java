package com.example.android.movieflix.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.repositories.MovieDbRepository;
import com.example.android.movieflix.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private MovieDbRepository movieDbRepository;
    private LiveData<List<Movie>> allMovies;
    private MovieListViewModel instance;


    public MovieListViewModel(Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
        movieDbRepository = MovieDbRepository.getInstance(application);
        allMovies = movieDbRepository.getAllFavoriteMovies();
    }

    public MovieListViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new MovieListViewModel(application);
        }
        return instance;
    }


    public LiveData<List<Movie>> getFavorites() {
        return movieDbRepository.getAllFavoriteMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovies();
    }

    public LiveData<List<Movie>> getMoviesPopular() {
        return movieRepository.getMoviesPopular();
    }

    public LiveData<List<Movie>> getMoviesLatest() {
        return movieRepository.getMoviesLatest();
    }

    public LiveData<List<Movie>> getMoviesTopRated() {
        return movieRepository.getMoviesTopRated();
    }


    public void searchMovieApi(String query, int page) {
        movieRepository.searchMoviesApi(query, page);
    }

    public void searchMovieApiPopular(int page) {
        movieRepository.searchMoviesApiPopular(page);
    }

    public void searchMovieApiUpComing(int page) {
        movieRepository.searchMoviesApiUpComing(page);
    }

    public void searchMovieApiTopRated(int page) {
        movieRepository.searchMoviesApiTopRated(page);
    }


}
