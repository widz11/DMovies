package com.dana.widyamass.dmovies.data.model;

/**
 * Created by Widya Mas S on 4/2/2019.
 */

public class FavoriteMovieModel {
    public static final String TABLE_NAME = "favorite_movie";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ID_MOVIE = "movie_id";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private int movieId;
    private String timestamp;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ID_MOVIE + " INTEGER,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public FavoriteMovieModel(int id, int movieId, String timestamp) {
        this.id = id;
        this.movieId = movieId;
        this.timestamp = timestamp;
    }

    public FavoriteMovieModel() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
