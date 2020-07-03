package com.zyyoona7.easypopup.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.panel.PanelHelper;
import com.zyyoona7.easypopup.panel.PanelSwitchHelper;
import com.zyyoona7.easypopup.panel.interfaces.listener.OnEditFocusChangeListener;
import com.zyyoona7.easypopup.panel.interfaces.listener.OnKeyboardStateListener;
import com.zyyoona7.easypopup.panel.interfaces.listener.OnPanelChangeListener;
import com.zyyoona7.easypopup.panel.interfaces.listener.OnViewClickListener;
import com.zyyoona7.easypopup.panel.view.PanelView;


public class ChatDialogFragment extends DialogFragment implements DialogInterface.OnKeyListener, View.OnTouchListener {

    private static final String TAG = "ChatDialogFragment";

    private PanelSwitchHelper mHelper;
    private View emotionBtn;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_keyborad_comment2, container);
        emotionBtn = view.findViewById(R.id.emotion_btn);
        editText = view.findViewById(R.id.edit_text);
        view.findViewById(R.id.empty_view).setOnTouchListener(this);
        return view;
    }


    /**
     * dialogfragment基于dialog实现，需要设置以下代码
     */
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnKeyListener(this);
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        editText.post(new Runnable() {
            @Override
            public void run() {
                PanelHelper.showKeyboard(getContext(),editText);
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mHelper == null) {
            mHelper = new PanelSwitchHelper.Builder(this)
                    .bindPanelSwitchLayout(R.id.panel_switch_layout)
                    .bindPanelContainerId(R.id.panel_container)
                    .bindContentContainerId(R.id.content_view)
                    //可选
                    .addKeyboardStateListener(new OnKeyboardStateListener() {
                        @Override
                        public void onKeyboardChange(boolean visible) {
                            Log.d(TAG, "系统键盘是否可见 : " + visible);

                        }
                    })
                    //可选
                    .addEdittextFocesChangeListener(new OnEditFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean hasFocus) {
                            Log.d(TAG, "输入框是否获得焦点 : " + hasFocus);
                        }
                    })
                    //可选
                    .addViewClickListener(new OnViewClickListener() {
                        @Override
                        public void onViewClick(View view) {
                            Log.d(TAG, "点击了View : " + view);
                        }
                    })
                    //可选
                    .addPanelChangeListener(new OnPanelChangeListener() {

                        @Override
                        public void onKeyboard() {
                            Log.d(TAG, "唤起系统输入法");
                            // scrollToBottom();
                            emotionBtn.setSelected(false);
                        }

                        @Override
                        public void onNone() {
                            Log.d(TAG, "隐藏所有面板");
                            emotionBtn.setSelected(false);
                        }

                        @Override
                        public void onPanel(PanelView view) {
                            Log.d(TAG, "唤起面板 : " + view);
                            // scrollToBottom();
                            emotionBtn.setSelected(view.getId() == R.id.panel_emotion ? true : false);
                        }

                        @Override
                        public void onPanelSizeChange(PanelView panelView, boolean portrait, int oldWidth, int oldHeight, int width, int height) {
                            Log.d(TAG,"onPanelSizeChange oldHeight:" + oldHeight + ",height:" + height);
                        }
                    })
                    .logTrack(true)             //output log
                    .build(true);

        }
    }


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
            if (mHelper != null && mHelper.hookSystemBackForHindPanel()) {
                return true;
            } else {
                dismiss();
                return true;
            }
        }
        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) {
            mHelper.onDestroy();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        getDialog().dismiss();
        return true;
    }

}
