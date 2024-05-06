package com.facebook.maciejprogramuje.miazga1.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoDbHandler extends SQLiteOpenHelper {
    // **********************************************************************
    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "miazgaMoviesDb";
    // **********************************************************************
    private static final String TABLE_EPISODES = "episodesTable";
    private static final String KEY_EPISODE_ID = "id";
    private static final String KEY_EPISODE_NUMBER = "episodeNumber";
    private static final String KEY_EPISODE_NAME = "episodeName";
    private static final String KEY_EPISODE_WATCHED = "watched";
    private static final String KEY_EPISODE_FK_SEASON = "seasonFK";
    private static final String KEY_EPISODE_DURATION = "duration";
    private static final String KEY_EPISODE_URI = "uri";
    // **********************************************************************

    public VideoDbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void manageDatabase(List<Episode> episodes) {
        Map<String, Integer> episodesExistInDb = getEpisodesExistInDb(episodes);

        deleteDataFromTables();

        fillDbByEpisodesFromMediaStore(episodes);

        setWatchedFlaginDb(episodesExistInDb);
    }

    private void setWatchedFlaginDb(Map<String, Integer> episodesExistInDb) {
        for (Episode eDb : getAllEpisodesFromDb()) {
            for (String episodeNameExistsInDb : episodesExistInDb.keySet()) {
                if (eDb.getName().equals(episodeNameExistsInDb)) {
                    updateEpisodeWatchedInDb(eDb.getEpisodeId(), episodesExistInDb.get(episodeNameExistsInDb) == 1);
                }
            }
        }
    }

    private void fillDbByEpisodesFromMediaStore(List<Episode> episodes) {
        for (Episode v : episodes) {
            addEpisodeToDb(new Episode(
                    v.getUri(),
                    v.getName(),
                    v.getDuration(),
                    v.getSize()));
        }
    }

    private Map<String, Integer> getEpisodesExistInDb(List<Episode> episodes) {
        Map<String, Integer> episodesExist = new HashMap<>();

        for (Episode eDb : getAllEpisodesFromDb()) {
            if (isEpisodeFromMediaStoreInDb(eDb, episodes)) {
                episodesExist.put(eDb.getName(), eDb.getWatched());
            }
        }

        return episodesExist;
    }

    private boolean isEpisodeFromMediaStoreInDb(Episode eDb, List<Episode> episodes) {
        for (Episode e : episodes) {
            if (eDb.getName().equals(e.getName())) {
                return true;
            }
        }

        return false;
    }

    private List<Episode> getAllEpisodesFromDb() {
        List<Episode> episodes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_EPISODES;

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
                        cursor.getInt(5),
                        Uri.parse(cursor.getString(6))
                );
                episodes.add(episode);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return episodes;
    }

    public void deleteDataFromTables() {
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
                + KEY_EPISODE_DURATION + " INTEGER,"
                + KEY_EPISODE_URI + " TEXT"
                + ")";
        db.execSQL(CREATE_EPISODES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EPISODES);

        onCreate(db);
    }

    public void addEpisodeToDb(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EPISODE_NUMBER, episode.getEpisodeNumber());
        values.put(KEY_EPISODE_NAME, episode.getName());
        values.put(KEY_EPISODE_WATCHED, episode.getWatched());
        values.put(KEY_EPISODE_FK_SEASON, episode.getSeasonNumber());
        values.put(KEY_EPISODE_DURATION, episode.getDuration());
        values.put(KEY_EPISODE_URI, episode.getUri().toString());

        db.insertWithOnConflict(TABLE_EPISODES, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public Episode getEpisodeFromDb(int id) {
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
                    cursor.getInt(5),
                    Uri.parse(cursor.getString(6))
            );
            cursor.close();
        }

        return episode;
    }

    public Episode getEpisodeByNumbersFromDb(int episodeNumber, int seasonNumber) {
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
                    cursor.getInt(5),
                    Uri.parse(cursor.getString(6))
            );
            cursor.close();
        }

        return episode;
    }

    public void updateEpisodeWatchedInDb(int episodeId, boolean watched) {
        int watchedInt = 0;
        if (watched) watchedInt = 1;

        String sql = "UPDATE " + TABLE_EPISODES
                + " SET " + KEY_EPISODE_WATCHED + " = " + watchedInt
                + " WHERE " + KEY_EPISODE_ID + " = " + episodeId;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void deleteEpisodeFromDb(Episode episode) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_EPISODES,
                KEY_EPISODE_ID + " = ?",
                new String[]{
                        String.valueOf(episode.getEpisodeId())
                });
    }

    public List<Episode> getAllEpisodesFromSeasonFromDb(int seasonNumber) {
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
                        cursor.getInt(5),
                        Uri.parse(cursor.getString(6))
                );
                episodes.add(episode);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return episodes;
    }

    public List<Season> getAllSeasonsFromDb() {
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

    public int getNumberOfWatchedFromDb(List<Episode> episodesInSeason) {
        int watched = 0;
        for (Episode e : episodesInSeason) {
            if (e.getWatched() == 1) {
                watched++;
            }
        }

        return watched;
    }
}
