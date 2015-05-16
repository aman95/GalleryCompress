package com.amankumar.gallerycompressor.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.amankumar.gallerycompressor.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 16/5/15.
 */
public class ImageItem {
    private static int count;
    String imgPath;
    String imgName;
    boolean isSelected;


    public static List<ImageItem> getItem(Context context) {
        List<ImageItem> data = new ArrayList<>();

        final String[] columns = {MediaStore.Images.Media.DATA};
        final String orderBy = MediaStore.Images.Media.DATE_ADDED;

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,columns,null,null,orderBy);

        count = cursor.getCount();

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            //Store the path of the image
            ImageItem current = new ImageItem();
            current.imgPath = cursor.getString(dataColumnIndex);
            //File img = new File(current.imgPath);
            current.imgName = "blah";//img.getName();
            current.isSelected = false;
            data.add(current);
            //Log.i("PATH", arrPath[i]);
            //Log.d("browser", "Browser Class Called 4.===================================");
        }

        return data;
    }




}
