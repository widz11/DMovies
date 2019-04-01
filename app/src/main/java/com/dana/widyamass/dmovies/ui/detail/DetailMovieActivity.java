package com.dana.widyamass.dmovies.ui.detail;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dana.widyamass.dmovies.BuildConfig;
import com.dana.widyamass.dmovies.GlobalApplication;
import com.dana.widyamass.dmovies.R;
import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.di.component.DaggerDetailActivityComponent;
import com.dana.widyamass.dmovies.di.component.DaggerMainActivityComponent;
import com.dana.widyamass.dmovies.di.component.DetailActivityComponent;
import com.dana.widyamass.dmovies.di.component.MainActivityComponent;
import com.dana.widyamass.dmovies.di.module.DetailActivityContextModule;
import com.dana.widyamass.dmovies.di.module.MainActivityContextModule;
import com.dana.widyamass.dmovies.retrofit.Service;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity implements DetailActivityMVP.DetailView{
    private MovieModel movieModel;
    private DetailActivityPresenter detailActivityPresenter;
    private int idMovie;

    @Inject
    Service service;

    @BindView(R.id.tv_detail_movie_title)
    TextView mDetailMovieTitle;

    @BindView(R.id.tv_detail_movie_date)
    TextView mDetailMovieDate;

    @BindView(R.id.tv_detail_movie_duration)
    TextView mDetailMovieDuration;

    @BindView(R.id.tv_detail_movie_rate)
    TextView mDetailMovieRate;

    @BindView(R.id.tv_detail_movie_overview)
    TextView mDetailMovieOverview;

    @BindView(R.id.img_detail_movie_poster)
    ImageView imgDetailMoviePoster;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Movie Detail");
        idMovie = getIntent().getIntExtra("idMovie", 0);

        DetailActivityComponent component = DaggerDetailActivityComponent.builder()
                .detailActivityContextModule(new DetailActivityContextModule(this))
                .applicationComponent(((GlobalApplication) getApplicationContext()).getApplicationComponent())
                .build();

        component.inject(this);

        if(idMovie != 0) {
            detailActivityPresenter = new DetailActivityPresenter(service, this);
            detailActivityPresenter.getMovieDetail(idMovie);
        }
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.i("OnFailure:", errorMessage);
    }

    @Override
    public void onSuccess(MovieModel movieModel) {
        Picasso.with(imgDetailMoviePoster.getContext()).load(BuildConfig.IMGBASEURL + movieModel.getPosterPath()).fit().centerCrop().into(imgDetailMoviePoster);
        mDetailMovieTitle.setText(movieModel.getOriginalTitle());
        mDetailMovieDate.setText(movieModel.getReleaseDate());
        mDetailMovieDuration.setText(String.format("%sm", Integer.toString(movieModel.getRuntime())));
        mDetailMovieRate.setText(String.format("%s/10",Double.toString(movieModel.getVoteAverage())));
        mDetailMovieOverview.setText(movieModel.getOverview());
    }
}
