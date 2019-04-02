package com.dana.widyamass.dmovies.ui.detail;

import android.content.Intent;

import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.data.model.MovieTrailerResponse;
import com.dana.widyamass.dmovies.data.model.MoviesResponse;

/**
 * Created by Widya Mas S on 4/1/2019.
 */

public interface DetailActivityMVP {
    interface DetailView {
        void showWait();

        void removeWait();

        void onFailure(String errorMessage);

        void onSuccess(MovieModel movieModel);

        void onSuccess(MovieTrailerResponse movieTrailerResponse);

        void openMovieTrailer(Intent intent);
    }
}
