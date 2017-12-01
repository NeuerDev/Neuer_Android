package com.neu.neuer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neu.neuer.R;
import com.neu.neuer.entity.HIcon;
import com.neu.neuer.holder.HIconHolder;

import java.util.List;

/**
 * Created by fengyuluo on 2017/11/21.
 */

public class HIconAdapter extends RecyclerView.Adapter<HIconHolder>{
    List<HIcon> mData;

    public HIconAdapter(List<HIcon> mData){
        this.mData = mData;
    }

    @Override
    public HIconHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_hscroll_layout,parent,false);
        HIconHolder viewholder = new HIconHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(HIconHolder holder, int position) {
        HIcon hIcon = mData.get(position);
        holder.getImageView().setImageResource(hIcon.getiId());
        holder.getTitle().setText(hIcon.getTitle());
        holder.getSubTitle().setText(hIcon.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
