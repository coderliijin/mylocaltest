package com.coderli.myapplication.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/27.
 */

public class SecondActivity extends BaseActivity {
    private Button button;
    private LocalBroadcastManager localBroadcastManager;
    
    private Localreceiver localreceiver;
    private IntentFilter intentFilter;
     

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_item);
        
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        
        button= (Button) findViewById(R.id.local_receiver);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.coderli.localbroadcast");
                localBroadcastManager.sendBroadcast(intent);//发送本地广播
            }
        });
        localreceiver=new Localreceiver();
        intentFilter=new IntentFilter();
        intentFilter.addAction("com.coderli.localbroadcast");
        
        localBroadcastManager.registerReceiver(localreceiver,intentFilter);//注册本地广播监听器
       ExitApp();
    }

    private void ExitApp() {
        Button exitbt= (Button) findViewById(R.id.exit);
        exitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.coderli.exitmyapp");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localreceiver);
    }

    class Localreceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast",
                    Toast.LENGTH_SHORT).show();

        }
    }
    public static void startaction(Context context,String a,String b){
        Intent intent=new Intent(context,SecondActivity.class);
        intent.putExtra("params",a);
        intent.putExtra("params2",b);
        context.startActivity(intent);
        
    }
}
