package com.zyyoona7.easypopup.dialogfragment;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.panel.Constants;


/**
 * 动态测量输入法高度的 Dialog fragment 基类
 * Created by zicen on 2019-01-09.
 */
public abstract class BaseKeyBoardDialogFragment extends DialogFragment {
    private static final int TAG_ON_GLOBAL_LAYOUT_LISTENER = -8;
    private static int sDecorViewDelta = 0;

    protected int mKeyboardHeight;
    protected boolean mIsSoftKeyboardPop = false;
    private Window mWindow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 该 style 默认设置了
        // 背景透明
        // NO_TITLE
        // 背后有遮罩
        // 点击外部不可消失
        setStyle(STYLE_NORMAL, setTheme());
        mKeyboardHeight = SPUtils.getInstance().getInt("keyboard_height", ScreenUtils.getScreenHeight() / 3);
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
            wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.gravity = windowGravity();
            window.setAttributes(wlp);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerSoftInputChangedListener();
    }

    /**
     * 注册软键盘监听
     */
    private void registerSoftInputChangedListener() {
        if (getActivity() != null) {
            LogUtils.d("getWindow() != null");
            mWindow = getActivity().getWindow();
            final int flags = mWindow.getAttributes().flags;
            if ((flags & WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) != 0) {
                mWindow.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }
            mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            final FrameLayout contentView = mWindow.findViewById(android.R.id.content);
            final int[] decorViewInvisibleHeightPre = {getDecorViewInvisibleHeight(mWindow)};
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener =
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int height = getDecorViewInvisibleHeight(mWindow);
                    if (decorViewInvisibleHeightPre[0] != height) {
                        onSoftInputChanged(height);
                        decorViewInvisibleHeightPre[0] = height;
                    }
                }
            };
            contentView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
            contentView.setTag(TAG_ON_GLOBAL_LAYOUT_LISTENER, onGlobalLayoutListener);
        }
    }

    protected Window getWindow() {
        return mWindow;
    }

    private int getDecorViewInvisibleHeight(@NonNull final Window window) {
        final View decorView = window.getDecorView();
        final Rect outRect = new Rect();
        decorView.getWindowVisibleDisplayFrame(outRect);
        LogUtils.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - outRect.bottom));
        int delta = Math.abs(decorView.getBottom() - outRect.bottom);


        if (delta <= getNavigationBarHeight() + getStatusBarHeight()) {
            sDecorViewDelta = delta;
            return 0;
        }
        return delta - sDecorViewDelta;
    }

    public static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), Constants.STATUS_BAR_HEIGHT_RES_NAME);
    }

    public static int getNavigationBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), Constants.NAVIGATION_BAR_HEIGHT_RES_NAME);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, Constants.DIMEN, Constants.ANDROID);
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        removeSoftInputChangeListener();
        super.onDestroyView();
    }

    /**
     * 注销软键盘监听
     */
    private void removeSoftInputChangeListener() {
        if (getActivity() != null) {
            Window window = getActivity().getWindow();
            final FrameLayout contentView = window.findViewById(android.R.id.content);
            Object tag = contentView.getTag(TAG_ON_GLOBAL_LAYOUT_LISTENER);
            if (tag instanceof ViewTreeObserver.OnGlobalLayoutListener) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    contentView.getViewTreeObserver()
                            .removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) tag);
                }
            }
        }
    }


    protected void onSoftInputChanged(int height) {
        LogUtils.d("onSoftInputChanged height：" + height);
        // 获取软键盘高度值
        if (height > 100 && height < 1280 && height != mKeyboardHeight) {
            // 保存 到 sp
            mKeyboardHeight = height;
            SPUtils.getInstance().put("keyboard_height", height);
        }

        if (height == 0) {
            mIsSoftKeyboardPop = false;
            onSoftClose();
        } else if (height == mKeyboardHeight) {
            mIsSoftKeyboardPop = true;
            onSoftPop(height);
        }
    }

    /**
     * 获取软键盘是否弹起
     */
    public boolean isSoftKeyboardPop() {
        return mIsSoftKeyboardPop;
    }

    /**
     * 软键盘弹起
     */
    protected abstract void onSoftPop(int height);

    /**
     * 软键盘关闭
     */
    protected abstract void onSoftClose();


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new ProxyDialog(requireContext(), getTheme());
    }

    public ProxyDialog getBaseDialog() {
        return (ProxyDialog) super.getDialog();
    }


    // --- 自定义 ----------------------------------------------------------------------------------
    protected int windowGravity() {
        return Gravity.BOTTOM;
    }

    @StyleRes
    protected int setTheme() {
        return R.style.MissEvan_Dialog;
    }

}
