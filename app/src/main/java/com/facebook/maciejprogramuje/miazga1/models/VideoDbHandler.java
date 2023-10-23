package com.facebook.maciejprogramuje.miazga1.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VideoDbHandler extends SQLiteOpenHelper {
    // **********************************************************************
    private static final int DATABASE_VERSION = 3;
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

    public VideoDbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void fillDatabase(List<Video> videos) {
        deleteDataFromTables();

        for (Video video : videos) {
            if (getSeasonByNumber(video.getSeasonNumber()) == null) {
                Season season = new Season(video.getSeasonNumber(), "Sezon nr." + video.getSeasonNumber());
                addSeason(season);
            }

            if (getEpisodeByNumbers(video.getEpisodeNumber(), video.getSeasonNumber()) == null) {
                Episode episode = new Episode(video.getEpisodeNumber(), "Ep." + video.getName(), video.getSeasonNumber());
                addEpisode(episode);
            }
        }
    }

    private void deleteDataFromTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_SEASONS);
        db.execSQL("delete from " + TABLE_EPISODES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SEASONS_TABLE = "CREATE TABLE " + TABLE_SEASONS + "("
                + KEY_SEASON_ID + " INTEGER PRIMARY KEY,"
                + KEY_SEASON_NUMBER + " INTEGER UNIQUE,"
                + KEY_SEASON_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_SEASONS_TABLE);

        String CREATE_EPISODES_TABLE = "CREATE TABLE " + TABLE_EPISODES + "("
                + KEY_EPISODE_ID + " INTEGER PRIMARY KEY,"
                + KEY_EPISODE_NUMBER + " INTEGER,"
                + KEY_EPISODE_NAME + " TEXT UNIQUE,"
                + KEY_EPISODE_WATCHED + " INTEGER,"
                + KEY_EPISODE_FK_SEASON + " INTEGER"
                + ")";
        db.execSQL(CREATE_EPISODES_TABLE);

        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEASONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EPISODES);

        onCreate(db);

        //db.close();
    }

    public void addSeason(Season season) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SEASON_NUMBER, season.getSeasonNumber());
        values.put(KEY_SEASON_NAME, season.getSeasonName());

        db.insertWithOnConflict(TABLE_SEASONS, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        //db.close();
    }

    public void addEpisode(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EPISODE_NUMBER, episode.getEpisodeNumber());
        values.put(KEY_EPISODE_NAME, episode.getEpisodeName());
        values.put(KEY_EPISODE_WATCHED, episode.isWatched());
        values.put(KEY_EPISODE_FK_SEASON, episode.getSeasonFK());

        db.insertWithOnConflict(TABLE_EPISODES, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public Season getSeason(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SEASONS, new String[]{
                        KEY_SEASON_ID,
                        KEY_SEASON_NUMBER,
                        KEY_SEASON_NAME
                },
                KEY_SEASON_ID + "=?",
                new String[]{String.valueOf(id + 1)},
                null,
                null,
                null,
                null);

        Season season = null;

        if (cursor != null && cursor.moveToFirst()) {
            season = new Season(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );
            cursor.close();
        }

        //db.close();

        return season;
    }

    public Season getSeasonByNumber(int seasonNumber) {
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

        Season season = null;

        if (cursor != null && cursor.moveToFirst()) {
            season = new Season(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );
            cursor.close();
        }

        //db.close();

        return season;
    }

    public Episode getEpisode(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EPISODES, new String[]{
                        KEY_EPISODE_ID,
                        KEY_EPISODE_NUMBER,
                        KEY_EPISODE_NAME,
                        KEY_EPISODE_WATCHED,
                        KEY_EPISODE_FK_SEASON
                },
                KEY_EPISODE_ID + "=?",
                new String[]{String.valueOf(id + 1)},
                null,
                null,
                null,
                null);

        Episode episode = null;

        if (cursor != null && cursor.moveToFirst()) {
            episode = new Episode(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            );
            cursor.close();
        }

        return episode;
    }

    public Episode getEpisodeByNumbers(int episodeNumber, int seasonNumber) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EPISODES, new String[]{
                        KEY_EPISODE_ID,
                        KEY_EPISODE_NUMBER,
                        KEY_EPISODE_NAME,
                        KEY_EPISODE_WATCHED,
                        KEY_EPISODE_FK_SEASON
                },
                KEY_EPISODE_NUMBER + "=? AND " + KEY_EPISODE_FK_SEASON + "=?",
                new String[]{String.valueOf(episodeNumber), String.valueOf(seasonNumber)},
                null,
                null,
                null,
                null);

        Episode episode = null;

        if (cursor != null && cursor.moveToFirst()) {
            episode = new Episode(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            );
            cursor.close();
        }

        return episode;
    }

    public List<Season> getAllSeasons() {
        List<Season> seasons = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_SEASONS + " ORDER BY " + KEY_SEASON_NUMBER + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Season season = new Season(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2)
                );
                seasons.add(season);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return seasons;
    }

    public List<Episode> getAllEpisodes() {
        List<Episode> episodes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_EPISODES + " ORDER BY " + KEY_EPISODE_FK_SEASON + ", " + KEY_EPISODE_NUMBER + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Episode episode = new Episode(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
                );
                episodes.add(episode);
            } while (cursor.moveToNext());
        }

        //cursor.close();

        return episodes;
    }

    public int updateSeason(Season season) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SEASON_NUMBER, season.getSeasonNumber());
        values.put(KEY_SEASON_NAME, season.getSeasonName());

        return db.update(TABLE_SEASONS,
                values,
                KEY_SEASON_ID + " = ?",
                new String[]{
                        String.valueOf(season.getSeasonId())
                });
    }

    public int updateEpisode(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EPISODE_NUMBER, episode.getEpisodeNumber());
        values.put(KEY_EPISODE_NAME, episode.getEpisodeName());

        return db.update(TABLE_EPISODES,
                values,
                KEY_EPISODE_ID + " = ?",
                new String[]{
                        String.valueOf(episode.getEpisodeId())
                });
    }

    public void deleteSeason(Season season) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SEASONS,
                KEY_SEASON_ID + " = ?",
                new String[]{
                        String.valueOf(season.getSeasonId())
                });

        db.delete(TABLE_EPISODES,
                KEY_EPISODE_FK_SEASON + " = ?",
                new String[]{
                        String.valueOf(season.getSeasonId())
                });
    }

    public void deleteEpisode(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_EPISODES,
                KEY_EPISODE_ID + " = ?",
                new String[]{
                        String.valueOf(episode.getEpisodeId())
                });
    }

    public List<Episode> getAllEpisodesFromSeason(int seasonNumber) {
        List<Episode> episodes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_EPISODES
                + " WHERE " + KEY_EPISODE_FK_SEASON + " = " + seasonNumber
                + " ORDER BY " + KEY_EPISODE_NUMBER + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Episode episode = new Episode(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
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
        int count = cursor.getCount();

        cursor.close();
        //db.close();

        return count;
    }

    public int getEpisodesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EPISODES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();
        //db.close();

        return count;
    }
}
