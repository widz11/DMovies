package com.dana.widyamass.dmovies.ui.main;

import android.content.Intent;

import com.dana.widyamass.dmovies.data.model.MoviesResponse;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

public interface MainActivityMVP {
    interface mainView {
        void showWait();

        void removeWait();

        void onFailure(String errorMessage);

        void onSuccess(MoviesResponse moviesResponse);

        void moveToDetail(Intent intent);
    }
}
