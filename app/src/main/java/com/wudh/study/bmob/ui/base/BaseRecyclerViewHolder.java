package com.wudh.study.bmob.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;


public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mSpareArray;


    private Object tag;

    private Context mContext;

    private View mCoverView;



    private BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public BaseRecyclerViewHolder(Context context, View coverView) {
        super(coverView);
        this.mContext = context;
        this.mSpareArray = new SparseArray<>();
        this.mCoverView=coverView;
        mCoverView.setTag(this);
    }

    public static BaseRecyclerViewHolder getInstance(Context context, View coverView) {


        return new BaseRecyclerViewHolder(context, coverView);
    }

    public <T extends View> T getView(int resId) {
        View view = mSpareArray.get(resId);
        if (view == null) {
            view = mCoverView.findViewById(resId);
            mSpareArray.put(resId, view);
        }
        return (T) view;
    }

    public View getmCoverView() {
        return mCoverView;
    }


}
