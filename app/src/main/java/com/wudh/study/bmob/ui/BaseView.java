package com.wudh.study.bmob.ui;

import android.view.View;

/**
 * Created by wudh on 2018/7/30.
 **/
public interface BaseView<T> {
    void initViews(View view);
    void setPresenter(T presenter);
    void showMsg(String msg);
    void showProgressDialog(String msg);
    void dismissProgressDialog();
}
