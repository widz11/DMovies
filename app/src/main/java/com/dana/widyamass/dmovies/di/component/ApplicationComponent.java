package com.dana.widyamass.dmovies.di.component;

import com.dana.widyamass.dmovies.di.module.ContextModule;
import com.dana.widyamass.dmovies.di.module.RetrofitModule;
import com.dana.widyamass.dmovies.di.scopes.ApplicationScope;
import com.dana.widyamass.dmovies.retrofit.MovieApiInterface;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

@ApplicationScope
@Component(modules = {RetrofitModule.class, ContextModule.class})
public interface ApplicationComponent {
    MovieApiInterface getMovieService();
}
