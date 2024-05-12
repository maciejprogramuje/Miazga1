package com.facebook.maciejprogramuje.miazga1.models;

import android.net.Uri;

public class Episode {
    private Uri uri;
    private String name;
    private int duration;
    private int size;
    private int episodeId;
    private int seasonNumber;
    private int episodeNumber;
    private int watched;
    private int currentPosition;


    public Episode(Uri contentUri, String name, int duration, int size) {
        this.uri = contentUri;
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.episodeNumber = Integer.parseInt(this.name.substring(this.name.indexOf("e") + 1, this.name.indexOf(".")));
        this.seasonNumber = Integer.parseInt(this.name.substring(this.name.indexOf("s") + 1, this.name.indexOf("e")));
        this.watched = 0;
        this.currentPosition = 0;
    }

    public Episode(int episodeId, int episodeNumber, String episodeName, int watched, int seasonNumber, int duration, Uri uri, int currentPosition) {
        this.episodeId = episodeId;
        this.episodeNumber = episodeNumber;
        this.name = episodeName;
        this.watched = watched;
        this.seasonNumber = seasonNumber;
        this.duration = duration;
        this.uri = uri;
        this.currentPosition = currentPosition;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
