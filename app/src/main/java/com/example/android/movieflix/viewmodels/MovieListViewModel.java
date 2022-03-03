package com.example.android.movieflix.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovies();
    }

    public void searchMovieApi(String query, int page){
        movieRepository.searchMovieApi(query, page);
    }

}
