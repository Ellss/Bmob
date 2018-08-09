package com.wudh.study.bmob.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by wudh on 2018/8/3.
 **/
public class User extends BmobUser {
    private BmobFile icon;
    private String sex;

    public BmobFile getIcon() {
        return icon;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
