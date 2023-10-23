package com.facebook.maciejprogramuje.miazga1.models;

public class Episode {
    private int episodeId;
    private int episodeNumber;
    private String episodeName;
    private int watched;
    private int seasonFK;

    public Episode(int episodeNumber, String episodeName, int seasonFK) {
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
        this.watched = 0;
        this.seasonFK = seasonFK;
    }

    public Episode(int episodeId, int episodeNumber, String episodeName, int watched, int seasonFK) {
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

    public int isWatched() {
        return watched;
    }

    public void setWatched(int watched) {
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
