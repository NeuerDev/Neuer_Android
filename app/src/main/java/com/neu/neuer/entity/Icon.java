package com.neu.neuer.entity;

/**
 * Created by fengyuluo on 2017/11/16.
 */

/**
 * 图标的实例
 */
public class Icon {
    private int iId;
    private int colorId;
    private String iName;
    public Icon(){}
    public Icon(int iId,String iName,int colorId){
        this.iId = iId;
        this.iName = iName;
        this.colorId = colorId;
    }

    public int getColorId() {
        return colorId;
    }

    public int getiId(){
        return iId;
    }
    public String getiName(){
        return iName;
    }
    public void setiId(int iId){
        this.iId = iId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public void setiName(String iName){
        this.iName = iName;
    }
}
