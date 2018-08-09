package com.wudh.study.bmob.ui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;


/**
 * Created by asus on 2016/8/30.
 */
public class WaitDialog extends ProgressDialog {
    public WaitDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage("请稍后...");
    }

    public WaitDialog(Context context, int theme) {
        super(context, theme);
    }
}
