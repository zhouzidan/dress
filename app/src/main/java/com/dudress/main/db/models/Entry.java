package com.dudress.main.db.models;

import com.orm.SugarRecord;

/**
 * Created by zhou on 16-4-7.
 */
public class Entry extends SugarRecord{
    long sendtime; //发送时间
    long sendid; //发送的编号，排序
    String title ; //标题
    String subtitle; //副标题
    long loves; //喜欢的人数
    String author; //作者
    String picurl; //条目的配图
    String userid; //作者的用户编号
    String url; //条目的网址
    int type ; //类型{（专栏：1）,（文章：2）,（其他：3）}

    public Entry() {
    }

    public long getSendtime() {
        return sendtime;
    }

    public void setSendtime(long sendtime) {
        this.sendtime = sendtime;
    }

    public long getSendid() {
        return sendid;
    }

    public void setSendid(long sendid) {
        this.sendid = sendid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public long getLoves() {
        return loves;
    }

    public void setLoves(long loves) {
        this.loves = loves;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
