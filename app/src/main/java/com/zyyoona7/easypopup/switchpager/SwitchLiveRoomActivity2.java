package com.zyyoona7.easypopup.switchpager;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.FrameLayout;

import com.zyyoona7.easypopup.R;

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
