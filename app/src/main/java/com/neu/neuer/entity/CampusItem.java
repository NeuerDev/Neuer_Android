package com.neu.neuer.entity;

/**
 * Created by fengyuluo on 2017/11/18.
 */

public class CampusItem {
    private String mainTitle;
    private String subTitle;
    private int photoId;
    private int colorId;
    public CampusItem(String mainTitle,String subTitle,int photoId,int colorId){
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.photoId = photoId;
        this.colorId = colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public int getColorId() {
        return colorId;
    }

    public String getSubTitle() {
        return subTitle;
    }
}
