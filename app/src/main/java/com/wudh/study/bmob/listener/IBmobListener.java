package com.wudh.study.bmob.listener;

import com.wudh.study.bmob.model.BaseModel;
import com.wudh.study.bmob.model.User;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by asus on 2016/9/9.
 */
public interface IBmobListener {

    void onMsgSendSuccess();
    void onMsgSendFailure();
    void onLoginSuccess();
    void onLoginFailure();
    void onSignUpSuccess(User user);
    void onSignUpFailure(BmobException e);
    void onQuerySuccess(List<? extends BaseModel> dataList);
    void onQueryFailure(BmobException e);
}
