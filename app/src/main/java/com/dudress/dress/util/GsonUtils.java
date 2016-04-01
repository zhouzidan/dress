package com.dudress.dress.util;

/**
 * Created by zhou on 16-3-18.
 */
import com.google.gson.Gson;

public class GsonUtils {
    private static GsonUtils instance;
    private Gson gson;

    private GsonUtils() {
        gson = new Gson();
    }

    public static GsonUtils getInstance() {
        if (instance == null) {
            instance = new GsonUtils();
        }
        return instance;
    }

    public String toJson(Object object){
        return gson.toJson(object);
    }

    /**
     * 泛型：http://www.cnblogs.com/iyangyuan/archive/2013/04/09/3011274.html
     * @param json
     * @param classOfT
     * @return
     */
    public <T> T fromJson(String json,Class<T> classOfT){
        return gson.fromJson(json, classOfT);
    }

}