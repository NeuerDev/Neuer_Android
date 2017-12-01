package com.neu.neuer.model;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.neu.neuer.util.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by fengyuluo on 2017/11/27.
 */

public class MainModel implements IMainModel {
    private static  int noNet = 0;
    private static int noNeu = 1;
    private static int haveNeu = 2;
    private NetListener netListener;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            netListener.popNetState(msg.what);
        }
    };
    @Override
    public void getNetState(final NetListener netListener) {
        this.netListener = netListener;
        OkHttpUtil.sendOkHttpRequest("http://baidu.com",new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = noNet;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                OkHttpUtil.sendOkHttpRequest("http://ipgw.neu.edu.cn",new okhttp3.Callback(){

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = new Message();
                        msg.what = noNeu;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message msg = new Message();
                        msg.what = haveNeu;
                        handler.sendMessage(msg);
                    }
                });
            }
        });
    }
}
