package com.amankumar.gallerycompressor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amankumar.gallerycompressor.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by aman on 16/5/15.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    LayoutInflater inflater;
    List<ImageItem> data = Collections.emptyList();
    Context context;

    public ImageAdapter(Context context,List<ImageItem> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row,parent,false);
        ImageViewHolder holder = new ImageViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ImageItem currentItem = data.get(position);
        Picasso.with(context)
                .load("file:"+currentItem.imgPath)
                .placeholder(R.drawable.ic_launcher)
                .resize(160, 160)
                .centerCrop()
                .error(R.drawable.ic_launcher)
                .into(holder.thumbnail);
        holder.thumbnail.setTag(currentItem.imgName);
        holder.checkBox.setChecked(currentItem.isSelected);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
