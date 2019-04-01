package com.dana.widyamass.dmovies.di.component;

import com.dana.widyamass.dmovies.di.module.MainActivityContextModule;
import com.dana.widyamass.dmovies.di.scopes.ActivityScope;
import com.dana.widyamass.dmovies.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

@ActivityScope
@Component(modules = MainActivityContextModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
