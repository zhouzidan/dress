package com.dudress.main.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.dudress.main.R;
import com.dudress.main.ui.customview.HeaderGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 16/4/5.
 */
public class PageActivity extends FragmentActivity {
    HeaderGridView gridView;
    View headerView;
    @Bind(R.id.img)
    ImageView faceImg;
    Context context = this;
    @Bind(R.id.showTwoImg)
    ImageView showTwoImg;
    @Bind(R.id.showOneImg)
    ImageView showOneImg;


    SimpleAdapter oneAdapter , twoAdapter;
    ArrayList<HashMap<String, Object>> lstImageItem ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headerView = inflater.inflate(R.layout.activity_page_header, null);
        ButterKnife.bind(this,headerView);
        initView();
    }

    private void initView() {
        DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(100)).build();
        String imgUrl = "http://img4.duitang.com/uploads/item/201602/29/20160229121022_rFktZ.jpeg";
        ImageLoader.getInstance().displayImage(imgUrl, faceImg, options);
        gridView = (HeaderGridView) findViewById(R.id.gridview);
        gridView.addHeaderView(headerView); // 他需要在setAdapter()之前

        lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.mipmap.test_dog);//添加图像资源的ID
            map.put("ItemText", "NO." + String.valueOf(i));//按序号做ItemText
            lstImageItem.add(map);
        }
        showTwoImg();
        //添加并且显示
    }

    @OnClick(R.id.showTwoImg)
    public void showTwoImg() {
        Toast.makeText(context, "showTwoImg", Toast.LENGTH_SHORT).show();
        gridView.setNumColumns(2);
        initTwoAdapter();
        gridView.setAdapter(twoAdapter);
//        twoAdapter.notifyDataSetChanged();
    }
    @OnClick(R.id.showOneImg)
    void showOneImg(){
        Toast.makeText(context, "showOneImg", Toast.LENGTH_SHORT).show();
        //添加并且显示
        gridView.setNumColumns(1);
        initOneAdapter();
        gridView.setAdapter(oneAdapter);
//        oneAdapter.notifyDataSetChanged();
    }

    private void initOneAdapter(){
        if (oneAdapter == null){
            //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
            oneAdapter = new SimpleAdapter(this, //没什么解释
                    lstImageItem,//数据来源
                    R.layout.gridview_item_page_one,//night_item的XML实现
                    //动态数组与ImageItem对应的子项
                    new String[]{"ItemImage", "ItemText"},
                    //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                    new int[]{R.id.img, R.id.tv});
        }
    }

    private void initTwoAdapter(){
        if (twoAdapter == null){
            //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
            twoAdapter = new SimpleAdapter(this, //没什么解释
                    lstImageItem,//数据来源
                    R.layout.gridview_item_page_two,//night_item的XML实现
                    //动态数组与ImageItem对应的子项
                    new String[]{"ItemImage", "ItemText"},
                    //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                    new int[]{R.id.img, R.id.tv});
        }
    }

}
