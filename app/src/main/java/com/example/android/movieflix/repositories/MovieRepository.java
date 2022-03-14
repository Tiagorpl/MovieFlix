package com.example.android.movieflix.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.models.Review;
import com.example.android.movieflix.models.Trailer;
import com.example.android.movieflix.request.Servicey;
import com.example.android.movieflix.response.MovieSearchResponse;
import com.example.android.movieflix.response.MovieSearchReviews;
import com.example.android.movieflix.response.MovieSearchTrailers;
import com.example.android.movieflix.utils.Configs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private MutableLiveData<List<Review>> reviews;
    private MutableLiveData<List<Trailer>> trailers;
    private MutableLiveData<List<Movie>> mMovies;
    private MutableLiveData<List<Movie>> mMoviesPopular;
    private MutableLiveData<List<Movie>> mMoviesUpComing;
    private MutableLiveData<List<Movie>> mMoviesTopRated;
    private MutableLiveData<List<Movie>> mMoviesFavorite;
    private static MovieRepository instance;


    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        mMovies = new MutableLiveData<>();
        mMoviesUpComing = new MutableLiveData<>();
        mMoviesPopular = new MutableLiveData<>();
        mMoviesTopRated = new MutableLiveData<>();
        mMoviesFavorite = new MutableLiveData<>();
        reviews = new MutableLiveData<>();
        trailers = new MutableLiveData<>();

    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public LiveData<List<Movie>> getMoviesPopular() {
        return mMoviesPopular;
    }

    public LiveData<List<Movie>> getMoviesLatest() {
        return mMoviesUpComing;
    }

    public LiveData<List<Movie>> getMoviesTopRated() {
        return mMoviesTopRated;
    }


    public void searchMoviesApi(String query, int pageNumber) {

        Call<MovieSearchResponse> callSearch = Servicey.getMoviesApi().searchMovie(Configs.API_KEY, query, String.valueOf(pageNumber));
        callSearch.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(response.body().getMovies());
                    if (pageNumber == 1) {
                        mMovies.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }

                }
            }


            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                mMovies.postValue(null);
            }
        });
    }


    public void searchMoviesApiPopular(int pageNumber) {

        Call<MovieSearchResponse> callPopular = Servicey.getMoviesApi().popularMovies(Configs.API_KEY, pageNumber);
        callPopular.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(response.body().getMovies());
                    if (pageNumber == 1) {
                        mMoviesPopular.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMoviesPopular.getValue();
                        currentMovies.addAll(list);
                        mMoviesPopular.postValue(currentMovies);
                    }

                }


            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                mMoviesPopular.postValue(null);
            }
        });
    }

    public void searchMoviesApiUpComing(int pageNumber) {

        Call<MovieSearchResponse> callUpComing = Servicey.getMoviesApi().upComingMovies(Configs.API_KEY, pageNumber);
        callUpComing.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(response.body().getMovies());
                    if (pageNumber == 1) {
                        mMoviesUpComing.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMoviesUpComing.getValue();
                        currentMovies.addAll(list);
                        mMoviesUpComing.postValue(currentMovies);
                    }

                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                mMoviesUpComing.postValue(null);
            }
        });
    }

    public void searchMoviesApiTopRated(int pageNumber) {

        Call<MovieSearchResponse> callTopRated = Servicey.getMoviesApi().topRatedMovies(Configs.API_KEY, pageNumber);
        callTopRated.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(response.body().getMovies());
                    if (pageNumber == 1) {
                        mMoviesTopRated.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMoviesTopRated.getValue();
                        currentMovies.addAll(list);
                        mMoviesTopRated.postValue(currentMovies);
                    }

                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                mMoviesTopRated.postValue(null);
            }
        });
    }


    public void searchForReviews(int id) {
        Call<MovieSearchReviews> callReviews = Servicey.getMoviesApi().movieReviews(id, Configs.API_KEY);
        callReviews.enqueue(new Callback<MovieSearchReviews>() {
            @Override
            public void onResponse(Call<MovieSearchReviews> call, Response<MovieSearchReviews> response) {
                if (response.code() == 200) {
                    List<Review> list = new ArrayList<>(response.body().getReviews());
                    reviews.postValue(list);
                }


            }

            @Override
            public void onFailure(Call<MovieSearchReviews> call, Throwable t) {
                reviews.postValue(null);
            }
        });

    }

    public void searchForTrailers(int id) {
        Call<MovieSearchTrailers> callTrailers = Servicey.getMoviesApi().movieTrailers(id, Configs.API_KEY);
        callTrailers.enqueue(new Callback<MovieSearchTrailers>() {
            @Override
            public void onResponse(Call<MovieSearchTrailers> call, Response<MovieSearchTrailers> response) {
                if (response.code() == 200) {
                    List<Trailer> list = new ArrayList<>(response.body().getTrailers());
                    trailers.postValue(list);
                }
            }

            @Override
            public void onFailure(Call<MovieSearchTrailers> call, Throwable t) {
                trailers.postValue(null);
            }
        });

    }


    public MutableLiveData<List<Review>> getReviews() {
        return reviews;
    }

    public MutableLiveData<List<Trailer>> getTrailers() {
        return trailers;
    }
}
