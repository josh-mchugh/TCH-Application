package com.redrumming.thecreaturehub.util;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 8/4/2015.
 */
public class ImageLoaderUtil {

    private static ImageLoaderUtil INSTANCE = null;
    private static ImageLoader IMAGE_LOADER = null;
    private static DisplayImageOptions IMAGE_OPTIONS = null;
    private static ImageLoaderConfiguration IMAGE_CONFIGS = null;

    protected ImageLoaderUtil(){

    }

    public static ImageLoaderUtil get(Context context){

        if(INSTANCE == null){

            INSTANCE = new ImageLoaderUtil();
            IMAGE_LOADER = ImageLoader.getInstance();
            IMAGE_LOADER.init(getConfiguration(context));

            return INSTANCE;
        }

        return INSTANCE;
    }

    public void displayImage(String url, ImageView view){

        IMAGE_LOADER.displayImage(url, view, getDisplayOptions());
    }

    private static DisplayImageOptions getDisplayOptions(){

        if(IMAGE_OPTIONS == null) {

            IMAGE_OPTIONS = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.loading_video_thumbnail)
                    .showImageForEmptyUri(R.drawable.error_video_thumbnail)
                    .showImageOnFail(R.drawable.error_video_thumbnail)
                    .cacheInMemory(false)
                    .cacheOnDisk(false)
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                    .build();

            return IMAGE_OPTIONS;
        }

        return IMAGE_OPTIONS;
    }

    private static ImageLoaderConfiguration getConfiguration(Context context){

        if(IMAGE_CONFIGS == null){

            IMAGE_CONFIGS = new ImageLoaderConfiguration.Builder(context).build();

            return IMAGE_CONFIGS;
        }

        return IMAGE_CONFIGS;
    }
}
