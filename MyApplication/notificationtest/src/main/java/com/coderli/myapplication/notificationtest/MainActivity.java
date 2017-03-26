package com.coderli.myapplication.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
      int notification_ID;
    private NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Button send_notice= (Button) findViewById(R.id.send_notice);
        Button cancle_notice= (Button) findViewById(R.id.cancle_notice);
        send_notice.setOnClickListener(this);
        cancle_notice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_notice:
               
                Notification.Builder builder=new Notification.Builder(this);
                builder.setSmallIcon(R.mipmap.ic_launcher);//设置图标
                builder.setTicker("hello,having a news");//设置状态栏提示信息
                builder.setWhen(System.currentTimeMillis());
                builder.setContentTitle("状态栏通知");
                builder.setContentText("我来自notificationtest");
                //设置点击后的响应
                Intent intent=new Intent(this,MainActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
                builder.setContentIntent(pendingIntent);
                //设置振动，声音等
                builder.setDefaults(Notification.DEFAULT_ALL);
                
                Notification notification=builder.build();
                
//                notification.ledARGB= Color.GREEN;
//                notification.ledOffMS=1000;
//                notification.ledOnMS=1000;
//                notification.flags=Notification.FLAG_SHOW_LIGHTS;
                
                manager.notify(notification_ID,notification);
                break;
            case R.id.cancle_notice:
                manager.cancel(notification_ID);
                break;
            default:
                break;
        }
    }
}
