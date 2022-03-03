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

    private MutableLiveData<List<Movie>> mMovies;

    private static MovieApiClient instance;


    private RetrieveMoviesRunnable retrieveMoviesRunnable;


    public static MovieApiClient getInstance() {

        if (instance == null) {
            instance = new MovieApiClient();
        }

        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getmMovies() {
        return mMovies;
    }




    public void searchMoviesApi(String query, int pageNumber) {

        if (retrieveMoviesRunnable != null){
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

            try{
                Response response = getMovies(query, pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200){
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if (pageNumber == 1){
                        // sending data to live data
                        // postvalue : background thread
                        // setvalue : not background thread

                        mMovies.postValue(list);
                    }
                    else{
                        List<Movie> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }
                else {
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

}
