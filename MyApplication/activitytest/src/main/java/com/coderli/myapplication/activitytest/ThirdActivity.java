package com.coderli.myapplication.activitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/26.
 */

public class ThirdActivity extends BaseActivity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_item);
        textView= (TextView) findViewById(R.id.text2);
        Intent intent2=getIntent();
        String data=intent2.getStringExtra("param1");
        textView.setText(data);

    }
    public static void actionstart(Context context,String data1,String data2){
        Intent intent=new Intent(context,ThirdActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }
}
