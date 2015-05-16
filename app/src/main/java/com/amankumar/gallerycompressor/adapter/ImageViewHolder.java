package com.amankumar.gallerycompressor.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.amankumar.gallerycompressor.R;

/**
 * Created by aman on 16/5/15.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView thumbnail;
    CheckBox checkBox;

    public ImageViewHolder(View itemView) {
        super(itemView);
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox1);
    }
}
