package com.zyyoona7.easypopup.panel;


/**
 * Created by yummyLau on 18-7-07
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public class Constants {

    public static final String LOG_TAG = "Panel";
    public static final String KB_PANEL_PREFERENCE_NAME = "ky_panel_name";
    public static final String KEYBOARD_HEIGHT_FOR_L = "keyboard_height_for_l";
    public static final String KEYBOARD_HEIGHT_FOR_P = "keyboard_height_for_p";

    public static final float DEFAULT_KEYBOARD_HEIGHT_FOR_L = 263f;
    public static final float DEFAULT_KEYBOARD_HEIGHT_FOR_P = 198f;

    public static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
    public static final String NAVIGATION_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
    public static final String DIMEN = "dimen";
    public static final String ANDROID = "android";

    /**
     * panel id, custom panel (PanelView) id is panelView's triggerViewId
     * {@link PanelView#getTriggerViewId()}
     */
    public static final int PANEL_NONE = -1;
    public static final int PANEL_KEYBOARD = 0;


    public static final long DELAY_SHOW_KEYBOARD_TIME = 200L;
    public static final long DELAY_UNLOCK_CONTENT_TIME = 500L;

    public static final long PROTECT_KEY_CLICK_DURATION = 500L;

    static boolean DEBUG = false;
}
