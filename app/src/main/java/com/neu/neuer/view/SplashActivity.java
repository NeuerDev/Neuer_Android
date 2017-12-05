package com.neu.neuer.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.neu.neuer.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SHOW_TIME_MIN = 2000;// 最小显示时间
    private long mStartTime;// 开始时间
    private boolean IsFirst;//第一次进入应用

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    long loadingTime = System.currentTimeMillis() - mStartTime;// 计算一下总共花费的时间
                    if (loadingTime < SHOW_TIME_MIN) {
                        // 如果比最小显示时间还短，就延时进入MainActivity，否则直接进入
                        mHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN
                                - loadingTime);
                    } else {
                        mHandler.post(goToMainActivity);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    Runnable goToMainActivity = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.spalash_layout);

        mStartTime = System.currentTimeMillis();//记录开始时间1

        mHandler.sendEmptyMessage(1);
    }
}
