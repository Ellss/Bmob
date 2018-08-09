package com.wudh.study.bmob.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseRecyclerView<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    public Context mContext;

    private int layoutId;

    private View converView;

    public List<T> mData;


    public BaseRecyclerView(Context context, int layoutId, List<T> data) {
        this.mContext = context;
        this.layoutId = layoutId;
        if (data == null) {
            mData = new ArrayList<>();
        } else {
            this.mData = data;
        }

    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        converView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        BaseRecyclerViewHolder holder = BaseRecyclerViewHolder.getInstance(mContext, converView);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        getView(holder,position,mData);
    }

    public void setData(List<T> data){
        this.mData=data;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract void getView(BaseRecyclerViewHolder holder, int position,List<T> mData);

}
