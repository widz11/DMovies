package com.dana.widyamass.dmovies.di.module;

import android.content.Context;

import com.dana.widyamass.dmovies.di.qualifier.ApplicationContext;
import com.dana.widyamass.dmovies.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
