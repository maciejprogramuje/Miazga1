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
    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "miazgaMoviesDb";
    // **********************************************************************
    private static final String TABLE_EPISODES = "episodesTable";
    private static final String KEY_EPISODE_ID = "id";
    private static final String KEY_EPISODE_NUMBER = "episodeNumber";
    private static final String KEY_EPISODE_NAME = "episodeName";
    private static final String KEY_EPISODE_WATCHED = "watched";
    private static final String KEY_EPISODE_FK_SEASON = "seasonFK";
    private static final String KEY_EPISODE_DURATION = "duration";
    // **********************************************************************

    public VideoDbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void fillDatabase(List<Video> videos) {
        //deleteDataFromTables();

        for (Video video : videos) {
            Episode episode = new Episode(
                    video.getEpisodeNumber(),
                    video.getName(),
                    video.getSeasonNumber(),
                    video.getDuration()
            );

            addEpisode(episode);
        }
    }

    public void getAllEpisodes() {
        String selectQuery = "SELECT * FROM " + TABLE_EPISODES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Log.w("video1",
                        "KEY_EPISODE_ID=" + cursor.getInt(0)
                                + ", KEY_EPISODE_NUMBER=" + cursor.getInt(1)
                                + ", KEY_EPISODE_NAME=" + cursor.getString(2)
                                + ", KEY_EPISODE_WATCHED=" + cursor.getInt(3)
                                + ", KEY_EPISODE_FK_SEASON=" + cursor.getInt(4)
                                + ", KEY_EPISODE_DURATION=" + cursor.getInt(5)
                );
            } while (cursor.moveToNext());
        }

        cursor.close();
    }

    private void deleteDataFromTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_EPISODES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EPISODES_TABLE = "CREATE TABLE " + TABLE_EPISODES + "("
                + KEY_EPISODE_ID + " INTEGER PRIMARY KEY,"
                + KEY_EPISODE_NUMBER + " INTEGER,"
                + KEY_EPISODE_NAME + " TEXT UNIQUE,"
                + KEY_EPISODE_WATCHED + " INTEGER,"
                + KEY_EPISODE_FK_SEASON + " INTEGER,"
                + KEY_EPISODE_DURATION + " INTEGER"
                + ")";
        db.execSQL(CREATE_EPISODES_TABLE);

        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EPISODES);

        onCreate(db);

        //db.close();
    }

    public void addEpisode(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EPISODE_NUMBER, episode.getEpisodeNumber());
        values.put(KEY_EPISODE_NAME, episode.getEpisodeName());
        values.put(KEY_EPISODE_WATCHED, episode.isWatched());
        values.put(KEY_EPISODE_FK_SEASON, episode.getSeasonFK());
        values.put(KEY_EPISODE_DURATION, episode.getDuration());

        db.insertWithOnConflict(TABLE_EPISODES, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public Episode getEpisode(int id) {
        String sql = "SELECT * FROM " + TABLE_EPISODES
                + " WHERE " + KEY_EPISODE_ID + " = " + (id + 1);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Episode episode = null;

        if (cursor != null && cursor.moveToFirst()) {
            episode = new Episode(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
            );
            cursor.close();
        }

        return episode;
    }

    public Episode getEpisodeByNumbers(int episodeNumber, int seasonNumber) {
        String sql = "SELECT * FROM " + TABLE_EPISODES
                + " WHERE " + KEY_EPISODE_NUMBER + " = " + episodeNumber
                + " AND " + KEY_EPISODE_FK_SEASON + " = " + seasonNumber;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Episode episode = null;

        if (cursor != null && cursor.moveToFirst()) {
            episode = new Episode(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
            );
            cursor.close();
        }

        return episode;
    }

    public void updateEpisodeWatched(int episodeId, boolean watched) {
        int watchedInt = 0;
        if (watched) watchedInt = 1;

        String sql = "UPDATE " + TABLE_EPISODES
                + " SET " + KEY_EPISODE_WATCHED + " = " + watchedInt
                + " WHERE " + KEY_EPISODE_ID + " = " + episodeId;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
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
                        cursor.getInt(4),
                        cursor.getInt(5)
                );
                episodes.add(episode);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return episodes;
    }

    public List<Season> getAllSeasons() {
        List<Season> seasons = new ArrayList<>();

        String sql = "SELECT DISTINCT " + KEY_EPISODE_FK_SEASON
                + " FROM " + TABLE_EPISODES
                + " ORDER BY " + KEY_EPISODE_FK_SEASON + " ASC";
        ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Season s = new Season(
                        cursor.getInt(0)
                );

                seasons.add(s);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return seasons;
    }

    public int getWatched(List<Episode> episodesInSeason) {
        int watched = 0;
        for (Episode e : episodesInSeason) {
            if (e.isWatched()) {
                watched++;
            }
        }
        return watched;
    }
}
