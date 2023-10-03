package com.facebook.maciejprogramuje.miazga1.database;

public class Movie {
    int _id;
    int _seasonNumber;
    String _seasonName;
    int _episodeNumber;
    String _episodeName;
    boolean _watched;

    public Movie(int _seasonNumber, String _seasonName, int _episodeNumber, String _episodeName, boolean _watched) {
        this._seasonNumber = _seasonNumber;
        this._seasonName = _seasonName;
        this._episodeNumber = _episodeNumber;
        this._episodeName = _episodeName;
        this._watched = _watched;
    }

    public Movie(int _id, int _seasonNumber, String _seasonName, int _episodeNumber, String _episodeName, boolean _watched) {
        this._id = _id;
        this._seasonNumber = _seasonNumber;
        this._seasonName = _seasonName;
        this._episodeNumber = _episodeNumber;
        this._episodeName = _episodeName;
        this._watched = _watched;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_seasonNumber() {
        return _seasonNumber;
    }

    public void set_seasonNumber(int _seasonNumber) {
        this._seasonNumber = _seasonNumber;
    }

    public String get_seasonName() {
        return _seasonName;
    }

    public void set_seasonName(String _seasonName) {
        this._seasonName = _seasonName;
    }

    public int get_episodeNumber() {
        return _episodeNumber;
    }

    public void set_episodeNumber(int _episodeNumber) {
        this._episodeNumber = _episodeNumber;
    }

    public String get_episodeName() {
        return _episodeName;
    }

    public void set_episodeName(String _episodeName) {
        this._episodeName = _episodeName;
    }

    public boolean is_watched() {
        return _watched;
    }

    public void set_watched(boolean _watched) {
        this._watched = _watched;
    }
}
