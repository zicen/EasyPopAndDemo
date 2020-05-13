package com.zyyoona7.easypopup.switchpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.zyyoona7.easypopup.R;

import java.util.Locale;
import java.util.Random;

/**
 * Created by zicen on 2020-05-12.
 */
public class RoomFragment extends Fragment {
    public static final String TAG = "RoomFragment";
    private Button mButton;

    public static RoomFragment newInstance(long roomId) {
        Bundle args = new Bundle();
        args.putLong("roomId", roomId);
        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        Log.e(TAG, "onCreateView");
        return view;
    }

    public Button getButton() {
        return mButton;
    }

    public void setButton(Button button) {
        mButton = button;
    }

    public void refreshRoomId(long roomId) {
        mButton.setText(String.format(Locale.getDefault(), "房间号: %s", String.valueOf(roomId)));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated");
        mButton = (Button) view.findViewById(R.id.btn_manage);
        long roomId = getArguments().getLong("roomId");
        mButton.setText(String.format(Locale.getDefault(), "房间号: %s", String.valueOf(roomId)));
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("HAHAH");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }
}
