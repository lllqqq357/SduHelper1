package com.example.sduhelper.tool;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 顾文涛 on 2016/12/30.
 * 一个工具类，用于存放所有的Activity，便于一件销毁
 * 在BaseActivity中使用
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
