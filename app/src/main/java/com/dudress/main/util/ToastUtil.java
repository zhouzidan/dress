package com.dudress.main.util;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.dudress.main.DressApplication;


/**
 * Created by zhou on 16/4/10.
 */
public class ToastUtil {
    public static void show(String msg){
        if (TextUtils.isEmpty(msg) == false){
            Toast toast = Toast.makeText(DressApplication.getAppContext(),msg,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,100);
            toast.show();
        }
    }


    public static void show(int resId){
        if (resId > 0){
            Toast toast = Toast.makeText(DressApplication.getAppContext(),resId,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,100);
            toast.show();
        }
    }
}
