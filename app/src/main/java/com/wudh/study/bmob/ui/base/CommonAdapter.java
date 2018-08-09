package com.wudh.study.bmob.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudh on 2018/4/3.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<T> data, int resId){
        mContext=context;
        mInflater= LayoutInflater.from(mContext);
        mItemLayoutId=resId;
        if (mDatas==null){
            mDatas=new ArrayList<>();
        }else {
            mDatas=data;
        }
    }
    @Override
    public int getCount() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<T> data){
        this.mDatas=data;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder holder= BaseViewHolder.get(mContext,convertView,parent,mItemLayoutId,position);
        convert(holder,getItem(position));
        return holder.getConvertView();
    }
    public abstract void convert(BaseViewHolder viewHolder, T item);
}
