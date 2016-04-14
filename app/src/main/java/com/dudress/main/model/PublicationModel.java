package com.dudress.main.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.dudress.main.DressApplication;
import com.dudress.main.presenter.PublicationPresenter;
import com.dudress.main.ui.activities.PublicationActivity;

import java.io.File;

/**
 * Created by zhou on 16-4-14.
 */
public class PublicationModel {

    public void showAddPicDialog(final Context context,final PublicationPresenter.AddPicStyle addPicStyle){
        CharSequence[] chars = {"open camera","open gallery"};
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setItems(chars, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            addPicStyle.takeCamera();
                        }else if(which == 1){
                            addPicStyle.choosePic();
                        }
                    }
                })
                .setTitle("选择获取照片的方式")
                .show();

    }




}
