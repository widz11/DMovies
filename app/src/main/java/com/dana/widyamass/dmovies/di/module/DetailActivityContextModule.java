package com.dana.widyamass.dmovies.di.module;

import com.dana.widyamass.dmovies.retrofit.MovieApiInterface;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.dana.widyamass.dmovies.ui.detail.DetailMovieActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Widya Mas S on 4/1/2019.
 */

@Module
public class DetailActivityContextModule {
    private DetailMovieActivity detailMovieActivity;

    public DetailActivityContextModule(DetailMovieActivity detailMovieActivity) {
        this.detailMovieActivity = detailMovieActivity;
    }

    public DetailMovieActivity getDetailMovieActivity() {
        return detailMovieActivity;
    }

    @Provides
    @SuppressWarnings("unused")
    public Service provideService(MovieApiInterface movieApiInterface) {
        return new Service(movieApiInterface);
    }
}
