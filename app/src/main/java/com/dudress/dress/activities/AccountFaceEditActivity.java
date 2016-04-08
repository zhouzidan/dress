package com.dudress.dress.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.dudress.dress.R;
import com.dudress.dress.db.AccountDBManager;
import com.dudress.dress.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 16-4-8.
 * 操作用户头像
 */
public class AccountFaceEditActivity extends AppCompatActivity {
String TAG = this.getClass().getSimpleName();
    public static int REQUEST_CODE_PICK_IMAGE = 101;
    public static int REQUEST_CODE_CAPTURE_CAMEIA = 102;
    Uri imageFilePath = null;
    @Bind(R.id.img_face)
    ImageView faceImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_face_edit);
        ButterKnife.bind(this);
        DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(200)).build();
        String imgUrl = AccountDBManager.get().getPicUrl();
        ImageLoader.getInstance().displayImage(imgUrl, faceImg, options);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @OnClick(R.id.tv_take_photo)
    void takePhoto(){
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues(3);
//            values.put(MediaStore.Images.Media.DISPLAY_NAME, "");
//            values.put(MediaStore.Images.Media.DESCRIPTION, "this is description");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            String imgPath = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + "face.img";
            imageFilePath = Uri.fromFile(new File(imgPath)) ;
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageFilePath);
            startActivityForResult(getImageByCamera, REQUEST_CODE_CAPTURE_CAMEIA);
        } else {
            Toast.makeText(getApplicationContext(), "SD is not avaiable", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.tv_chose_in_local)
    void chooseLocalImg(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @OnClick({R.id.tv_cancel})
    void cancel(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,item.toString());
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_CAPTURE_CAMEIA == requestCode && resultCode == RESULT_OK){
            DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(200)).build();
            String imgUrl = imageFilePath.toString();
            Log.d(TAG,"camera-url:"+imgUrl);
            ImageLoader.getInstance().displayImage(imgUrl, faceImg, options);
            //TODO account`s faceimg upload to server
        }else if(REQUEST_CODE_PICK_IMAGE == requestCode && resultCode == RESULT_OK){
            Uri uri = data.getData();
            DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(200)).build();
            String imgUrl = uri.toString();
            Log.d(TAG,"album--url:"+imgUrl);
            ImageLoader.getInstance().displayImage(imgUrl, faceImg, options);
            //TODO account`s faceimg upload to server
        }
        if (REQUEST_CODE_CAPTURE_CAMEIA == requestCode && imageFilePath!= null){
            getContentResolver().delete(imageFilePath,null,null);
        }
    }


}
