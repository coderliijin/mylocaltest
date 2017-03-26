package com.coderli.myapplication.broadcasttest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/26.
 */

public class ActivityCollector {
    public static  List<Activity> activities=new ArrayList<>();
    public static void addactivity(Activity activity){
        activities.add(activity);
    }
    public static void removeactivity(Activity activity){
        activities.remove(activity);
    }
    public  static void finishall(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
