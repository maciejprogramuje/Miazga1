package com.facebook.maciejprogramuje.miazga1;

import static android.os.Build.VERSION.SDK_INT;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;

import com.facebook.maciejprogramuje.miazga1.models.Video;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MediaStoreHelper {
    private final List<Video> videoList = new ArrayList<>();


    public MediaStoreHelper(View view) {
        //konieczne przyznanie uprawnień dla aplikacji

        populateVideoList(view);
    }

    private void populateVideoList(View view) {
        Uri collection;
        if (SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }

        String[] projection = new String[]{
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE
        };

        String selection = MediaStore.Video.Media.DISPLAY_NAME + " LIKE ?";
        String[] selectionArgs = new String[]{
                "miazga_%"
        };

        String sortOrder = MediaStore.Video.Media.DISPLAY_NAME + " ASC";

        try (
                Cursor cursor = view.getContext().getApplicationContext().getContentResolver().query(
                        collection,
                        projection,
                        selection,
                        selectionArgs,
                        sortOrder)
        ) {
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);

            while (cursor.moveToNext()) {
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                int size = cursor.getInt(sizeColumn);

                Uri contentUri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);

                videoList.add(new Video(contentUri, name, duration, size));
            }
        }
    }

    public List<Video> getVideoListSortedBySeasonNumber() {
        videoList.sort(Comparator.comparing(Video::getSeasonNumber));
        return videoList;
    }
}