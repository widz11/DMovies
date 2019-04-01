package com.dana.widyamass.dmovies.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Widya Mas S on 3/30/2019.
 */

public class MoviesResponse {
    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    @SerializedName("results")
    @Expose
    public List<MovieModel> results;

    public List<MovieModel> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPage() {
        return page;
    }
}
