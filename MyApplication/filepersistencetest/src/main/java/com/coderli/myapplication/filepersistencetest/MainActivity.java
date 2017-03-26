package com.coderli.myapplication.filepersistencetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button save_data;
   
   
    private TextView text;
     

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save_data= (Button) findViewById(R.id.save_bt);
        editText= (EditText) findViewById(R.id.edittext);
        text= (TextView) findViewById(R.id.text);
        
//        save_data.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                
//                init(data);
//                
//            }
//        });
//        String inputdata=load();
//        text.setText(inputdata);
        String inputdata=load();
       if (!TextUtils.isEmpty(inputdata)){
           editText.setText(inputdata);
           editText.setSelection(inputdata.length());
           Toast.makeText(this, "Restoring succeeded",
                   Toast.LENGTH_SHORT).show();
       }
       
        
       
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String data=editText.getText().toString();
        init(data);
    }

    private void init(String data) {
         FileOutputStream out;
         BufferedWriter writer=null;
        try {
            out=openFileOutput("mydata",MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
            
        }catch (IOException e){
            e.printStackTrace();
            
        }finally {
            try {
                if (writer!=null){
                    writer.close();
                }
                
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in;
        BufferedReader reader=null;
        StringBuilder builder=new StringBuilder();
        try{
            in=openFileInput("mydata");
            reader=new BufferedReader(new InputStreamReader(in));
            String Line ="";
            while((Line=reader.readLine())!=null){
                builder.append(Line);
            } 
            
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           
            if (reader!=null){
                    try{
                    reader.close();
                        
                }catch (IOException e){
                        e.printStackTrace();
                    }
                
            }
        }
        return builder.toString();
    }
    
}
