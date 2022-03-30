package com.example.android.movieflix.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android.movieflix.models.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    public abstract MovieFavoriteDao movieFavoriteDao();

    public static synchronized MovieDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MovieDatabase.class,"movie_favorite_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
