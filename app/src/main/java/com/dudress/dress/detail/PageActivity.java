package com.dudress.dress.detail;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.dudress.dress.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 16/4/5.
 */
public class PageActivity extends Activity {
    @Bind(R.id.img) ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(100)).build();
        String imgUrl = "http://img4.duitang.com/uploads/item/201602/29/20160229121022_rFktZ.jpeg";
        ImageLoader.getInstance().displayImage(imgUrl,imageView,options);

    }


@OnClick(R.id.img) void clickImg(){
    //TODO
}
}
