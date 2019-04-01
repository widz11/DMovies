package com.dana.widyamass.dmovies.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Widya Mas S on 3/30/2019.
 */

public class MovieModel {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    @SerializedName("video")
    @Expose
    private boolean video;

    @SerializedName("genres")
    @Expose
    private List<Integer> genres;

    public String getPosterPath() {
        return posterPath;
    }
}
