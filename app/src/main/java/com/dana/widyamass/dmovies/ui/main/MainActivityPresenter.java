package com.dana.widyamass.dmovies.ui.main;

import android.app.Activity;
import android.content.Intent;

import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.data.model.MoviesResponse;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.dana.widyamass.dmovies.ui.detail.DetailMovieActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

public class MainActivityPresenter {
    private Service service;
    private MainActivityMVP.mainView mainView;
    private CompositeSubscription compositeSubscription;

    public MainActivityPresenter(Service service, MainActivityMVP.mainView mainView) {
        this.service = service;
        this.mainView = mainView;
        this.compositeSubscription = new CompositeSubscription();
    }

    public void getMovieList(int currentPage) {
        mainView.showWait();

        Subscription subscription = service.getMovieList(currentPage, new Service.MovieListCallback() {
            @Override
            public void onSuccess(MoviesResponse moviesResponse) {
                mainView.removeWait();
                mainView.onSuccess(moviesResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                mainView.removeWait();
                mainView.onFailure(throwable.toString());
            }
        });
    }

    public void getTopRateMovieList(int currentPage) {
        mainView.showWait();

        Subscription subscription = service.getTopRateMovieList(currentPage, new Service.MovieListCallback() {
            @Override
            public void onSuccess(MoviesResponse moviesResponse) {
                mainView.removeWait();
                mainView.onSuccess(moviesResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                mainView.removeWait();
                mainView.onFailure(throwable.toString());
            }
        });
    }

    public void onStop() {
        compositeSubscription.unsubscribe();
    }

    public void getMovieDetail(MovieModel movieModel, Activity mainActivity) {
        Intent intent = new Intent(mainActivity, DetailMovieActivity.class);
        intent.putExtra("idMovie", movieModel.getId());
        mainView.moveToDetail(intent);
    }
}
