package com.zyyoona7.easypopup.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.zyyoona7.easypopup.R;


/**
 * Created by zicen on 2019/10/8
 * ToastUtils 二次封装，使用自定义布局来解决部分机型显示不正常的问题（一加的 Toast 变宽）
 */
public class ToastUtil {

    public static void showShort(@StringRes final int resId) {
        View view = getView();
        setText(resId, view, R.id.txt);
        ToastUtils.showCustomShort(view);
    }

    public static void showShort(@NonNull final CharSequence text) {
        View view = getView();
        setText(text, view, R.id.txt);
        ToastUtils.showCustomShort(view);
    }

    public static void showLong(@StringRes final int resId) {
        View view = getView();
        setText(resId, view, R.id.txt);
        ToastUtils.showCustomLong(view);
    }

    public static void showLong(@NonNull final CharSequence text) {
        View view = getView();
        setText(text, view, R.id.txt);
        ToastUtils.showCustomLong(view);
    }

    private static void setText(@StringRes int resId, View view, int p) {
        TextView txt = view.findViewById(p);
        txt.setText(resId);
    }

    private static void setText(@NonNull CharSequence text, View view, int p) {
        TextView txt = view.findViewById(p);
        txt.setText(text);
    }

    @SuppressLint("InflateParams")
    private static View getView() {
        LayoutInflater inflate =
                (LayoutInflater) Utils.getApp().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //noinspection ConstantConditions
        return inflate.inflate(R.layout.toast, null);
    }

    public static void cancel() {
        ToastUtils.cancel();
    }
}
