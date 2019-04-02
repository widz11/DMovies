package com.dana.widyamass.dmovies.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dana.widyamass.dmovies.data.model.FavoriteMovieModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Widya Mas S on 4/2/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_movie";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FavoriteMovieModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteMovieModel.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public long insertFavorite(int movieId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoriteMovieModel.COLUMN_ID_MOVIE, movieId);

        long id = sqLiteDatabase.insert(FavoriteMovieModel.TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();

        return id;
    }

    public FavoriteMovieModel getFavorite(int movieId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        FavoriteMovieModel favoriteMovieModel = new FavoriteMovieModel();

        Cursor cursor = sqLiteDatabase.query(FavoriteMovieModel.TABLE_NAME,
                new String[]{FavoriteMovieModel.COLUMN_ID, FavoriteMovieModel.COLUMN_ID_MOVIE,
                FavoriteMovieModel.COLUMN_TIMESTAMP},
                FavoriteMovieModel.COLUMN_ID_MOVIE + "=?",
                new String[]{String.valueOf(movieId)}, null, null, null, null);
        Log.d("Count",String.valueOf(cursor.getCount()));
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            favoriteMovieModel = new FavoriteMovieModel(
                    cursor.getInt(cursor.getColumnIndex(FavoriteMovieModel.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(FavoriteMovieModel.COLUMN_ID_MOVIE)),
                    cursor.getString(cursor.getColumnIndex(FavoriteMovieModel.COLUMN_TIMESTAMP))
            );
        }

        cursor.close();

        return favoriteMovieModel;
    }

    public List<FavoriteMovieModel> getAllFavorite() {
        List<FavoriteMovieModel> favoriteList = new ArrayList<>();

        String query = "SELECT * FROM " + FavoriteMovieModel.TABLE_NAME + " ORDER BY "
                + FavoriteMovieModel.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.getCount() > 0) {
            if(cursor.moveToFirst()) {
                do {
                    FavoriteMovieModel favoriteMovieModel = new FavoriteMovieModel();
                    favoriteMovieModel.setId(cursor.getInt(cursor.getColumnIndex(FavoriteMovieModel.COLUMN_ID)));
                    favoriteMovieModel.setMovieId(cursor.getInt(cursor.getColumnIndex(FavoriteMovieModel.COLUMN_ID_MOVIE)));
                    favoriteMovieModel.setTimestamp(cursor.getString(cursor.getColumnIndex(FavoriteMovieModel.COLUMN_TIMESTAMP)));

                    favoriteList.add(favoriteMovieModel);
                } while (cursor.moveToNext());
            }
        }

        sqLiteDatabase.close();

        return favoriteList;
    }

    public void deleteFavorite(int movieId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(FavoriteMovieModel.TABLE_NAME, FavoriteMovieModel.COLUMN_ID_MOVIE + " = ?",
                new String[]{String.valueOf(movieId)});
        sqLiteDatabase.close();
    }
}
