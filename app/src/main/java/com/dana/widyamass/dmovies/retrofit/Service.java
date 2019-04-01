package com.dana.widyamass.dmovies.retrofit;

import com.dana.widyamass.dmovies.BuildConfig;
import com.dana.widyamass.dmovies.data.model.MoviesResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Widya Mas S on 3/31/2019.
 */

public class Service {
    private MovieApiInterface movieApiInterface;
    private  final String API_KEY = BuildConfig.API_KEY;

    public Service(MovieApiInterface movieApiInterface) {
        this.movieApiInterface = movieApiInterface;
    }

    public Subscription getMovieList(int page, final MovieListCallback callback) {
        return movieApiInterface.getPopularMovies(page, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MoviesResponse>>() {
                    @Override
                    public Observable<? extends MoviesResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MoviesResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getCause());
                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        callback.onSuccess(moviesResponse);
                    }
                });
    }

    public Subscription getTopRateMovieList(int page, final MovieListCallback callback) {
        return movieApiInterface.getTopRateMovies(page, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MoviesResponse>>() {
                    @Override
                    public Observable<? extends MoviesResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MoviesResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getCause());
                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        callback.onSuccess(moviesResponse);
                    }
                });
    }

    public interface MovieListCallback {
        void onSuccess(MoviesResponse moviesResponse);

        void onError(Throwable throwable);
    }
}