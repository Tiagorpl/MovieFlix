package com.example.android.movieflix.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.android.movieflix.database.MovieDatabase;
import com.example.android.movieflix.database.MovieFavoriteDao;
import com.example.android.movieflix.models.Movie;

import java.util.List;

public class MovieDbRepository {

    private static MovieDbRepository instance;
    private MovieFavoriteDao movieFavoriteDao;
    private LiveData<List<Movie>> allFavoriteMovies;
    private MovieDatabase database;

    public MovieDbRepository(Context context){
        database = MovieDatabase.getInstance(context);
        movieFavoriteDao = database.movieFavoriteDao();
        allFavoriteMovies = movieFavoriteDao.getAllFavoriteMovies();
    }

    public static MovieDbRepository getInstance(Context context){
        if (instance == null){
            instance = new MovieDbRepository(context);
        }
        return instance;
    }

    public void insert(Movie movieFavorite){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.movieFavoriteDao().insert(movieFavorite);
                return null;
            }
        }.execute();


    }

    public void delete(int id){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                database.movieFavoriteDao().delete(id);
                return null;
            }
        }.execute();
    }


    public LiveData<List<Movie>> getAllFavoriteMovies(){

        return allFavoriteMovies;
    }

    public boolean movieExists(int id){
        if (database.movieFavoriteDao().getMovieByID(id)){
            return true;
        }
        else{
            return false;
        }
    }

}
