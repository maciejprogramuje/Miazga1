package com.facebook.maciejprogramuje.miazga1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MovieDbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "miazgaMoviesDb";
    private static final String TABLE_MOVIES = "miazgaMovies";
    private static final String KEY_ID = "id";
    private static final String KEY_SEASON_NUMBER = "seasonNumber";
    private static final String KEY_SEASON_NAME = "seasonName";
    private static final String KEY_EPISODE_NUMBER = "episodeNumber";
    private static final String KEY_EPISODE_NAME = "episodeName";
    private static final String KEY_WATCHED = "watched";

    public MovieDbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SEASON_NUMBER + " TEXT,"
                + KEY_SEASON_NAME + " TEXT,"
                + KEY_EPISODE_NUMBER + " TEXT,"
                + KEY_EPISODE_NAME + " TEXT,"
                + KEY_WATCHED + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    public void addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SEASON_NUMBER, movie.get_seasonNumber());
        values.put(KEY_SEASON_NAME, movie.get_seasonName());
        values.put(KEY_EPISODE_NUMBER, movie.get_episodeNumber());
        values.put(KEY_EPISODE_NAME, movie.get_episodeName());
        values.put(KEY_WATCHED, movie.is_watched());

        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    public Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MOVIES,
                new String[]{
                        KEY_ID,
                        KEY_SEASON_NUMBER,
                        KEY_SEASON_NAME,
                        KEY_EPISODE_NUMBER,
                        KEY_EPISODE_NAME,
                        KEY_WATCHED
                },
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        Movie movie = new Movie(
                Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),
                Integer.parseInt(cursor.getString(3)),
                cursor.getString(4),
                Boolean.parseBoolean(cursor.getString(5))
        );

        cursor.close();

        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getString(4),
                        Boolean.parseBoolean(cursor.getString(5))
                );
                movies.add(movie);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return movies;
    }

    public int updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SEASON_NUMBER, movie.get_seasonNumber());
        values.put(KEY_SEASON_NAME, movie.get_seasonName());
        values.put(KEY_EPISODE_NUMBER, movie.get_episodeNumber());
        values.put(KEY_EPISODE_NAME, movie.get_episodeName());
        values.put(KEY_WATCHED, movie.is_watched());

        return db.update(TABLE_MOVIES,
                values,
                KEY_ID + " = ?",
                new String[]{
                        String.valueOf(movie.get_id())
                });
    }

    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES,
                KEY_ID + " = ?",
                new String[]{
                        String.valueOf(movie.get_id())
                });
        db.close();
    }

    public int getMoviesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
