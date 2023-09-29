package com.facebook.maciejprogramuje.miazga1.models;

public class EpisodeItem {
    int episodeNumber;

    public EpisodeItem(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getSeasonNumber() {
        return episodeNumber;
    }
}
