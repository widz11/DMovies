package com.dana.widyamass.dmovies.ui.favorite;

import android.app.Activity;
import android.content.Intent;

import com.dana.widyamass.dmovies.data.model.FavoriteMovieModel;
import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.dana.widyamass.dmovies.ui.detail.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Widya Mas S on 4/2/2019.
 */

public class FavoriteActivityPresenter {
    private Service service;
    private FavoriteActivityMVP.FavoriteView favoriteView;
    private List<MovieModel> favoriteListResponse;
    private CompositeSubscription compositeSubscription;
    private int counter;

    public FavoriteActivityPresenter(Service service, FavoriteActivityMVP.FavoriteView favoriteView) {
        this.service = service;
        this.favoriteView = favoriteView;
        this.compositeSubscription = new CompositeSubscription();
    }

    public void getFavoriteMovieList(final List<FavoriteMovieModel> favoriteMovieList) {
        favoriteView.showWait();

        if(favoriteMovieList.size() > 0) {
            favoriteListResponse = new ArrayList<>();
            counter = 1;
            for(final FavoriteMovieModel favoriteMovieModel: favoriteMovieList) {
                Subscription subscription = service.getMovieDetail(favoriteMovieModel.getMovieId(), new Service.MovieDetailCallback() {
                    @Override
                    public void onSuccess(MovieModel movieModel) {
                        favoriteListResponse.add(movieModel);
                        counter++;
                        if(favoriteMovieList.size() < counter) {
                            favoriteView.removeWait();
                            favoriteView.onSuccess(favoriteListResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        favoriteView.removeWait();
                        favoriteView.onFailure(throwable.toString());
                    }
                });
            }
        }
    }

    public void getMovieDetail(MovieModel movieModel, Activity mainActivity) {
        Intent intent = new Intent(mainActivity, DetailMovieActivity.class);
        intent.putExtra("idMovie", movieModel.getId());
        favoriteView.moveToDetail(intent);
    }

    public void onStop() {
        compositeSubscription.unsubscribe();
    }
}
