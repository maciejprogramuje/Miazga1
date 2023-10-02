package com.facebook.maciejprogramuje.miazga1.models;

import java.util.List;

public class Season {
    int seasonNumber;
    String seasonName;
    List<Episode> episodes;

    public Season(int seasonNumber, String seasonName, List<Episode> episodes) {
        this.seasonNumber = seasonNumber;
        this.seasonName = seasonName;
        this.episodes = episodes;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
