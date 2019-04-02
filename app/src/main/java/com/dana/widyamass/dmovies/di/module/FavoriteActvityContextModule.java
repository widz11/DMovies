package com.dana.widyamass.dmovies.di.module;

import android.content.Context;

import com.dana.widyamass.dmovies.retrofit.MovieApiInterface;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.dana.widyamass.dmovies.ui.favorite.FavoriteActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Widya Mas S on 4/2/2019.
 */

@Module
public class FavoriteActvityContextModule {
    private FavoriteActivity favoriteActivity;

    public FavoriteActvityContextModule(FavoriteActivity favoriteActivity) {
        this.favoriteActivity = favoriteActivity;
    }

    public  FavoriteActivity getFavoriteActivity() {
        return favoriteActivity;
    }

    @Provides
    @SuppressWarnings("unused")
    public Service provideService(MovieApiInterface movieApiInterface) {
        return new Service(movieApiInterface);
    }
}
