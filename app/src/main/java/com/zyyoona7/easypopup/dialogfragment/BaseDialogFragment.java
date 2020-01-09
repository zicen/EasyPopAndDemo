package com.zyyoona7.easypopup.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.blankj.utilcode.util.LogUtils;
import com.zyyoona7.easypopup.R;


/**
 * Dialog fragment 基类
 * Created by yangya on 2019-12-17.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 该 style 默认设置了
        // 背景透明
        // NO_TITLE
        // 背后有遮罩
        // 点击外部不可消失
        setStyle(STYLE_NORMAL, setTheme());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // window 默认：
        // 宽度填满，高度 wrap，padding 0
        final Window window = getDialog().getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            wlp.gravity = windowGravity();
            window.setAttributes(wlp);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LogUtils.d("onCreateDialog");
        return new ProxyDialog(requireContext(), getTheme());
    }

    public ProxyDialog getBaseDialog() {
        return (ProxyDialog) super.getDialog();
    }


    // --- 自定义 ----------------------------------------------------------------------------------
    protected int windowGravity() {
        return Gravity.CENTER;
    }

    @StyleRes
    protected int setTheme() {
        return R.style.MissEvan_Dialog;
    }
}
