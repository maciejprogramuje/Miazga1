package com.facebook.maciejprogramuje.miazga1.models;

public class Season {
    private int seasonId;
    private int seasonNumber;

    public Season(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

}
