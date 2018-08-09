package com.wudh.study.bmob.presenter;

import com.wudh.study.bmob.model.Lost;
import com.wudh.study.bmob.presenter.contract.ShowLostContract;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by wudh on 2018/8/7.
 **/
public class ShowLostPresenter implements ShowLostContract.Presenter {

    private ShowLostContract.View view;

    public ShowLostPresenter(ShowLostContract.View view) {
        this.view=view;
        this.view.setPresenter(this);
    }
    @Override
    public void initData() {
        BmobQuery<Lost> query=new BmobQuery<Lost>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Lost>() {
            @Override
            public void done(List<Lost> list, BmobException e) {
                if (e==null){
                    ArrayList<Lost> lostArrayList=new ArrayList<>();
                    lostArrayList.addAll(list);
                    view.showList(lostArrayList);
                }else {
                    view.showMsg(e.getMessage());
                }
            }
        });
    }
}
