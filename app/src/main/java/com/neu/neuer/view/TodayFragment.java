package com.neu.neuer.view;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.neu.neuer.R;
import com.neu.neuer.adapter.HIconAdapter;
import com.neu.neuer.adapter.RotateVpAdapter;
import com.neu.neuer.base.CommonAdapter;
import com.neu.neuer.base.ViewHolder;
import com.neu.neuer.entity.HIcon;
import com.neu.neuer.entity.Icon;
import com.neu.neuer.entity.News;
import com.neu.neuer.entity.RotateBean;
import com.neu.neuer.presenter.ITodayPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyuluo on 2017/11/13.
 */

public class TodayFragment extends Fragment implements ITodayView,View.OnClickListener {

    private static final int TIME = 5000;

    private ViewPager viewPager;
    private LinearLayout pointLl;// 轮播状态改变的小圆点容器
    private List<RotateBean> datas;
    private RotateVpAdapter vpAdapter;
    private ITodayPresenter iTodayPresenter;

    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;
    //icon
    private GridView gridView;
    private ArrayList<Icon> iconData;
    //
    private RecyclerView hIcon;
    private ArrayList<HIcon> hIconData;

    private ListView listView;
    private ArrayList<News> newsData;


    public static TodayFragment newInstance(String paraml){
        TodayFragment mf = new TodayFragment();
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
       View view = inflater.inflate(R.layout.today_layout,container,false);
       Bundle bundle = getArguments();
       viewPager = (ViewPager)view.findViewById(R.id.rotate_vp);
       pointLl = (LinearLayout)view.findViewById(R.id.rotate_point_container);
       String schoolNum = bundle.getString("schoolNum");

       buildDatas();//构造数据

        vpAdapter = new RotateVpAdapter(datas,getActivity());
        viewPager.setAdapter(vpAdapter);
        // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
        // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
        viewPager.setCurrentItem(datas.size() * 100);

        //开始轮播
        handler = new Handler();
        startRotate();
        //添加轮播小点
        addPoints();
        //随轮播改变小点
        changePoint();

        gridView = (GridView)view.findViewById(R.id.grid_icon);
        gridView.setAdapter(new CommonAdapter<Icon>(getActivity(),iconData,R.layout.icon_item_layout) {
            @Override
            public void convert(ViewHolder helper, Icon item) {
                CardView cardView = helper.getView(R.id.icon_card);
                cardView.setCardBackgroundColor(getResources().getColor(item.getColorId()));
                ImageView  imageView = helper.getView(R.id.img_icon);
                imageView.setImageResource(item.getiId());
                TextView textView = helper.getView(R.id.text_icon);
                textView.setText(item.getiName());
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tag = (TextView)view.findViewById(R.id.text_icon);
                String str = tag.getText().toString();
                if(str.equals("一键联网")){
                    Intent intent = new Intent(getActivity(),NetActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //消除gridView的点击效果
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));


        hIcon = (RecyclerView)view.findViewById(R.id.h_icon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hIcon.setLayoutManager(linearLayoutManager);
        HIconAdapter hIconAdapter = new HIconAdapter(getActivity(),hIconData);

        hIcon.setAdapter(hIconAdapter);
        listView = (ListView)view.findViewById(R.id.news_list);
        listView.setAdapter(new CommonAdapter<News>(getActivity(),newsData,R.layout.news_item_layout) {

            @Override
            public void convert(ViewHolder helper, News item) {
                TextView textView = helper.getView(R.id.news_text);
                textView.setText(item.getContent());
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void setPicture(String[] purls) {

    }

    @Override
    public void setTimetable(String timetable) {

    }

    @Override
    public void setSchoolNews(String news) {

    }
    public void buildDatas(){
        //轮播图数据
        datas = new ArrayList<>();
        datas.add(new RotateBean(R.drawable.home1));
        datas.add(new RotateBean(R.drawable.home2));
        datas.add(new RotateBean(R.drawable.home3));

        //图标数据
        iconData = new ArrayList<>();

        iconData.add(new Icon(R.drawable.home_access_rank,"成绩查询",R.color.lightGrade));
        iconData.add(new Icon(R.drawable.home_access_net,"一键联网",R.color.lightNet));
        iconData.add(new Icon(R.drawable.home_access_book,"图书检索",R.color.lightBook));
        iconData.add(new Icon(R.drawable.home_access_card,"校卡余额",R.color.lightCard));
//        iconData.add(new Icon(R.drawable.home_activity_television,"电视直播"));
//        iconData.add(new Icon(R.drawable.home_activity_arcampus,"AR校园"));


        //横向Icon数据
        hIconData = new ArrayList<>();
        hIconData.add(new HIcon(R.drawable.home_activity_arcampus,"AR校园","玩转校园，体验最酷AR"));
        hIconData.add(new HIcon(R.drawable.home_activity_television,"电视直播","校内免费电视"));
        hIconData.add(new HIcon(R.drawable.home_activity_share,"资源共享","东大人共享云平台"));


        //新闻数据
        newsData = new ArrayList<>();
        newsData.add(new News("东北大学特大新闻"));
        newsData.add(new News("东北大学特大新闻东北大学特大新闻东北大学特大新闻"));
        newsData.add(new News("东北大学特大新闻"));
        newsData.add(new News("东北大学特大新闻"));
        newsData.add(new News("东北大学特大新闻东北大学特大新闻东北大学特大新闻"));
        newsData.add(new News("东北大学特大新闻"));
        newsData.add(new News("东北大学特大新闻"));
        newsData.add(new News("东北大学特大新闻东北大学特大新闻东北大学特大新闻"));
        newsData.add(new News("东北大学特大新闻"));
    }
    private void changePoint(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(isRotate){
                    //将所有小点设置为白色
                    for(int i=0;i<datas.size();i++){
                        ImageView pointIv = (ImageView)pointLl.getChildAt(i);
                        pointIv.setImageResource(R.drawable.point_white);
                    }
                    //设置当前位置小点为灰色
                    ImageView iv = (ImageView)pointLl.getChildAt(position%datas.size());
                    iv.setImageResource(R.drawable.point_grey);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /**
     * 添加轮播的小点
     */
    private void addPoints(){
        // 有多少张图加载多少个小点
        for(int i=0;i<datas.size();i++){
            ImageView pointIv = new ImageView(getActivity());
            pointIv.setPadding(5,5,5,5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            pointIv.setLayoutParams(params);
            // 设置第0页小点的为灰色
            if(i==0){
                pointIv.setImageResource(R.drawable.point_grey);
            }else{
                pointIv.setImageResource(R.drawable.point_white);
            }
            pointLl.addView(pointIv);
        }
    }

    /**
     * 开始轮播
     */
    private void startRotate(){
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
                if(isRotate){
                    handler.postDelayed(rotateRunnable,TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable,TIME);
    }
    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }
}
