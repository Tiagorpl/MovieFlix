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

    public LiveData<List<Movie>> getMoviesPopular() {
        return movieRepository.getMoviesPopular();
    }

    public LiveData<List<Movie>> getMoviesLatest() {
        return movieRepository.getMoviesLatest();
    }

    public LiveData<List<Movie>> getMoviesTopRated() {
        return movieRepository.getMoviesTopRated();
    }

    public void searchMovieApi(String query, int page){
        movieRepository.searchMovieApi(query, page);
    }

    public void searchMovieApiPopular(int page){
        movieRepository.searchMovieApiPopular(page);
    }

    public void searchMovieApiLatest(){
        movieRepository.searchMovieApiLatest();
    }

    public void searchMovieApiTopRated(int page){
        movieRepository.searchMovieApiTopRated(page);
    }

    public void searchNextPage(){
        movieRepository.searchNextPage();
    }
    public void searchNextPagePopular(){
        movieRepository.searchNextPagePopular();
    }
    public void searchNextPageTopRated(){
        movieRepository.searchNextPageTopRated();
    }

}
