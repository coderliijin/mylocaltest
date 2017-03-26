package com.coderli.myapplication.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/3/2.
 */

public class MyopenHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK="create table book("+"id integer primary key autoincrement,"+"name text,"+"author text,"+"price real,"+"pages integer)";
    public static final String CREATE_CATEGORY="create table category("+"id integer primary key autoincrement,"+"category_name text,"+"category_code integer)";
    private Context mcontext;
    public MyopenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
       //Toast.makeText(mcontext,"succeed_create",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_CATEGORY);
                default:
                   
        }

    }
}
