package com.zyyoona7.easypopup.panel.interfaces.listener;

import android.view.View;
import com.zyyoona7.easypopup.panel.PanelSwitchHelper;


/**
 * preventing listeners that {@link PanelSwitchHelper} set these to view from being overwritten
 * Created by yummyLau on 18-7-07
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public interface OnViewClickListener {

    void onViewClick(View view);
}
