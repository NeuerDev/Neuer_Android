package com.neu.neuer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neu.neuer.R;
import com.neu.neuer.entity.HIcon;
import com.neu.neuer.holder.HIconHolder;
import com.neu.neuer.util.LogUtil;
import com.neu.neuer.view.Ipv6Activity;

import java.util.List;

/**
 * Created by fengyuluo on 2017/11/21.
 */

public class HIconAdapter extends RecyclerView.Adapter<HIconHolder>{
    List<HIcon> mData;
    Context context;
    public HIconAdapter(Context context,List<HIcon> mData){
        this.context = context;
        this.mData = mData;
    }

    @Override
    public HIconHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_hscroll_layout,parent,false);
        final HIconHolder viewholder = new HIconHolder(view);
        viewholder.getRelativeLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { 
                if(viewholder.getTitle().getText().toString().equals("电视直播")){
                    Intent intent = new Intent(context, Ipv6Activity.class);
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "敬请期待", Toast.LENGTH_SHORT).show();
                }

            }
        });
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
