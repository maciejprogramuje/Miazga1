package com.facebook.maciejprogramuje.miazga1.models;

import java.util.ArrayList;
import java.util.List;

public class Season {
    private int seasonNumber;
    private String seasonName;

    public Season(int seasonNumber, String seasonName) {
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

}
