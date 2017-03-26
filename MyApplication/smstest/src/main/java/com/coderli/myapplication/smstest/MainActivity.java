package com.coderli.myapplication.smstest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView sender;
    private TextView content;
    private IntentFilter receiverFilter;
    private BroadcastReceiver mymessagereceiver;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender= (TextView) findViewById(R.id.sender);
        content= (TextView) findViewById(R.id.content);
       receiverFilter =new IntentFilter();
        receiverFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        receiverFilter.setPriority(100);
        mymessagereceiver=new MessageReceiver();
        registerReceiver(mymessagereceiver,receiverFilter);
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    unregisterReceiver(mymessagereceiver);
    }

    class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
             Bundle bundle=intent.getExtras();
            Object[]pdus= (Object[]) bundle.get("pdus");
            SmsMessage smsMessage[]=new SmsMessage[pdus.length];
            for (int i = 0; i <smsMessage.length ; i++) {
                smsMessage[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                
            }
            String address=smsMessage[0].getOriginatingAddress();
            String fullmessage="";
            for (SmsMessage message:smsMessage){
                fullmessage+=message.getMessageBody();
            }
            sender.setText(address);
            content.setText(fullmessage);
            
            Notification.Builder builder=new Notification.Builder(context);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setTicker("a new message");
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle(address);
            builder.setContentText("新消息");
            
            Intent intents=new Intent(context,MainActivity.class);
            PendingIntent pendintent=PendingIntent.getActivity(context,0,intents,0);
            builder.setContentIntent(pendintent);
            
            builder.setDefaults(Notification.DEFAULT_ALL);
            Notification notification=builder.build();
            manager.notify(1,notification);
            abortBroadcast();
        }
    }
}
