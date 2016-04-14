package com.dudress.main.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhou on 16-3-18.
 */
public class SharedpreUtil {
    /**
     * 保存在手机里面的文件名
     */
    private static final String SP_NAME = "sp_device";

    public static long getLongFromSP(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, -1l);
    }

    public static void setLongToSP(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value).commit();
    }

    public static void setBooleanToSP(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value).commit();
    }

    public static boolean getBooleanFromSP(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static String getStringFromSP(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }

    public static void setStringToSP(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value).commit();
    }

    public static int getIntFromSP(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, -1);
    }

    public static void setIntToSP(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value).commit();
    }

    public static void removeKeyBySP(Context context ,String key){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key).commit();
    }

    public static boolean containKeyBySP(Context mContext , String key){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static boolean removeAll(Context mContext ){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.edit().clear().commit();
    }

}
