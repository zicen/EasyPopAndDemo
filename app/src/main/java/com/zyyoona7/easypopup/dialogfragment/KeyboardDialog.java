package com.zyyoona7.easypopup.dialogfragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zyyoona7.easypopup.R;

/**
 * Created by zicen on 2020-01-09.
 */
public class KeyboardDialog extends BaseDialogFragment {

    private DialogInterface.OnDismissListener mOnDismissListener;
    private OnConfirmClickListener mOnConfirmClickListener;

    @Override
    protected int windowGravity() {
        return Gravity.BOTTOM;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getBaseDialog().setCanceledOnTouchOutside(true);
        getBaseDialog().setOnBeforeDimissListener(new ProxyDialog.OnBeforeDimissListener() {
            @Override
            public void onBeforeDimiss() {

            }
        });

        View view = inflater.inflate(R.layout.popup_keyboard, container, false);

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
        super.onDismiss(dialog);
    }


    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        mOnConfirmClickListener = onConfirmClickListener;
    }

    public interface OnConfirmClickListener {
        void onClick(String number, View v);
    }

}
