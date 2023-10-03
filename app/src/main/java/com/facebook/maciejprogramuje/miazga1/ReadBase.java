package com.facebook.maciejprogramuje.miazga1;

import android.app.Application;

import com.facebook.maciejprogramuje.miazga1.database.Movie;
import com.facebook.maciejprogramuje.miazga1.database.MovieDbHandler;
import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.models.Season;

import java.util.ArrayList;
import java.util.List;

public class ReadBase extends Application {
    private List<Season> seasons;

    public ReadBase() {
        MovieDbHandler db = fillMovieDb();


        seasons = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            List<Episode> episodes = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                episodes.add(new Episode(j, "Episode " + j));
            }

            seasons.add(new Season(i, "Season " + i, episodes));
        }
    }

    private MovieDbHandler fillMovieDb() {
        MovieDbHandler movieDbHandler = new MovieDbHandler(this);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                movieDbHandler.addMovie(new Movie(
                        i,
                        "Season no." + i,
                        j,
                        "Episode no." + j,
                        false
                ));
            }
        }

        return movieDbHandler;
    }


    public List<Season> getSeasons() {
        return seasons;
    }

}
