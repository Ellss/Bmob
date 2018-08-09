package com.wudh.study.bmob.app;

import android.app.Application;

import com.wudh.study.bmob.app.util.MySDCardUtil;

import java.io.File;

import cn.bmob.v3.Bmob;
import io.realm.Realm;

/**
 * Created by wudh on 2018/7/13.
 **/
public class ApplicationEx extends Application {

    public static AppConfig appConfig=null;
    private static ApplicationEx app=null;
    private static String SDCardPath="";
    public static String APPID = "22175aa9cd06585bc6eee4bfc24a306a";
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        appConfig=new AppConfig();
        Realm.init(this);
        SDCardPath=initFilePath();
        Bmob.initialize(this,APPID);
    }
    public String initFilePath()
    {
        String filePath="";
        MySDCardUtil.getInstance().init(this);
        String dir=MySDCardUtil.getInstance().getInternalInfo();
        File file=new File(dir+File.separator+"MVPRR");
        if(!file.exists()){
            file.mkdir();
        }
        filePath=file.getAbsolutePath()+File.separator;
        return filePath;
    }
    public String getSDCardPath() {
        return SDCardPath;
    }

    public static ApplicationEx getApp() {
        return app;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }
}
