package com.example.android.movieflix.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private MovieApiClient movieApiClient;

    private static MovieRepository instance;



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

    public void searchMovieApi(String query, int page){

        movieApiClient.searchMoviesApi(query, page);
    }

}
