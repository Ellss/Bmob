package com.wudh.study.bmob.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by wudh on 2018/8/7.
 * 失物表
 **/
public class Lost extends BmobObject {

    private String title;
    private String phone;
    private String describe;
    private BmobFile img;

    public BmobFile getImg() {
        return img;
    }

    public void setImg(BmobFile img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
