package com.coderli.myapplication.databasetest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyopenHelper mHelper;
    private EditText book_name,book_author,book_prices,book_pages;
    private Button insert_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper=new MyopenHelper(this,"BookStore.db",null,1);
       
        book_author= (EditText) findViewById(R.id.book_author);
        book_name= (EditText) findViewById(R.id.book_name);
        book_pages= (EditText) findViewById(R.id.book_pages);
        book_prices= (EditText) findViewById(R.id.book_prices);
        insert_data= (Button) findViewById(R.id.insert_data);
        insert_data.setOnClickListener(this);
       
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert_data:
                SQLiteDatabase db=mHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                String author=book_author.getText().toString().trim();
                String name=book_name.getText().toString().trim();
                Integer pages=Integer.parseInt(book_pages.getText().toString());
                Double prices=Double.parseDouble(book_prices.getText().toString());
               
                values.put("name",name);
                values.put("author",author);
                values.put("price",prices);
                values.put("pages",pages);
                db.insert("book",null,values);
                values.clear();
                break;
            default:
                break;
        }
    }
}
