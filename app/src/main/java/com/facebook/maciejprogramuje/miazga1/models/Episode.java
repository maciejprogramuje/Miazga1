package com.facebook.maciejprogramuje.miazga1.models;

public class Episode {
    private int episodeId;
    private int episodeNumber;
    private String episodeName;
    private boolean watched;
    private int seasonFK;

    public Episode(int episodeNumber, String episodeName, boolean watched, int seasonFK) {
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
        this.watched = watched;
        this.seasonFK = seasonFK;
    }

    public Episode(int episodeId, int episodeNumber, String episodeName, boolean watched, int seasonFK) {
        this.episodeId = episodeId;
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
        this.watched = watched;
        this.seasonFK = seasonFK;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public int getSeasonFK() {
        return seasonFK;
    }

    public void setSeasonFK(int seasonFK) {
        this.seasonFK = seasonFK;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }
}
