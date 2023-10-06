package com.facebook.maciejprogramuje.miazga1;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

public class MediaTest {
    public MediaTest(Context context) {
            String id = MediaStore.Video.Media._ID;
            String id1 = MediaStore.Video.VideoColumns._ID;
            String displayName = MediaStore.Video.VideoColumns.DISPLAY_NAME;

            String[] proj = {id, id1, displayName};

            Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
            if (cursor.moveToFirst()) {



                Log.w("mmm", cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2));
            }
    }
}
