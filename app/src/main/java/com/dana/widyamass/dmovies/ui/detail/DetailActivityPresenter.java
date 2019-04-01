package com.dana.widyamass.dmovies.ui.detail;

import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.dana.widyamass.dmovies.ui.main.MainActivityMVP;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Widya Mas S on 4/1/2019.
 */

public class DetailActivityPresenter {
    private Service service;
    private DetailActivityMVP.DetailView detailView;
    private CompositeSubscription compositeSubscription;

    public DetailActivityPresenter(Service service, DetailActivityMVP.DetailView detailView) {
        this.service = service;
        this.detailView = detailView;
        this.compositeSubscription = new CompositeSubscription();
    }

    public void getMovieDetail(int idMovie) {
        detailView.showWait();

        Subscription subscription = service.getMovieDetail(idMovie, new Service.MovieDetailCallback() {
            @Override
            public void onSuccess(MovieModel movieModel) {
                detailView.removeWait();
                detailView.onSuccess(movieModel);
            }

            @Override
            public void onError(Throwable throwable) {
                detailView.removeWait();
                detailView.onFailure(throwable.toString());
            }
        });
    }

    public void onStop() {
        compositeSubscription.unsubscribe();
    }
}
