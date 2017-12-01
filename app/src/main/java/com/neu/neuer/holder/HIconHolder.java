package com.neu.neuer.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neu.neuer.R;

/**
 * Created by fengyuluo on 2017/11/21.
 */

public class HIconHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title;
    TextView subTitle;
    RelativeLayout relativeLayout;

    public HIconHolder(View itemView) {
        super(itemView);
        relativeLayout = (RelativeLayout)itemView.findViewById(R.id.hscroll_item);
        imageView = (ImageView)itemView.findViewById(R.id.h_image);
        title = (TextView)itemView.findViewById(R.id.title);
        subTitle = (TextView)itemView.findViewById(R.id.sub_title);
    }

    public TextView getSubTitle() {
        return subTitle;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTitle() {
        return title;
    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public void setSubTitle(TextView subTitle) {
        this.subTitle = subTitle;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}
