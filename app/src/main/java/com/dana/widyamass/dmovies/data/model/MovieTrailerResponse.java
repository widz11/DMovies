package com.dana.widyamass.dmovies.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Widya Mas S on 4/2/2019.
 */

public class MovieTrailerResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("results")
    @Expose
    private ArrayList<MovieTrailerModel> results;

    public ArrayList<MovieTrailerModel> getResults() {
        return results;
    }
}
