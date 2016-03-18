package com.zhou.dress.bean;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by zhou on 16-3-18.
 */
public class ResultBean {
    public int code ;
    public String msg;
    public JSONObject obj;
    public JSONArray objArray;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }

    public JSONArray getObjArray() {
        return objArray;
    }

    public void setObjArray(JSONArray objArray) {
        this.objArray = objArray;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                ", objArray=" + objArray +
                '}';
    }
}
