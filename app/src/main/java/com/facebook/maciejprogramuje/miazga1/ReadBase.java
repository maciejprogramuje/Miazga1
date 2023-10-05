package com.facebook.maciejprogramuje.miazga1;

import android.app.Application;

import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.models.Season;

import java.util.ArrayList;
import java.util.List;

public class ReadBase extends Application {
    private List<Season> seasons;

    public ReadBase() {
    }

    public List<Season> getSeasons() {
        return seasons;
    }

}
