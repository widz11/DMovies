package com.dana.widyamass.dmovies;

import android.app.Application;

import com.dana.widyamass.dmovies.di.component.ApplicationComponent;
import com.dana.widyamass.dmovies.di.component.DaggerApplicationComponent;
import com.dana.widyamass.dmovies.di.module.ContextModule;
import com.dana.widyamass.dmovies.retrofit.MovieApiInterface;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

public class GlobalApplication extends Application {
    ApplicationComponent applicationComponent;

    MovieApiInterface movieApiInterface;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        movieApiInterface = applicationComponent.getMovieService();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
