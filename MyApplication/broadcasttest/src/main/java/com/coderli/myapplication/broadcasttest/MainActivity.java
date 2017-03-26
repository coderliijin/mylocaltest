package com.coderli.myapplication.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    private IntentFilter intentFilter;
    private Networkchangereciver networkchangereciver;
    private Button send_Broadcast,run_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkchangereciver=new Networkchangereciver();
        registerReceiver(networkchangereciver,intentFilter);
        init();
    }
//发送自定义广播
    private void init() {
        send_Broadcast= (Button) findViewById(R.id.send_broadcast);
        send_Broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.coderli.mybroadcasttext");
                //发送标准广播
                //sendBroadcast(intent);
                //发送有序广播,在注册里加上优先级
                sendOrderedBroadcast(intent,null);
            }
        });
        run_activity= (Button) findViewById(R.id.run_activity);
        run_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.startaction(MainActivity.this,null,null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    unregisterReceiver(networkchangereciver);
    }
    class Networkchangereciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "network is unavailable",Toast.LENGTH_SHORT).show();
                        
            }
        }
}

}
