package com.dudress.main.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by zhou on 16-4-1.
 */
public class AppUtil {
    public static String getAppVersionName(Context mContext) {
        PackageManager pm = mContext.getPackageManager();//context为当前Activity上下文
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionName;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return "0";
    }

    public  static int getAppVersionCode(Context mContext){
        PackageManager pm = mContext.getPackageManager();//context为当前Activity上下文
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionCode;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return 0;
    }
}
