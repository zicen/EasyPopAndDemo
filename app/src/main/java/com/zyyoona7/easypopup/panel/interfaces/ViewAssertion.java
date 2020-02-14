package com.zyyoona7.easypopup.panel.interfaces;


import com.zyyoona7.easypopup.panel.view.ContentContainer;
import com.zyyoona7.easypopup.panel.view.PanelContainer;
import com.zyyoona7.easypopup.panel.view.PanelSwitchLayout;
import com.zyyoona7.easypopup.panel.view.PanelView;

/**
 *     --------------------
 *    | PanelSwitchLayout  |
 *    |  ----------------  |
 *    | |                | |
 *    | |ContentContainer| |
 *    | |                | |
 *    |  ----------------  |
 *    |  ----------------  |
 *    | | PanelContainer | |
 *    |  ----------------  |
 *     --------------------
 * There are some rules that must be processed:
 * 1. {@link PanelSwitchLayout} must have only two children
 * {@link ContentContainer} and {@link PanelContainer}
 * 2. {@link ContentContainer} must set "edit_view" value to provide {@link android.widget.EditText}
 * 3. {@link PanelContainer} has some Children that are {@link PanelView}
 * {@link PanelView} must set "panel_layout" value to provide panelView and set "panel_trigger"  value to
 * specify layout for click to checkout panelView
 * Created by yummyLau on 18-7-10
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public interface ViewAssertion {

    void assertView();
}
