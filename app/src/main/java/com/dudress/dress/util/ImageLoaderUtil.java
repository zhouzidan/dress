package com.dudress.dress.util;

import android.content.Context;

import com.dudress.dress.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by zhou on 16-4-6.
 */
public class ImageLoaderUtil {

    public static void loadConfig(Context mContext){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .threadPoolSize(3) //线程池加载的线程数量
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheFileCount(100)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }


    static DisplayImageOptions options;
    public static DisplayImageOptions getDisplayImageOption(){
        if (options == null){
            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.mipmap.du_dress_icon_108)
                    .showImageOnFail(R.mipmap.du_dress_icon_108)
                    .showImageOnLoading(R.mipmap.du_dress_icon_108)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .displayer(new RoundedBitmapDisplayer(100)) //设置圆角
                    .displayer(new FadeInBitmapDisplayer(100))//图片加载好之后，渐入的动画
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED) //按照正常比例缩放
                    .build();
        }
        return  options;
    }
}
