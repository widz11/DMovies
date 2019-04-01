package com.dana.widyamass.dmovies.di.module;

import android.content.Context;

import com.dana.widyamass.dmovies.retrofit.MovieApiInterface;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.dana.widyamass.dmovies.ui.main.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public  MainActivity getMainActivity() {
        return mainActivity;
    }

    @Provides
    @SuppressWarnings("unused")
    public Service provideService(MovieApiInterface movieApiInterface) {
        return new Service(movieApiInterface);
    }
}
