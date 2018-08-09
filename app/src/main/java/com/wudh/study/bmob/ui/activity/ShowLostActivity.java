package com.wudh.study.bmob.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.wudh.study.bmob.R;
import com.wudh.study.bmob.model.Lost;
import com.wudh.study.bmob.presenter.ShowLostPresenter;
import com.wudh.study.bmob.presenter.contract.ShowLostContract;
import com.wudh.study.bmob.ui.base.BaseActivity;
import com.wudh.study.bmob.ui.base.BaseRecyclerView;
import com.wudh.study.bmob.ui.base.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudh on 2018/8/7.
 **/
public class ShowLostActivity extends BaseActivity implements ShowLostContract.View{

    private ShowLostContract.Presenter presenter;
    private RecyclerView recyclerView;
    private ProgressDialog pd;
    private ArrayList<Lost> list=new ArrayList<>();
    private BaseRecyclerView adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlost);
        presenter=new ShowLostPresenter(this);
        initView();
        presenter.initData();
    }

    private void initView(){

        recyclerView=findViewById(R.id.list_showlost);
        LinearLayoutManager layoutManager=new LinearLayoutManager(ShowLostActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(ShowLostActivity.this,DividerItemDecoration.VERTICAL));
        adapter=new BaseRecyclerView<Lost>(ShowLostActivity.this,R.layout.item_lost,list) {
            @Override
            public void getView(BaseRecyclerViewHolder holder, int position, List<Lost> mData) {
                AppCompatTextView tvTitle=holder.getView(R.id.tv_title);
                AppCompatTextView tvDescribe=holder.getView(R.id.tv_describe);
                AppCompatTextView tvTime=holder.getView(R.id.tv_time);
                AppCompatTextView tvPhone=holder.getView(R.id.tv_phone);
                AppCompatImageView ivLost=holder.getView(R.id.iv_lost);
                tvTitle.setText(mData.get(position).getTitle().toString());
                tvDescribe.setText(mData.get(position).getDescribe().toString());
                tvTime.setText(mData.get(position).getUpdatedAt().toString());
                tvPhone.setText(mData.get(position).getPhone().toString());
                if (mData.get(position).getImg()!=null){
                    Glide.with(mContext).load(mData.get(position).getImg().getFileUrl()).into(ivLost);
                }

            }
        };
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void showList(ArrayList<Lost> list) {
        adapter.setData(list);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void setPresenter(ShowLostContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void showMsg(String msg) {
        Snackbar.make(recyclerView,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog(String msg) {
        if (pd==null){
            pd=new ProgressDialog(ShowLostActivity.this);
            pd.setMessage(msg);
            pd.show();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (pd!=null){
            pd.dismiss();
        }
    }
}
