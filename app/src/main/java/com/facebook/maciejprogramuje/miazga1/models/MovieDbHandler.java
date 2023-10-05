package com.facebook.maciejprogramuje.miazga1.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MovieDbHandler extends SQLiteOpenHelper {
    // **********************************************************************
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "miazgaMoviesDb";
    // **********************************************************************
    private static final String TABLE_SEASONS = "seasonsTable";
    private static final String KEY_SEASON_ID = "id";
    private static final String KEY_SEASON_NUMBER = "seasonNumber";
    private static final String KEY_SEASON_NAME = "seasonName";
    // **********************************************************************
    private static final String TABLE_EPISODES = "episodesTable";
    private static final String KEY_EPISODE_ID = "id";
    private static final String KEY_EPISODE_NUMBER = "episodeNumber";
    private static final String KEY_EPISODE_NAME = "episodeName";
    private static final String KEY_EPISODE_WATCHED = "watched";
    private static final String KEY_EPISODE_FK_SEASON = "seasonFK";
    // **********************************************************************

    public MovieDbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //todo
    public void fillDatabase() {
        for (int i = 0; i < 10; i++) {
            addSeason(new Season(i, "Sezon " + i));

            for (int j = 0; j < 10; j++) {
                addEpisode(new Episode(j, "Epizod " + j, false, i));
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SEASONS_TABLE = "CREATE TABLE " + TABLE_SEASONS + "("
                + KEY_SEASON_ID + " INTEGER PRIMARY KEY,"
                + KEY_SEASON_NUMBER + " TEXT,"
                + KEY_SEASON_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_SEASONS_TABLE);

        String CREATE_EPISODES_TABLE = "CREATE TABLE " + TABLE_EPISODES + "("
                + KEY_EPISODE_ID + " INTEGER PRIMARY KEY,"
                + KEY_EPISODE_NUMBER + " TEXT,"
                + KEY_EPISODE_WATCHED + " TEXT,"
                + KEY_EPISODE_NAME + " TEXT,"
                + KEY_EPISODE_FK_SEASON + " TEXT"
                + ")";
        db.execSQL(CREATE_EPISODES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEASONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EPISODES);

        onCreate(db);
    }

    public void addSeason(Season season) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SEASON_NUMBER, season.getSeasonNumber());
        values.put(KEY_SEASON_NAME, season.getSeasonName());

        db.insert(TABLE_SEASONS, null, values);
        db.close();
    }

    public void addEpisode(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EPISODE_NUMBER, episode.getEpisodeNumber());
        values.put(KEY_EPISODE_NAME, episode.getEpisodeName());
        values.put(KEY_EPISODE_WATCHED, episode.isWatched());
        values.put(KEY_EPISODE_FK_SEASON, episode.getSeasonFK());

        db.insert(TABLE_EPISODES, null, values);
        db.close();
    }

    public Season getSeason(int seasonNumber) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SEASONS, new String[]{
                        KEY_SEASON_ID,
                        KEY_SEASON_NUMBER,
                        KEY_SEASON_NAME
                },
                KEY_SEASON_NUMBER + "=?",
                new String[]{String.valueOf(seasonNumber)},
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        Season season = new Season(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1)
        );

        cursor.close();

        return season;
    }

    public Episode getEpisode(int episodeNumber) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EPISODES, new String[]{
                        KEY_EPISODE_ID,
                        KEY_EPISODE_NUMBER,
                        KEY_EPISODE_NAME,
                        KEY_EPISODE_WATCHED,
                        KEY_EPISODE_FK_SEASON
                },
                KEY_EPISODE_NUMBER + "=?",
                new String[]{String.valueOf(episodeNumber)},
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        Episode episode = new Episode(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Boolean.parseBoolean(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3))
        );

        cursor.close();

        return episode;
    }

    public List<Season> getAllSeasons() {
        List<Season> seasons = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_SEASONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Season season = new Season(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1)
                );
                seasons.add(season);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return seasons;
    }

    public List<Episode> getAllEpisodes() {
        List<Episode> episodes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_EPISODES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Episode episode = new Episode(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Boolean.parseBoolean(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))
                );
                episodes.add(episode);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return episodes;
    }

    public int updateSeason(Season season) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SEASON_NUMBER, season.getSeasonNumber());
        values.put(KEY_SEASON_NAME, season.getSeasonName());

        return db.update(TABLE_SEASONS,
                values,
                KEY_SEASON_NUMBER + " = ?",
                new String[]{
                        String.valueOf(season.getSeasonNumber())
                });
    }

    public int updateEpisode(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EPISODE_NUMBER, episode.getEpisodeNumber());
        values.put(KEY_EPISODE_NAME, episode.getEpisodeName());

        return db.update(TABLE_EPISODES,
                values,
                KEY_EPISODE_NUMBER + " = ?",
                new String[]{
                        String.valueOf(episode.getEpisodeNumber())
                });
    }

    public void deleteSeason(Season season) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SEASONS,
                KEY_SEASON_NUMBER + " = ?",
                new String[]{
                        String.valueOf(season.getSeasonNumber())
                });

        db.delete(TABLE_EPISODES,
                KEY_EPISODE_FK_SEASON + " = ?",
                new String[]{
                        String.valueOf(season.getSeasonNumber())
                });

        db.close();
    }

    public void deleteEpisode(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_EPISODES,
                KEY_EPISODE_NUMBER + " = ?",
                new String[]{
                        String.valueOf(episode.getEpisodeNumber())
                });

        db.close();
    }

    public List<Episode> getAllEpisodesFromSeason(Season season) {
        List<Episode> episodes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_EPISODES + " WHERE " + KEY_EPISODE_FK_SEASON + " = " + season.getSeasonNumber();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Episode episode = new Episode(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Boolean.parseBoolean(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3))
                );
                episodes.add(episode);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return episodes;
    }

    public int getSeasonsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SEASONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int getEpisodesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EPISODES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
