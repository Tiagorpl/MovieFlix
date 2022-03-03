package com.example.android.movieflix.request;


import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.models.MovieOutput;
import com.example.android.movieflix.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("3/movie/popular")
    Call<MovieSearchResponse> popularMovies(@Query("api_key") String apiKey);

    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") String page);

    @GET("3/movie/{movie_id}?")
    Call<Movie> getMovie(
            @Path("movie_id") int movie_id, @Query("api_key") String api_key);

}