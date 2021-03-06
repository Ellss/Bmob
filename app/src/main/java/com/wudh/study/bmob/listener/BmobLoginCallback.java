package com.wudh.study.bmob.listener;


import com.wudh.study.bmob.model.BaseModel;
import com.wudh.study.bmob.model.User;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by asus on 2016/9/10.
 */
public abstract class BmobLoginCallback implements IBmobListener {
    @Override
    public void onMsgSendSuccess() {

    }

    @Override
    public void onMsgSendFailure() {

    }

    @Override
    public void onSignUpSuccess(User user) {

    }

    @Override
    public void onSignUpFailure(BmobException e) {

    }

    @Override
    public void onQuerySuccess(List<? extends BaseModel> dataList) {

    }

    @Override
    public void onQueryFailure(BmobException e) {

    }
}
