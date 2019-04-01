package com.dana.widyamass.dmovies.retrofit;

import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.data.model.MoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

public interface MovieApiInterface {
    @GET("movie/popular")
    Observable<MoviesResponse> getPopularMovies(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRateMovies(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<MovieModel> getMovieDetail(@Path("id") int idMovie, @Query("api_key") String apiKey);
}
