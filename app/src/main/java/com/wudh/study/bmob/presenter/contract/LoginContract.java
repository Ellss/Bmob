package com.wudh.study.bmob.presenter.contract;

import com.wudh.study.bmob.presenter.BasePresenter;
import com.wudh.study.bmob.ui.BaseView;


public interface LoginContract {
    interface Presenter extends BasePresenter {
        void login(String username, String password);
        void signUp(String username, String msgCode, String password1, String password2);
        void getCode(String username);
    }

    interface View extends BaseView<Presenter> {

        void goToMainActivity();
        void setCodeTimeDown();
    }
}
