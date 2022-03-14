package com.example.android.movieflix.request;


import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.models.MovieOutput;
import com.example.android.movieflix.response.MovieSearchResponse;
import com.example.android.movieflix.response.MovieSearchReviews;
import com.example.android.movieflix.response.MovieSearchTrailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("3/movie/upcoming")
    Call<MovieSearchResponse> upComingMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/movie/top_rated")
    Call<MovieSearchResponse> topRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/movie/popular")
    Call<MovieSearchResponse> popularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") String page);

    @GET("3/movie/{movie_id}?")
    Call<Movie> getMovie(
            @Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("3/movie/{movie_id}/reviews?")
    Call<MovieSearchReviews> movieReviews(
            @Path("movie_id") int movie_id, @Query("api_key") String apiKey);



    @GET("3/movie/{movie_id}/videos?")
    Call<MovieSearchTrailers> movieTrailers(
            @Path("movie_id") int movie_id, @Query("api_key") String apiKey);




}
