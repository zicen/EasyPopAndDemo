package com.zyyoona7.easypopup.dialogfragment;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;

/**
 * Created by yangya on 2019-12-18.
 */
public class ProxyDialog extends AppCompatDialog {

    private OnBeforeDimissListener mOnBeforeDimissListener;

    public ProxyDialog(Context context) {
        super(context);
    }

    public ProxyDialog(Context context, int theme) {
        super(context, theme);
    }

    protected ProxyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void dismiss() {
        if (mOnBeforeDimissListener != null) {
            mOnBeforeDimissListener.onBeforeDimiss();
        }
        super.dismiss();
    }

    public void setOnBeforeDimissListener(OnBeforeDimissListener onBeforeDimissListener) {
        mOnBeforeDimissListener = onBeforeDimissListener;
    }

    public interface OnBeforeDimissListener {
        void onBeforeDimiss();
    }

}
