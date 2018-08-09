package com.wudh.study.bmob.app.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wudh on 2018/3/7.
 * 标准的单例模式
 * Activity管理类
 */

public class ActivityMangerUtil {

    private static Stack<Activity> activityStack;

    private static volatile ActivityMangerUtil activityMangerUtil;

    private ActivityMangerUtil() {

    }

    public static ActivityMangerUtil getInstance() {
        if (activityMangerUtil==null){
            synchronized (ActivityMangerUtil.class){
                if (activityMangerUtil==null){
                    activityMangerUtil=new ActivityMangerUtil();
                    activityStack = new Stack<>();
                }
            }
        }
        return activityMangerUtil;
    }

    public void addActivity(Activity activity) {
        if (null != activity) {
            activityStack.add(activity);
        }
    }

    public Activity getCurrentActivity() {
        return activityStack.lastElement();
    }

    public void finishActivity(Activity activity) {
        if (null != activity && activityStack.search(activity) != -1 && activityStack.size() > 0) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

}
