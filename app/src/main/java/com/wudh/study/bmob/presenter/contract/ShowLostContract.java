package com.wudh.study.bmob.presenter.contract;

import com.wudh.study.bmob.model.Lost;
import com.wudh.study.bmob.presenter.BasePresenter;
import com.wudh.study.bmob.ui.BaseView;

import java.util.ArrayList;

/**
 * Created by wudh on 2018/8/7.
 **/
public interface ShowLostContract {
    interface View extends BaseView<Presenter>{
        void showList(ArrayList<Lost> list);
    }
    interface Presenter extends BasePresenter{
        void initData();
    }

}
