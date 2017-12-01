package com.neu.neuer.entity;

/**
 * Created by fengyuluo on 2017/11/16.
 */

/**
 * 横向图标的实例
 */
public class HIcon {
    private int iId;
    private String title;
    private String subTitle;
    public HIcon(){}
    public HIcon(int iId, String title,String subTitle){
        this.iId = iId;
        this.title = title;
        this.subTitle = subTitle;
    }

    public int getiId(){
        return iId;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setiId(int iId){
        this.iId = iId;
    }
}
