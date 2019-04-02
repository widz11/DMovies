package com.dana.widyamass.dmovies.ui.favorite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.dana.widyamass.dmovies.GlobalApplication;
import com.dana.widyamass.dmovies.R;
import com.dana.widyamass.dmovies.adapter.MoviesAdapter;
import com.dana.widyamass.dmovies.data.model.FavoriteMovieModel;
import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.database.DatabaseHelper;
import com.dana.widyamass.dmovies.di.component.DaggerFavoriteActivityComponent;
import com.dana.widyamass.dmovies.di.component.FavoriteActivityComponent;
import com.dana.widyamass.dmovies.di.module.FavoriteActvityContextModule;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.dana.widyamass.dmovies.ui.main.MainActivity;
import com.dana.widyamass.dmovies.utils.MovieClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends AppCompatActivity implements FavoriteActivityMVP.FavoriteView {
    private DatabaseHelper db;
    private List<FavoriteMovieModel> favoriteMovieModelList;
    FavoriteActivityPresenter presenter;
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;

    @Inject
    Service service;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Favorite Movies");

        FavoriteActivityComponent component = DaggerFavoriteActivityComponent.builder()
                .favoriteActvityContextModule(new FavoriteActvityContextModule(this))
                .applicationComponent(((GlobalApplication) getApplicationContext()).getApplicationComponent())
                .build();

        component.inject(this);

        recyclerView = findViewById(R.id.rv_list_favorite_movies);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        recyclerView.setLayoutManager(manager);

        loadData();
    }

    public void loadData() {
        db = new DatabaseHelper(this);
        favoriteMovieModelList = db.getAllFavorite();

        presenter = new FavoriteActivityPresenter(service, this);
        if(favoriteMovieModelList.size() > 0) {
            presenter.getFavoriteMovieList(favoriteMovieModelList);
        } else {
            removeWait();
        }
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
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
    public void onSuccess(List<MovieModel> movieModelList) {
        moviesAdapter = new MoviesAdapter(this, movieModelList, new MovieClickListener() {
            @Override
            public void onMovieClick(MovieModel movieModel) {
                presenter.getMovieDetail(movieModel, FavoriteActivity.this);
            }
        });
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
