package com.neu.neuer.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neu.neuer.R;

/**
 * Created by fengyuluo on 2017/11/18.
 */

public class CampusHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView imageView;
    TextView mainTitle;
    TextView subTitle;
    LinearLayout title;

    public CampusHolder(View itemView) {
        super(itemView);
        cardView = (CardView)itemView.findViewById(R.id.campus_card);
        mainTitle = (TextView)itemView.findViewById(R.id.main_title);
        subTitle = (TextView)itemView.findViewById(R.id.sub_title);
        imageView = (ImageView)itemView.findViewById(R.id.bg_img);
        title = (LinearLayout)itemView.findViewById(R.id.title);
    }

    public CardView getCardView() {
        return cardView;
    }

    public LinearLayout getTitle() {
        return title;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getMainTitle() {
        return mainTitle;
    }

    public TextView getSubTitle() {
        return subTitle;
    }
}
