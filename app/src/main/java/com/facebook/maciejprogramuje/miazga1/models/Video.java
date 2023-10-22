package com.facebook.maciejprogramuje.miazga1.models;

import android.net.Uri;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Video {
    private final Uri uri;
    private final String name;
    private final int duration;
    private final int size;
    private final int seasonNumber;
    private final int episodeNumber;

    public Video(Uri uri, String name, int duration, int size) {
        this.uri = uri;
        this.name = name;
        this.duration = duration;
        this.size = size;

        seasonNumber = Integer.parseInt(name.substring(name.indexOf("s") + 1, name.indexOf("e")));
        episodeNumber = Integer.parseInt(name.substring(name.indexOf("e") + 1, name.indexOf(".")));
    }

    public Uri getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getSize() {
        return size;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }


}
