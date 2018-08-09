package com.wudh.study.bmob.app.util;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by asus on 2016/8/30.
 */
public class SnackbarUtil {

    private static Snackbar snackbar;
    private static Snackbar longSnackbar;

    public static void show(View view, CharSequence text) {
        if (snackbar == null) {
            snackbar = Snackbar.make(view,text, snackbar.LENGTH_SHORT);
        }
        snackbar.setText(text);
        snackbar.show();
    }

    public static void show(View view, @StringRes int textRes) {
        if (snackbar == null) {
            snackbar = Snackbar.make(view,textRes, snackbar.LENGTH_SHORT);
        }
        snackbar.setText(textRes);
        snackbar.show();
    }

    public static void showLong(View view, CharSequence text) {
        if (longSnackbar == null) {
            longSnackbar = Snackbar.make(view,text, snackbar.LENGTH_LONG);
        }
        longSnackbar.setText(text);
        longSnackbar.show();
    }

    public static void showLong(View view, @StringRes int textRes) {
        if (longSnackbar == null) {
            longSnackbar = Snackbar.make(view,textRes, snackbar.LENGTH_LONG);
        }
        longSnackbar.setText(textRes);
        longSnackbar.show();
    }
}
