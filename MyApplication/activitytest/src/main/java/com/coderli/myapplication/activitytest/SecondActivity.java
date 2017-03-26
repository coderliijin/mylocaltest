package com.coderli.myapplication.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/26.
 */

public class SecondActivity extends BaseActivity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_item);
        textView= (TextView) findViewById(R.id.textView);
        //获取从mainactivity中的数据
        Intent intent=getIntent();
        String data=intent.getStringExtra("extradata");
        textView.setText(data);
        //启动活动的最佳写法
        Button bestrun_activity= (Button) findViewById(R.id.run_fouractivity);
        bestrun_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdActivity.actionstart(SecondActivity.this,"你好","data2");
            }
        });
    }
}
