package com.dana.widyamass.dmovies.di.component;

import com.dana.widyamass.dmovies.di.module.DetailActivityContextModule;
import com.dana.widyamass.dmovies.di.scopes.ActivityScope;
import com.dana.widyamass.dmovies.ui.detail.DetailMovieActivity;

import dagger.Component;

/**
 * Created by Widya Mas S on 4/1/2019.
 */

@ActivityScope
@Component(modules = DetailActivityContextModule.class, dependencies = ApplicationComponent.class)
public interface DetailActivityComponent {
    void inject(DetailMovieActivity detailMovieActivity);
}
