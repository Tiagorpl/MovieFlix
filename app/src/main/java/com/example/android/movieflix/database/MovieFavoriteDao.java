package com.example.android.movieflix.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.movieflix.models.Movie;

import java.util.List;

@Dao
public interface MovieFavoriteDao {

    @Insert
    void insert(Movie movieFavorite);

    @Query("DELETE FROM movie_favorite WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM movie_favorite")
    LiveData<List<Movie>> getAllFavoriteMovies();

    @Query("SELECT EXISTS(SELECT * FROM movie_favorite WHERE id = :id)")
    boolean getMovieByID(int id);

}
