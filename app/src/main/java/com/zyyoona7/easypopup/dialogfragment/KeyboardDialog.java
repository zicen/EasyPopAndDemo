package com.zyyoona7.easypopup.dialogfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.LogUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.panel.Constants;
import com.zyyoona7.easypopup.panel.LogTracker;

/**
 * Created by zicen on 2020-01-09.
 */
public class KeyboardDialog extends BaseKeyBoardDialogFragment {
    public static final String TAG = KeyboardDialog.class.getSimpleName();
    private static long preClickTime = 0;
    private int currentPanelId = Constants.PANEL_NONE;
    private boolean isCheckoutDoing;
    private Context context;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private OnConfirmClickListener mOnConfirmClickListener;
    private EditText mEdit;
    private View mPanelContainer;
    private View mEmptyView;
    private View mContentView;
    private CheckoutPanelRunnable checkoutPanelRunnable;
    private UnlockContentHeightRunnable unlockContentHeightRunnable;

    @Override
    protected int windowGravity() {
        return Gravity.BOTTOM;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context = getContext();
        // 起初的布局可自动调整大小
        View view = inflater.inflate(R.layout.popup_keyboard, container);
        mEdit = view.findViewById(R.id.edit);
        mPanelContainer = view.findViewById(R.id.panel_container);
        mEmptyView = view.findViewById(R.id.empty_view);
        mContentView = view.findViewById(R.id.content_view);

        final View emotionBtn = view.findViewById(R.id.emotion_btn);
        updatePanelHeight(mKeyboardHeight);

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotionBtn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        emotionBtn.setSelected(false);
                        mPanelContainer.setVisibility(View.GONE);
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    }
                }, 250);
            }
        });

        emotionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotionBtn.setSelected(!emotionBtn.isSelected());
                if (emotionBtn.isSelected()) {
                    // 隐藏输入法，显示 Panel 的切换,如何做到不闪烁？
                    lockContentHeight(mContentView);
                    hideSoft(v);

                    mPanelContainer.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPanelContainer.setVisibility(View.VISIBLE);
                            unlockContentHeight(mContentView);
                        }
                    }, 200);



                } else {

                    // lockContentHeight(mContentContainer);
                    // hidePanel(currentPanelId); currentPanelId = panel
                    // showPanelWithUnlockContentHeight(Constants.PANEL_KEYBOARD);


                    lockContentHeight(mContentView);
                    mPanelContainer.setVisibility(View.GONE);
                    setEmptyViewVisible(false);
                    mPanelContainer.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showSoft(mEdit);
                            setEmptyViewVisible(true);
                            mPanelContainer.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    unlockContentHeight(mContentView);
                                }
                            }, 500);

                        }
                    }, 200);

                }
            }
        });
        return view;
    }

    private void lockContentHeight(@NonNull View contentView) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) contentView.getLayoutParams();
        params.height = contentView.getHeight();
        params.weight = 0.0F;
        contentView.requestLayout();
        int height = contentView.getHeight();
        LogUtils.d(TAG, "lockContentHeight mContentContainer height:" + height);
        LogUtils.d(TAG, "panel_container height:" + mPanelContainer.getHeight());
    }

    private void unlockContentHeight(@NonNull View contentView) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) contentView.getLayoutParams();
        params.height = contentView.getHeight();
        params.weight = 1.0F;
        contentView.requestLayout();
        int height = contentView.getHeight();
        LogUtils.d(TAG, "lockContentHeight mContentContainer height:" + height);
        LogUtils.d(TAG, "panel_container height:" + mPanelContainer.getHeight());
    }

    private void showSoft(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEdit, 0);
    }

    private void hideSoft(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdit.getApplicationWindowToken(), 0);
    }

    private void setEmptyViewVisible(boolean isVisible) {
        mEmptyView.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }



    private boolean checkoutPanel(int toPanelId) {

        if (isCheckoutDoing) {
            LogTracker.Log(TAG + "#checkoutPanel", "is doing checkout, skip!");
            return false;
        }

        isCheckoutDoing = true;

        if (currentPanelId == toPanelId) {
            LogTracker.Log(TAG + "#checkoutPanel", "currentPanelId is the same as toPanelId, it doesn't need to be "
                    + "checkout!");
            isCheckoutDoing = false;
            return true;
        }

        if (toPanelId == Constants.PANEL_NONE) {
            hidePanel(currentPanelId);
            showPanel(Constants.PANEL_NONE);
            isCheckoutDoing = false;
            return true;
        }

        switch (currentPanelId) {
            case Constants.PANEL_NONE: {
                hidePanel(Constants.PANEL_NONE);
                showPanel(toPanelId);
                isCheckoutDoing = false;
                break;
            }
            case Constants.PANEL_KEYBOARD: {
                lockContentHeight(mContentView);
                hidePanel(Constants.PANEL_KEYBOARD);
                showPanelWithUnlockContentHeight(toPanelId);
                break;
            }

            default: {
                if (toPanelId == Constants.PANEL_KEYBOARD) {
                    lockContentHeight(mContentView);
                    hidePanel(currentPanelId);
                    showPanelWithUnlockContentHeight(Constants.PANEL_KEYBOARD);
                } else {
                    hidePanel(currentPanelId);
                    showPanel(toPanelId);
                    isCheckoutDoing = false;
                }
            }
        }
        return true;
    }
    private class CheckoutPanelRunnable implements Runnable {

        int panelId;

        public CheckoutPanelRunnable(int panelId) {
            this.panelId = panelId;
        }

        @Override
        public void run() {
            showPanel(panelId);
            checkoutPanelRunnable = null;
            //need to unlock
            if (panelId != Constants.PANEL_NONE) {
                unlockContentHeightRunnable = new UnlockContentHeightRunnable();
                mPanelContainer.postDelayed(unlockContentHeightRunnable, Constants.DELAY_UNLOCK_CONTENT_TIME);
            } else {
                isCheckoutDoing = false;
            }
        }
    }

    private class UnlockContentHeightRunnable implements Runnable {

        @Override
        public void run() {
            unlockContentHeight(mContentView);

            unlockContentHeightRunnable = null;
            isCheckoutDoing = false;
        }
    }


    /**
     * except Constants.PANEL_NONE，others should
     *
     */
    private void showPanelWithUnlockContentHeight(int panelId) {
        if (checkoutPanelRunnable != null) {
            mPanelContainer.removeCallbacks(checkoutPanelRunnable);
        }
        if (unlockContentHeightRunnable != null) {
            mPanelContainer.removeCallbacks(unlockContentHeightRunnable);
        }
        long delayMillis = 0;
        if (panelId == Constants.PANEL_KEYBOARD) {
            delayMillis = Constants.DELAY_SHOW_KEYBOARD_TIME;
        }
        checkoutPanelRunnable = new CheckoutPanelRunnable(panelId);
        mPanelContainer.postDelayed(checkoutPanelRunnable, delayMillis);
    }

    private void showPanel(int panelId) {
        switch (panelId) {
            case Constants.PANEL_NONE: {
                // mContentContainer.clearFocusByEditText();
                break;
            }
            case Constants.PANEL_KEYBOARD: {
                showSoft(mEdit);
                setEmptyViewVisible(true);
                break;
            }
            default: {
                mPanelContainer.setVisibility(View.VISIBLE);
                setEmptyViewVisible(true);
            }
        }
        setPanelId(panelId);
    }

    private void setPanelId(int panelId) {
        this.currentPanelId = panelId;
        LogTracker.Log(TAG + "#setPanelId", "panel' id :" + currentPanelId);
    }

    private boolean isSamePaneId(int panelId) {
        return this.currentPanelId == panelId;
    }

    private void hidePanel(int panelId) {
        switch (panelId) {
            case Constants.PANEL_NONE: {
                break;
            }
            case Constants.PANEL_KEYBOARD: {
                hideSoft(mEdit);
                setEmptyViewVisible(false);
                break;
            }
            default: {
                mPanelContainer.setVisibility(View.GONE);
                setEmptyViewVisible(false);
            }
        }
    }



    @Override
    protected void onSoftPop(int height) {
        LogUtils.d("onSoftPop:" + height);
        if (mPanelContainer.getVisibility() == View.VISIBLE) {
            mPanelContainer.setVisibility(View.GONE);
        }
        if (height != mPanelContainer.getHeight()) {
            updatePanelHeight(height);
        }
    }

    private void updatePanelHeight(int height) {
        ViewGroup.LayoutParams layoutParams = mPanelContainer.getLayoutParams();
        layoutParams.height = height;
        mPanelContainer.setLayoutParams(layoutParams);
        mPanelContainer.requestLayout();
    }

    @Override
    protected void onSoftClose() {
        LogUtils.d("onSoftClose:");
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
