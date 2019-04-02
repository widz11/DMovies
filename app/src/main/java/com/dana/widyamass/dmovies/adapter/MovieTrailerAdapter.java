package com.dana.widyamass.dmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dana.widyamass.dmovies.R;
import com.dana.widyamass.dmovies.data.model.MovieTrailerModel;
import com.dana.widyamass.dmovies.data.model.MovieTrailerResponse;
import com.dana.widyamass.dmovies.utils.MovieTrailerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Widya Mas S on 4/2/2019.
 */

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.ViewHolder> {
    private final List<MovieTrailerModel> movieTrailerModels, movieOnlyTrailerModels;
    private final MovieTrailerClickListener movieTrailerClickListener;

    public MovieTrailerAdapter(List<MovieTrailerModel> movieTrailerModels, MovieTrailerClickListener movieTrailerClickListener) {
        this.movieTrailerClickListener = movieTrailerClickListener;
        this.movieTrailerModels = movieTrailerModels;
        this.movieOnlyTrailerModels = new ArrayList<>(0);
        if(this.movieTrailerModels.size() > 0) {
            for (MovieTrailerModel movieTrailerModel : this.movieTrailerModels) {
                if(movieTrailerModel.getType().equals("Trailer")) {
                    this.movieOnlyTrailerModels.add(movieTrailerModel);
                }
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie_trailers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieTrailerAdapter.ViewHolder holder, int position) {
        MovieTrailerModel movieTrailerModel = this.movieOnlyTrailerModels.get(position);
        holder.bind(movieTrailerModel, movieTrailerClickListener);
    }

    @Override
    public int getItemCount() {
        return movieOnlyTrailerModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_detail_movie_trailer)
        TextView mDetailMovieTrailer;

        @BindView(R.id.progress)
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final MovieTrailerModel movieTrailerModel, final MovieTrailerClickListener movieTrailerClickListener) {
            mDetailMovieTrailer.setText(movieTrailerModel.getName());
            this.progressBar.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieTrailerClickListener.onMovieTrailerClick(movieTrailerModel);
                }
            });
        }
    }
}
