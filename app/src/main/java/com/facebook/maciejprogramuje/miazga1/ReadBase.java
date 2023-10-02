package com.facebook.maciejprogramuje.miazga1;

import android.app.Application;

import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.models.Season;

import java.util.ArrayList;
import java.util.List;

public class ReadBase extends Application {
    private List<Season> seasons;
    public ReadBase() {
        seasons = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            List<Episode> episodes = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                episodes.add(new Episode(j, "Episode " + j));
            }

            seasons.add(new Season(i, "Season " + i, episodes));
        }
    }

    public List<Season> getSeasons() {
        return seasons;
    }

}
