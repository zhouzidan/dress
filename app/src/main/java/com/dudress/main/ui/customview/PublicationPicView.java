package com.dudress.main.ui.customview;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.dudress.main.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhou on 16-4-14.
 */
public class PublicationPicView extends View {
    @Bind(R.id.img)
    ImageView imageView;
    public PublicationPicView(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pic_publication,null);
        ButterKnife.bind(view);
    }

    public void setImgUri(Uri uri){
        ImageLoader.getInstance().displayImage(uri.toString(), imageView);
    }


}
