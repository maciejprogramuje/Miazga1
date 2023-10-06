package com.facebook.maciejprogramuje.miazga1.models;

import java.util.ArrayList;
import java.util.List;

public class Season {
    private int seasonId;
    private int seasonNumber;
    private String seasonName;

    public Season(int seasonNumber, String seasonName) {
        this.seasonNumber = seasonNumber;
        this.seasonName = seasonName;
    }

    public Season(int seasonId, int seasonNumber, String seasonName) {
        this.seasonId = seasonId;
        this.seasonNumber = seasonNumber;
        this.seasonName = seasonName;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }
}
