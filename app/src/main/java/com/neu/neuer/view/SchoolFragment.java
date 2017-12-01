package com.neu.neuer.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neu.neuer.R;
import com.neu.neuer.adapter.CampusRecyclerAdapter;
import com.neu.neuer.entity.CampusItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyuluo on 2017/11/13.
 */

public class SchoolFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<CampusItem> campusData;
    private CampusRecyclerAdapter campusRecyclerAdapter;

    public static SchoolFragment newInstance(String paraml){
        SchoolFragment mf = new SchoolFragment();
        Bundle args = new Bundle();
        args.putString("schoolNum",paraml);
        mf.setArguments(args);
        return mf;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.campus_layout,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.campus_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        buildData();
        campusRecyclerAdapter = new CampusRecyclerAdapter(campusData,getActivity());
        recyclerView.setAdapter(campusRecyclerAdapter);
        return view;
    }
    private void buildData(){
        campusData = new ArrayList<CampusItem>();
        campusData.add(new CampusItem("AAAAAAAAA","sssssssssss",R.drawable.aao_campus_background,R.color.lightBlue));
        campusData.add(new CampusItem("AAAAAAAAA","sssssssssss",R.drawable.ecard_campus_background,R.color.lightCard));
        campusData.add(new CampusItem("AAAAAAAAA","sssssssssss",R.drawable.ipgw_campus_background,R.color.lightNet));
        campusData.add(new CampusItem("AAAAAAAAA","sssssssssss",R.drawable.library_campus_background,R.color.lightBook));
    }
}
