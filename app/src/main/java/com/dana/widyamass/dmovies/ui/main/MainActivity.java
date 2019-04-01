package com.dana.widyamass.dmovies.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.dana.widyamass.dmovies.GlobalApplication;
import com.dana.widyamass.dmovies.R;
import com.dana.widyamass.dmovies.adapter.MoviesAdapter;
import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.data.model.MoviesResponse;
import com.dana.widyamass.dmovies.di.component.DaggerMainActivityComponent;
import com.dana.widyamass.dmovies.di.component.MainActivityComponent;
import com.dana.widyamass.dmovies.di.module.MainActivityContextModule;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.dana.widyamass.dmovies.utils.MovieClickListener;
import com.dana.widyamass.dmovies.utils.RecyclerViewScrollListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.mainView{
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private static int totalPages = 0;
    private static int currentSortMode = 1;
    private MainActivityPresenter presenter;

    @Inject
    Service service;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Popular Movies");

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(((GlobalApplication) getApplicationContext()).getApplicationComponent())
                .build();

        component.inject(this);

        recyclerView = findViewById(R.id.rv_list_movies);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        recyclerView.setLayoutManager(manager);

        loadPage(1);

        RecyclerViewScrollListener scrollListener = new RecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemCount, RecyclerView view) {
                if((page + 1) <= totalPages) {
                    if(currentSortMode == 1) {
                        presenter.getMovieList(page + 1);
                    } else if(currentSortMode == 2) {
                        presenter.getTopRateMovieList(page + 1);
                    }
                    loadPage(page + 1);
                }
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
    }

    private void loadPage(final int page) {
        presenter = new MainActivityPresenter(service, this);

        switch (currentSortMode) {
            case 1:
                getSupportActionBar().setTitle("Popular Movies");
                presenter.getMovieList(page);
                break;
            case 2:
                getSupportActionBar().setTitle("Top Rated Movies");
                presenter.getTopRateMovieList(page);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_three_dots, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_popular:
                currentSortMode = 1;
                break;
            case R.id.item_top_rated:
                currentSortMode = 2;
                break;
            case R.id.item_favorite:
                return true;
        }
        loadPage(1);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.i("OnFailure:", errorMessage);
    }

    @Override
    public void onSuccess(MoviesResponse moviesResponse) {
        if(moviesResponse.getPage() == 1) {
            totalPages = moviesResponse.getTotalPages();
            moviesAdapter = new MoviesAdapter(this, moviesResponse.getResults(), new MovieClickListener() {
                @Override
                public void onMovieClick(MovieModel movieModel) {
                    presenter.getMovieDetail(movieModel, MainActivity.this);
                }
            });
            recyclerView.setAdapter(moviesAdapter);
        } else {
            moviesAdapter.swapData(moviesResponse.getResults());
        }

    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
    }
}
