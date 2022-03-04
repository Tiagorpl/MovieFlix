package com.example.android.movieflix.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieflix.executors.AppExecutors;
import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.response.MovieSearchResponse;
import com.example.android.movieflix.utils.Configs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    //Search
    private MutableLiveData<List<Movie>> mMovies;

    //Popular
    private MutableLiveData<List<Movie>> mMoviesPopular;
    //Latest
    private MutableLiveData<List<Movie>> mMoviesLatest;
    //Top_Rated
    private MutableLiveData<List<Movie>> mMoviesTopRated;


    private static MovieApiClient instance;

    //Search
    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    //Popular
    private RetrieveMoviesRunnablePopular retrieveMoviesRunnablePopular;
    //Latest
    private RetrieveMoviesRunnableLatest retrieveMoviesRunnableLatest;
    //Top_Rated
    private RetrieveMoviesRunnableTopRated retrieveMoviesRunnableTopRated;

    public static MovieApiClient getInstance() {

        if (instance == null) {
            instance = new MovieApiClient();
        }

        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
        mMoviesLatest = new MutableLiveData<>();
        mMoviesPopular = new MutableLiveData<>();
        mMoviesTopRated = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getmMovies() {
        return mMovies;
    }

    public LiveData<List<Movie>> getmMoviesPopular() {
        return mMoviesPopular;
    }

    public LiveData<List<Movie>> getmMoviesLatest() {
        return mMoviesLatest;
    }

    public LiveData<List<Movie>> getmMoviesTopRated() {
        return mMoviesTopRated;
    }


    public void searchMoviesApi(String query, int pageNumber) {

        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    public void searchMoviesApiPopular(int pageNumber) {

        if (retrieveMoviesRunnablePopular != null) {
            retrieveMoviesRunnablePopular = null;
        }

        retrieveMoviesRunnablePopular = new RetrieveMoviesRunnablePopular(pageNumber);

        final Future myHandlerPopular = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnablePopular);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling retrofit call
                myHandlerPopular.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    public void searchMoviesApiLatest() {

        if (retrieveMoviesRunnableLatest != null) {
            retrieveMoviesRunnableLatest = null;
        }

        retrieveMoviesRunnableLatest = new RetrieveMoviesRunnableLatest();

        final Future myHandlerLatest = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnableLatest);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling retrofit call
                myHandlerLatest.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    public void searchMoviesApiTopRated(int pageNumber) {

        if (retrieveMoviesRunnableTopRated != null) {
            retrieveMoviesRunnableTopRated = null;
        }

        retrieveMoviesRunnableTopRated = new RetrieveMoviesRunnableTopRated(pageNumber);

        final Future myHandlerTopRated = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnableTopRated);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling retrofit call
                myHandlerTopRated.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }


        @Override
        public void run() {

            try {
                Response response = getMovies(query, pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                    if (pageNumber == 1) {
                        // sending data to live data
                        // postvalue : background thread
                        // setvalue : not background thread

                        mMovies.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error" + error);
                    mMovies.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return Servicey.getMoviesApi().searchMovie(
                    Configs.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

    private class RetrieveMoviesRunnablePopular implements Runnable {
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnablePopular(int pageNumber) {

            this.pageNumber = pageNumber;
            cancelRequest = false;
        }


        @Override
        public void run() {

            try {
                Response responsePopular = getMoviesPopular(pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (responsePopular.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse) responsePopular.body()).getMovies());
                    if (pageNumber == 1) {
                        // sending data to live data
                        // postvalue : background thread
                        // setvalue : not background thread

                        mMoviesPopular.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMoviesPopular.getValue();
                        currentMovies.addAll(list);
                        mMoviesPopular.postValue(currentMovies);
                    }
                } else {
                    String error = responsePopular.errorBody().string();
                    Log.v("Tag", "Error" + error);
                    mMoviesPopular.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mMoviesPopular.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getMoviesPopular(int pageNumber) {
            return Servicey.getMoviesApi().popularMovies(
                    Configs.API_KEY,
                    pageNumber
            );
        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

    private class RetrieveMoviesRunnableLatest implements Runnable {
        boolean cancelRequest;

        public RetrieveMoviesRunnableLatest() {
            cancelRequest = false;
        }


        @Override
        public void run() {

            try {
                Response responseLatest = getMoviesLatest().execute();
                if (cancelRequest) {
                    return;
                }
                if (responseLatest.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse) responseLatest.body()).getMovies());
                    mMoviesLatest.postValue(list);

                }

                else{
                    String error = responseLatest.errorBody().string();
                    Log.v("Tag", "Error" + error);
                    mMoviesLatest.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mMoviesLatest.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getMoviesLatest() {
            return Servicey.getMoviesApi().latestMovies(
                    Configs.API_KEY
            );
        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

    private class RetrieveMoviesRunnableTopRated implements Runnable {
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnableTopRated(int pageNumber) {

            this.pageNumber = pageNumber;
            cancelRequest = false;
        }


        @Override
        public void run() {

            try {
                Response responseTopRated = getMoviesTopRated(pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (responseTopRated.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse) responseTopRated.body()).getMovies());
                    if (pageNumber == 1) {
                        // sending data to live data
                        // postvalue : background thread
                        // setvalue : not background thread

                        mMoviesTopRated.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMoviesTopRated.getValue();
                        currentMovies.addAll(list);
                        mMoviesTopRated.postValue(currentMovies);
                    }
                } else {
                    String error = responseTopRated.errorBody().string();
                    Log.v("Tag", "Error" + error);
                    mMoviesTopRated.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mMoviesTopRated.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getMoviesTopRated(int pageNumber) {
            return Servicey.getMoviesApi().topRatedMovies(
                    Configs.API_KEY,
                    pageNumber
            );
        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

}
