package com.example.android.movieflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.request.MoviesApi;
import com.example.android.movieflix.request.Servicey;
import com.example.android.movieflix.response.MovieSearchResponse;
import com.example.android.movieflix.utils.Configs;
import com.example.android.movieflix.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Network Security






    Button btn;

    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ObserveAnyChange();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMovieApi("Fast", 1);
            }
        });

    }

    private void ObserveAnyChange(){

        movieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null){
                    for(Movie movie : movies){
                        Log.v("Tag", "Title" + movie.getOriginal_title());
                    }
                }
            }
        });
    }

    private void searchMovieApi(String query, int page){
        movieListViewModel.searchMovieApi(query, page);
    }





    private void GetRetrofitResponse() {
        MoviesApi moviesApi = Servicey.getMoviesApi();

        Call<MovieSearchResponse> responseCall = moviesApi.searchMovie(Configs.API_KEY, "Jack Reacher", "1");

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if(response.code() == 200){
                    Log.v("Tag", "The response" + response.body().toString());

                    List<Movie> movies = new ArrayList<>(response.body().getMovies());

                    for (Movie movie : movies){
                        Log.v("Tag", "The List" + movie.getOriginal_title());
                    }
                }
                else {
                    try {
                        Log.v("Tag", "Error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });

    }

    private void GetRetrofitResponseById(){
            MoviesApi moviesApi = Servicey.getMoviesApi();
            Call<Movie> responseCall = moviesApi.getMovie(550, Configs.API_KEY);

            responseCall.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response.code() == 200){
                        Movie movie = response.body();
                        Log.v("Tag", "The response" + movie.getOriginal_title());
                    }
                    else
                    {
                        try {
                            Log.v("Tag", "Error" + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {

                }
            });
        }

}

