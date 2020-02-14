package com.zyyoona7.easypopup.panel;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.blankj.utilcode.util.Utils;

/**
 * panel helper
 * Created by yummyLau on 18-7-07
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */

public final class PanelHelper {

    private static final String TAG = PanelHelper.class.getSimpleName();

    /**
     * 显示软键盘
     */
    public static void showKeyboard(Context context, View view) {
        if (context == null) {
            context = Utils.getApp();
        }
        view.requestFocus();
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    /**
     * 隐藏软键盘
     */
    public static void hideKeyboard(Context context, View view) {
        if (context == null) {
            context = Utils.getApp();
        }
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 是否是全屏
     */
    public static boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN)
                == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * 是否是全屏
     */
    public static boolean isFullScreen(Window window) {
        return (window.getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN)
                == WindowManager.LayoutParams.FLAG_FULLSCREEN;
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


    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public static boolean isPortrait(@NonNull Context context) {
        int orientation = context.getResources().getConfiguration().orientation;
        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT: {
                return true;
            }
            case Configuration.ORIENTATION_LANDSCAPE: {
                return false;
            }
            default: {
                Display display = ((WindowManager)
                        context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                if (point.x <= point.y) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }


    @TargetApi(14)
    public static boolean isNavigationBarShow(Context context, Window window) {
        return isNavBarVisible(context, window);
    }

    /**
     * Decorview 源码
     *    public static final ColorViewAttributes NAVIGATION_BAR_COLOR_VIEW_ATTRIBUTES =
     *             new ColorViewAttributes(
     *                     SYSTEM_UI_FLAG_HIDE_NAVIGATION, FLAG_TRANSLUCENT_NAVIGATION,
     *                     Gravity.BOTTOM, Gravity.RIGHT, Gravity.LEFT,
     *                     Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME,
     *                     com.android.internal.R.id.navigationBarBackground,
     *                     0 /* hideWindowFlag
     * @return
     */
    public static boolean isNavBarVisible(Context context, @NonNull final Window window) {
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (viewGroup != null) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                int id = viewGroup.getChildAt(i).getId();
                if (id != View.NO_ID) {
                    String resourceEntryName = context.getResources()
                            .getResourceEntryName(id);
                    if ("navigationBarBackground".equals(resourceEntryName)
                            && viewGroup.getChildAt(i).getVisibility() == View.VISIBLE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取键盘高度
     */
    public static int getKeyBoardHeight(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.KB_PANEL_PREFERENCE_NAME, Context.MODE_PRIVATE);
        boolean isPortrait = PanelHelper.isPortrait(context);
        String key = isPortrait
                ? Constants.KEYBOARD_HEIGHT_FOR_P : Constants.KEYBOARD_HEIGHT_FOR_L;
        float defaultHeight = isPortrait
                ? Constants.DEFAULT_KEYBOARD_HEIGHT_FOR_P : Constants.DEFAULT_KEYBOARD_HEIGHT_FOR_L;
        return sp.getInt(key, dip2px(context, defaultHeight));
    }

    /**
     * 设置键盘高度
     */
    public static boolean setKeyBoardHeight(Context context, int height) {
        SharedPreferences sp = context.getSharedPreferences(Constants.KB_PANEL_PREFERENCE_NAME, Context.MODE_PRIVATE);
        boolean isPortrait = PanelHelper.isPortrait(context);
        //filter wrong data
        //mActivity.getWindow().getDecorView().getHeight() may be right when onGlobalLayout listener
        if (!isPortrait) {
            int portraitHeight = sp.getInt(Constants.KEYBOARD_HEIGHT_FOR_P,
                    dip2px(context, Constants.DEFAULT_KEYBOARD_HEIGHT_FOR_P));
            if (height >= portraitHeight) {
                LogTracker.getInstance().log(TAG + "#setKeyBoardHeight",
                        "filter wrong data : " + portraitHeight + " -> " + height);
                return false;
            }
        }
        String key = PanelHelper.isPortrait(context)
                ? Constants.KEYBOARD_HEIGHT_FOR_P : Constants.KEYBOARD_HEIGHT_FOR_L;
        return sp.edit().putInt(key, height).commit();
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
