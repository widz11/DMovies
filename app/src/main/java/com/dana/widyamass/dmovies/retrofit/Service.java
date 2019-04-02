package com.dana.widyamass.dmovies.retrofit;

import com.dana.widyamass.dmovies.BuildConfig;
import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.data.model.MovieTrailerResponse;
import com.dana.widyamass.dmovies.data.model.MoviesResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.observers.SafeSubscriber;
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

    public Subscription getMovieDetail(int idMovie, final MovieDetailCallback callback){
        return movieApiInterface.getMovieDetail(idMovie, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieModel>>() {
                    @Override
                    public Observable<? extends MovieModel> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MovieModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getCause());
                    }

                    @Override
                    public void onNext(MovieModel movieModel) {
                        callback.onSuccess(movieModel);
                    }
                });
    }

    public Subscription getMovieTrailers(int idMovie, final MovieTrailersCallback callback) {
        return movieApiInterface.getMovieTrailers(idMovie, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieTrailerResponse>>() {
                    @Override
                    public Observable<? extends MovieTrailerResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MovieTrailerResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getCause());
                    }

                    @Override
                    public void onNext(MovieTrailerResponse movieTrailerResponse) {
                        callback.onSuccess(movieTrailerResponse);
                    }
                });
    }

    public interface MovieListCallback {
        void onSuccess(MoviesResponse moviesResponse);

        void onError(Throwable throwable);
    }

    public interface MovieDetailCallback {
        void onSuccess(MovieModel movieModel);

        void onError(Throwable throwable);
    }

    public interface MovieTrailersCallback {
        void onSuccess(MovieTrailerResponse movieTrailerResponse);

        void onError(Throwable throwable);
    }
}
