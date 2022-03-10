package com.example.android.movieflix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.movieflix.adaptors.MovieAdapter;
import com.example.android.movieflix.adaptors.OnMovieListener;
import com.example.android.movieflix.models.Movie;
import com.example.android.movieflix.viewmodels.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMovieListener {

    private RecyclerView recyclerView;
    private MovieAdapter movieRecyclerAdapter;
    private MovieListViewModel movieListViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetupSearchView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        configureRecyclerView();
        observeAnyChange();
        observePopularMovies();
        observeLatestMovies();
        observeTopRatedMovies();


        movieListViewModel.searchMovieApiPopular(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_popular:
                movieListViewModel.searchMovieApiPopular(1);
                break;

            case R.id.item_topRated:
                movieListViewModel.searchMovieApiTopRated(1);
                break;

            case R.id.item_upComing:
                movieListViewModel.searchMovieApiUpComing(1);
                break;

            case R.id.item_favorite:
                movieListViewModel.getFavorites().observe(this, movies -> {
                        movieRecyclerAdapter.setmMovies(movies);
                        recyclerView.setAdapter(movieRecyclerAdapter);

                });
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }

    private void observeTopRatedMovies() {
        movieListViewModel.getMoviesTopRated().observe(this, movies -> {
            if (movies != null) {
                for (Movie movie : movies) {
                    movieRecyclerAdapter.setmMovies(movies);
                }
            }
        });
    }

    private void observeLatestMovies() {

        movieListViewModel.getMoviesLatest().observe(this, movies -> {
            if (movies != null) {
                for (Movie movie : movies) {
                    movieRecyclerAdapter.setmMovies(movies);
                }
            }
        });
    }

    private void observePopularMovies() {
        movieListViewModel.getMoviesPopular().observe(this, movies -> {
            if (movies != null) {
                for (Movie movie : movies) {
                    movieRecyclerAdapter.setmMovies(movies);
                }
            }
        });

    }

    private void observeAnyChange() {

        movieListViewModel.getMovies().observe(this, movies -> {
            if (movies != null) {
                for (Movie movie : movies) {
                    movieRecyclerAdapter.setmMovies(movies);
                }
            }
        });
    }


    private void configureRecyclerView(){
        movieRecyclerAdapter = new MovieAdapter(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieRecyclerAdapter);



    }


    @Override
    public void OnMovieClick(int position) {

        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void OnCategoryClick(String category) {

    }

    //SearchView
    private void SetupSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}

