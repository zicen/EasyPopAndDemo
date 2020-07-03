package com.zyyoona7.easypopup.keyboard;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.keyboard.common.Message;
import com.zyyoona7.easypopup.keyboard.common.MessageAdapter;

import java.util.ArrayList;
import java.util.List;


public class UnresolvedActivity extends AppCompatActivity {

    private EditText et_inputMessage;

    private LinearLayout ll_rootEmojiPanel;
    private RecyclerView mRv_messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unresolved);
        initView();
    }

    private void initView() {
        mRv_messageList = (RecyclerView) findViewById(R.id.rv_messageList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mRv_messageList.setLayoutManager(linearLayoutManager);
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("1"));
        messageList.add(new Message("2"));
        messageList.add(new Message("3"));
        messageList.add(new Message("4"));
        messageList.add(new Message("5"));
        messageList.add(new Message("6"));
        messageList.add(new Message("7"));
        messageList.add(new Message("8"));
        messageList.add(new Message("9"));
        messageList.add(new Message("10"));
        messageList.add(new Message("11"));
        messageList.add(new Message("12"));
        messageList.add(new Message("13"));
        messageList.add(new Message("14"));
        messageList.add(new Message("15"));
        messageList.add(new Message("16"));
        messageList.add(new Message("17"));
        messageList.add(new Message("18"));
        messageList.add(new Message("19"));
        messageList.add(new Message("20"));
        MessageAdapter messageAdapter = new MessageAdapter(this, messageList, R.layout.item_message);
        mRv_messageList.setAdapter(messageAdapter);

        et_inputMessage = (EditText) findViewById(R.id.et_inputMessage);
        ImageView iv_more = (ImageView) findViewById(R.id.iv_more);
        ll_rootEmojiPanel = (LinearLayout) findViewById(R.id.ll_rootEmojiPanel);
        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这是没有解决的时候
                if (ll_rootEmojiPanel.getVisibility() == View.VISIBLE) {
                    showKeyboard();
                } else {
                    hideKeyboard();
                    ll_rootEmojiPanel.setVisibility(View.VISIBLE);
                }

                // 这是解决的代码 主要就是 lockContentViewHeight 方法 和 unlockContentViewHeight 方法
                // lockContentViewHeight();
                // if (ll_rootEmojiPanel.getVisibility() == View.VISIBLE) {
                //     showKeyboard();
                // } else {
                //     hideKeyboard();
                //     ll_rootEmojiPanel.setVisibility(View.VISIBLE);
                // }
                // unlockContentViewHeight();

            }
        });
        et_inputMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    ll_rootEmojiPanel.setVisibility(View.GONE);
                }
            }
        });
        mRv_messageList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    hideKeyboard();
                    ll_rootEmojiPanel.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    /**
     * 锁定内容View以防止跳闪
     */
    private void lockContentViewHeight() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRv_messageList.getLayoutParams();
        layoutParams.height = mRv_messageList.getHeight();
        layoutParams.weight = 0;
    }

    /**
     * 释放锁定的内容View
     */
    private void unlockContentViewHeight() {
        mRv_messageList.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LinearLayout.LayoutParams) mRv_messageList.getLayoutParams()).weight = 1;
            }
        }, 200);
    }

    private void showKeyboard() {
        et_inputMessage.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) et_inputMessage.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et_inputMessage, 0);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        et_inputMessage.clearFocus();
        inputMethodManager.hideSoftInputFromWindow(et_inputMessage.getWindowToken(), 0);
    }




    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_UP && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            if (ll_rootEmojiPanel.getVisibility() == View.VISIBLE) {
//                ll_rootEmojiPanel.setVisibility(View.GONE);
//                return true;
//            }
//        }
        return super.dispatchKeyEvent(event);
    }

}
