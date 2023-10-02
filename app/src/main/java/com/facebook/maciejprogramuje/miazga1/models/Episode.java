package com.facebook.maciejprogramuje.miazga1.models;

public class Episode {
    int episodeNumber;
    String episodeName;

    public Episode(int episodeNumber, String episodeName) {
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getEpisodeName() {
        return episodeName;
    }
}
