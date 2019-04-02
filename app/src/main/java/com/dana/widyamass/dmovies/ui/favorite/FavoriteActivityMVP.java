package com.dana.widyamass.dmovies.ui.favorite;

import android.content.Intent;

import com.dana.widyamass.dmovies.data.model.MovieModel;

import java.util.List;

/**
 * Created by Widya Mas S on 4/2/2019.
 */

public interface FavoriteActivityMVP {
    interface FavoriteView {
        void showWait();

        void removeWait();

        void onFailure(String errorMessage);

        void onSuccess(List<MovieModel> movieModelList);

        void moveToDetail(Intent intent);
    }
}
