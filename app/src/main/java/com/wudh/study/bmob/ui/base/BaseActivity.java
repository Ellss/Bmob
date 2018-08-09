package com.wudh.study.bmob.ui.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.wudh.study.bmob.app.AppConfig;
import com.wudh.study.bmob.app.ApplicationEx;
import com.wudh.study.bmob.app.util.ActivityMangerUtil;
import com.wudh.study.bmob.ui.activity.MainActivity;


public class BaseActivity extends FragmentActivity {

    public Context mContext;

    protected String TAG = this.getClass().getSimpleName();

    protected AppConfig appConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityMangerUtil.getInstance().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        appConfig = ApplicationEx.getApp().getAppConfig();
    }

    protected void show(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (ActivityMangerUtil.getInstance().getCurrentActivity() instanceof MainActivity) {
            exitApp();
        } else {
            super.onBackPressed();
        }
    }

    private long mCurrentTime = 0;

    private void exitApp() {
        if (System.currentTimeMillis() - mCurrentTime > 2000) {
            show("再按一次退出程序！");
        } else {
            ActivityMangerUtil.getInstance().finishActivity(this);
        }
        mCurrentTime = System.currentTimeMillis();
    }

     
}
