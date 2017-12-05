package com.neu.neuer.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.neu.neuer.R;
import com.neu.neuer.model.IMainModel;
import com.neu.neuer.model.MainModel;
import com.neu.neuer.presenter.MainPresenterCompl;
import com.neu.neuer.util.OkHttpUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fengyuluo on 2017/11/28.
 */

public class NetworkStateService extends Service {
    private static  int noNet = 0;
    private static int noNeu = 1;
    private static int haveNeu = 2;
    private static int CONNECT_TIMEOUT = 1;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    Toast.makeText(NetworkStateService.this, "网络断开", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(NetworkStateService.this, "正使用移动数据，部分服务无法使用", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(NetworkStateService.this, "已连接校园网，可正常使用", Toast.LENGTH_SHORT).show();
                    break;

                default:
                        break;
            }
        //    Toast.makeText(NetworkStateService.this, msg.what+"", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        //注册网络状态的广播，绑定到mReceiver
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                getNetState();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销接收
        unregisterReceiver(mReceiver);
    }

    public void getNetState( ) {
        sendOkHttpRequest("http://baidu.com",new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = noNet;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendOkHttpRequest("http://ipgw.neu.edu.cn",new okhttp3.Callback(){

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
    public void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }


}
