package com.wudh.study.bmob.presenter;

import android.text.TextUtils;

import com.wudh.study.bmob.app.common.BmobManager;
import com.wudh.study.bmob.listener.BmobLoginCallback;
import com.wudh.study.bmob.listener.BmobMsgSendCallback;
import com.wudh.study.bmob.listener.BmobSignUpCallback;
import com.wudh.study.bmob.app.util.LoginHelperUtil;
import com.wudh.study.bmob.model.User;
import com.wudh.study.bmob.presenter.contract.LoginContract;

import cn.bmob.v3.exception.BmobException;

/**
 * Created by wudh on 2018/8/3.
 **/
public class LoginPresenter implements LoginContract.Presenter {

    public LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view=view;
        this.view.setPresenter(this);
    }

    @Override
    public void login(String username,String password) {
        if (LoginHelperUtil.isPhoneNumber(username)) {
            BmobManager.getInstance(new BmobLoginCallback() {
                @Override
                public void onLoginSuccess() {
                    view.showMsg("登录成功!");
                    view.goToMainActivity();
                }

                @Override
                public void onLoginFailure() {
                    view.showMsg("登录失败!");
                }
            }).login(username,password);
        } else {
            view.showMsg("手机号输入不正确");
        }
    }

    @Override
    public void signUp(String username,String msgCode,String password1,String password2) {
            if (LoginHelperUtil.isPhoneNumber(username) && LoginHelperUtil.isCodeCorrect(msgCode)
                    && TextUtils.equals(password1,password2)) {
                BmobManager.getInstance(new BmobSignUpCallback() {
                    @Override
                    public void onSignUpSuccess(User user) {
//                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
//                        intent.putExtra(INTENT_USER,user);
//                        startActivity(intent);
                        view.showMsg("注册成功");
                    }

                    @Override
                    public void onSignUpFailure(BmobException e) {
                        view.showMsg("注册失败");
                    }
                }).signUp(username,msgCode,password1);
            } else {
                view.showMsg("您输入的信息不正确，请检查你的输入");
            }
        }

    @Override
    public void getCode(String username) {
        if (LoginHelperUtil.isPhoneNumber(username)) {
            BmobManager.getInstance(new BmobMsgSendCallback() {
                @Override
                public void onMsgSendSuccess() {
                    view.showMsg("验证码已成功发送至您的手机，请注意查收");
                    //验证码发送成功，倒计时
                    view.setCodeTimeDown();
                }

                @Override
                public void onMsgSendFailure() {
                    view.showMsg("验证码发送失败");
                }
            }).sendMsgCode(username);
        } else {
            view.showMsg("手机号输入不正确");
        }
    }
}
