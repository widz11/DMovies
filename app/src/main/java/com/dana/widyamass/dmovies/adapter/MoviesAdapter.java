package com.dana.widyamass.dmovies.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dana.widyamass.dmovies.BuildConfig;
import com.dana.widyamass.dmovies.R;
import com.dana.widyamass.dmovies.data.model.MovieModel;
import com.dana.widyamass.dmovies.ui.main.MainActivity;
import com.dana.widyamass.dmovies.utils.MovieClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Widya Mas S on 3/30/2019.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private final List<MovieModel> movieModels;
    private final MovieClickListener movieClickListener;
    private final Context context;

    public MoviesAdapter(MainActivity context, List<MovieModel> movieModels, MovieClickListener movieClickListener) {
        this.context = context;
        this.movieModels = movieModels;
        this.movieClickListener = movieClickListener;
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        MovieModel movie = this.movieModels.get(position);
        holder.bind(movie, movieClickListener);
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_movie)
        ImageView mImageMovie;

        @BindView(R.id.cv_movie_card)
        CardView mMovieCard;

        @BindView(R.id.progress)
        ProgressBar progressBar;

//        private final String imagePath = "https://image.tmdb.org/t/p/w500/";

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MovieModel movieModel, final MovieClickListener movieClickListener) {
            mMovieCard.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth()/2, getMeasuredPosterHeight(getScreenWidth()/2)));

            Picasso.with(mImageMovie.getContext()).load(BuildConfig.IMGBASEURL + movieModel.getPosterPath()).fit().centerCrop().into(mImageMovie, new Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                    progressBar.setVisibility(View.GONE);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieClickListener.onMovieClick(movieModel);
                }
            });
        }

        private int getScreenWidth() {
            return Resources.getSystem().getDisplayMetrics().widthPixels;
        }
        private int getMeasuredPosterHeight(int width) {
            return (int) (width * 1.5f);
        }

    }

    public void swapData(Collection<MovieModel> items) {
        if(items != null) {
            movieModels.addAll(items);
        }
        notifyDataSetChanged();
    }
}
