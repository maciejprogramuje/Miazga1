package com.facebook.maciejprogramuje.miazga1.models;

import java.util.List;

public class SeasonItem {
    int seasonNumber;
    List<EpisodeItem> episodeItems;

    public SeasonItem(int seasonNumber, List<EpisodeItem> episodeItems) {
        this.seasonNumber = seasonNumber;
        this.episodeItems = episodeItems;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public List<EpisodeItem> getEpisodes() {
        return episodeItems;
    }
}
