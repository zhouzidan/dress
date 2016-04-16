package com.dudress.main.ui.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dudress.main.DressApplication;
import com.dudress.main.R;
import com.dudress.main.presenter.PublicationPresenter;
import com.dudress.main.view.PublicationView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 16-4-14.
 * 发表内容（img + text）
 */
public class PublicationActivity extends Activity implements PublicationView {
    public static int REQUEST_CODE_TAKE_CAMERA = 101;
    public static int REQUEST_CODE_PICK_IMAGE = 102;
    public String TAG = "PublicationActivity";
    public Context context = this;
    private Uri picPath = null;
    private List<Uri> picUris = new ArrayList<>();
    @Bind(R.id.layout_pic)
    LinearLayout picLayout;
    @Bind(R.id.ed_content)
    EditText contentEditText;
    @Bind(R.id.add)
    View addView;

    PublicationPresenter publicationPresenter = new PublicationPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_TAKE_CAMERA == requestCode && resultCode == RESULT_OK && picPath!= null) {
            picUris.add(picPath);
            //TODO 创建一个自定义的view 增加到layout里面去
//            ImageLoader.getInstance().displayImage(imgUrl, faceImg, options);
        } else if (REQUEST_CODE_PICK_IMAGE == requestCode && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            picUris.add(uri);
//            String imgUrl = uri.toString();
//            Log.d(TAG,"album--url:"+imgUrl);
//            ImageLoader.getInstance().displayImage(imgUrl, faceImg, options);
        }
//        if (REQUEST_CODE_TAKE_CAMERA == requestCode && picPath!= null){
//            getContentResolver().delete(picPath,null,null);
//        }
    }

    PublicationPresenter.AddPicStyle addPicStyle = new PublicationPresenter.AddPicStyle() {
        @Override
        public void takeCamera() {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ContentValues values = new ContentValues(3);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                picPath = getPicOutputUri();
                getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, picPath);
                startActivityForResult(getImageByCamera, PublicationActivity.REQUEST_CODE_TAKE_CAMERA);
            } else {
                Toast.makeText(DressApplication.getAppContext(), "SD is not avaiable", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void choosePic() {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PublicationActivity.REQUEST_CODE_PICK_IMAGE);
        }
    };

//the pic output file for take carema
    private Uri getPicOutputUri(){
        String filePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + String.valueOf(System.currentTimeMillis())+".jpg";
        return Uri.fromFile(new File(filePath));
    }

    @OnClick(R.id.add)
    public void addNewPic() {
        publicationPresenter.addNewPic(this, addPicStyle);
    }


    //将图片在界面上现实
    public void addPicToView() {

    }

    //将图片从界面上移除
    public void removePicFromView() {

    }

    @Override
    public int getSelectedPicSize() {
        return 0;
    }

}
