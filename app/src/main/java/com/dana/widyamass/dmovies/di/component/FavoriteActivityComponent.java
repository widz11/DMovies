package com.dana.widyamass.dmovies.di.component;

import com.dana.widyamass.dmovies.di.module.FavoriteActvityContextModule;
import com.dana.widyamass.dmovies.di.scopes.ActivityScope;
import com.dana.widyamass.dmovies.ui.favorite.FavoriteActivity;

import dagger.Component;

/**
 * Created by Widya Mas S on 4/2/2019.
 */

@ActivityScope
@Component(modules = FavoriteActvityContextModule.class, dependencies = ApplicationComponent.class)
public interface FavoriteActivityComponent {
    void inject(FavoriteActivity favoriteActivity);
}
