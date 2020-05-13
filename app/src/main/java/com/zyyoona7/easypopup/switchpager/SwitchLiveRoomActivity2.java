package com.zyyoona7.easypopup.switchpager;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.blankj.utilcode.util.LogUtils;
import com.zyyoona7.easypopup.R;

import java.util.ArrayList;

public class SwitchLiveRoomActivity2 extends AppCompatActivity {

    public static final String TAG = "SwitchLiveRoomActivitySwitchLiveRoomActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_live_room2);
        FrameLayout frameLayout = findViewById(R.id.frame_layout);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.frame_layout, MyLiveFragment.newInstance()).commitAllowingStateLoss();
    }
}
