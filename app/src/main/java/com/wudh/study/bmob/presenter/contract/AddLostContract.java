package com.wudh.study.bmob.presenter.contract;

import com.wudh.study.bmob.model.Lost;
import com.wudh.study.bmob.presenter.BasePresenter;
import com.wudh.study.bmob.ui.BaseView;

/**
 * Created by wudh on 2018/8/7.
 **/
public interface AddLostContract {
    interface View extends BaseView<Presenter>{
        Lost getLost();
        boolean isValid();
    }
    interface Presenter extends BasePresenter{
        void upload();
    }
}
