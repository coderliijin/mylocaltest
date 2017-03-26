package com.coderli.myapplication.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Button run_secondactivity;
    private Button run_thirdActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        run_secondactivity= (Button) findViewById(R.id.second_activity);
        run_thirdActivity= (Button) findViewById(R.id.third_activity);
        run_thirdActivity.setOnClickListener(this);
        run_secondactivity.setOnClickListener(this);
    }

    @Override
    //在活动中使用menu，重写oncreateoptionmenu（），onOptionsItemSelected(）方法。
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_menu:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_menu:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back_menu:
                //退出程序
                ActivityCollector.finishall();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.second_activity:
                Intent intent=new Intent("com.coderli.myapplication.second_action");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                //向下传递数据
                intent.putExtra("extra_data","hello");
                startActivity(intent);
               
               
               
                
                break;
            case R.id.third_activity:
                //更多隐式intent用法
                Intent intent1=new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
