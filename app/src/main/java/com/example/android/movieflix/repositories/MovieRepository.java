package com.example.android.movieflix.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private MovieApiClient movieApiClient;

    private static MovieRepository instance;


    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance(){
        if (instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository(){
         movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieApiClient.getmMovies();
    }

    public LiveData<List<Movie>> getMoviesPopular(){
        return movieApiClient.getmMoviesPopular();
    }

    public LiveData<List<Movie>> getMoviesLatest(){
        return movieApiClient.getmMoviesLatest();
    }

    public LiveData<List<Movie>> getMoviesTopRated(){
        return movieApiClient.getmMoviesTopRated();
    }



    public void searchMovieApi(String query, int page){

        mQuery = query;
        mPageNumber = page;
        movieApiClient.searchMoviesApi(query, page);


    }
    public void searchMovieApiPopular(int page){

        mPageNumber = page;
        movieApiClient.searchMoviesApiPopular(page);


    }

    public void searchMovieApiLatest(){
        movieApiClient.searchMoviesApiLatest();

    }

    public void searchMovieApiTopRated(int page){
        mPageNumber = page;
        movieApiClient.searchMoviesApiTopRated(page);


    }


    public void searchNextPage(){
        searchMovieApi(mQuery, mPageNumber + 1);
    }

    public void searchNextPagePopular(){
        searchMovieApiPopular(mPageNumber + 1);
    }

    public void searchNextPageTopRated(){
        searchMovieApiTopRated( mPageNumber + 1);
    }

}
