package com.neu.neuer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neu.neuer.R;
import com.neu.neuer.entity.CampusItem;
import com.neu.neuer.holder.CampusHolder;

import java.util.List;

/**
 * Created by fengyuluo on 2017/11/18.
 */

public class CampusRecyclerAdapter extends RecyclerView.Adapter<CampusHolder>{
    private List<CampusItem> campusItems;
    private Context context;

    public CampusRecyclerAdapter(List<CampusItem> campusItems,Context context){
        this.campusItems = campusItems;
        this.context = context;
    }

    @Override
    public CampusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.campus_item_layout,parent,false);
        CampusHolder campusHolder = new CampusHolder(v);
        return campusHolder;
    }

    @Override
    public void onBindViewHolder(CampusHolder holder, int position) {
        holder.getCardView().setCardBackgroundColor(context.getResources().getColor(campusItems.get(position).getColorId()));
        holder.getImageView().setImageResource(campusItems.get(position).getPhotoId());
        holder.getMainTitle().setText(campusItems.get(position).getMainTitle());
        holder.getSubTitle().setText(campusItems.get(position).getSubTitle());

    }

    @Override
    public int getItemCount() {
        return campusItems.size();
    }
}
