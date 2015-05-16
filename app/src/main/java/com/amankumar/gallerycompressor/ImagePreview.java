package com.amankumar.gallerycompressor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amankumar.gallerycompressor.adapter.ImageAdapter;
import com.amankumar.gallerycompressor.adapter.ImageItem;

/**
 * Created by aman on 16/5/15.
 */
public class ImagePreview extends Fragment {

    RecyclerView imgContainer;
    ImageAdapter imageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.image_preview,container,false);
        imgContainer = (RecyclerView) layout.findViewById(R.id.preview_container);
        imageAdapter = new ImageAdapter(getActivity(), ImageItem.getItem(getActivity()));
        imgContainer.setAdapter(imageAdapter);
        imgContainer.setLayoutManager(new GridLayoutManager(getActivity(),3));
        return layout;
    }

}
