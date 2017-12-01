package com.neu.neuer.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.neu.neuer.R;
import com.neu.neuer.presenter.MainPresenterCompl;
import com.neu.neuer.service.NetworkStateService;
import com.neu.neuer.util.LogUtil;

public class MainActivity extends AppCompatActivity implements IMainView ,BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    private TodayFragment todayFragment;
    private SchoolFragment schoolFragment;
    private MineFragment mineFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("今天");
        setSupportActionBar(toolbar);

        bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.tab_home,"今天"))
                .addItem(new BottomNavigationItem(R.drawable.tab_campus,"校园"))
                .addItem(new BottomNavigationItem(R.drawable.tab_me,"我的"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);

        setDefaultFragment();

        /**
         * 启动任务
         */
        Intent intent = new Intent(this, NetworkStateService.class);
        intent.setAction("com.text.service.NetworkStateService");
        startService(intent);
    }

    /**
     * 设置默认的首页
     */
    private void setDefaultFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        todayFragment = TodayFragment.newInstance("今天");
        transaction.replace(R.id.tb,todayFragment);
        transaction.commit();

    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position){
            case 0:
                if(todayFragment == null){
                    todayFragment = TodayFragment.newInstance("今天");
                }
                toolbar.setTitle("今天");
                transaction.replace(R.id.tb,todayFragment);
                break;
            case 1:
                if(schoolFragment == null){
                    schoolFragment = SchoolFragment.newInstance("学校");
                }
                toolbar.setTitle("校园");
                transaction.replace(R.id.tb,schoolFragment);
                break;
            case 2:
                if (mineFragment == null){
                    mineFragment = MineFragment.newInstance("我的");
                }
                toolbar.setTitle("我的");
                transaction.replace(R.id.tb,mineFragment);
                break;
            default:
                break;
        }
        //事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 弹出网络状态
     * @param msg
     */
    @Override
    public void popNetState(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
}