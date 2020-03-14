package com.zyyoona7.easypopup;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zyyoona7.easypopup.base.BaseActivity;
import com.zyyoona7.easypopup.basic.BasicActivity;
import com.zyyoona7.easypopup.demo.TestResizeActivity;
import com.zyyoona7.easypopup.easypop.EasyPopActivity;
import com.zyyoona7.easypopup.easypop.RecyclerViewActivity;
import com.zyyoona7.easypopup.emoji.EmojiActivity;
import com.zyyoona7.easypopup.keyboard.KeybordActivity;
import com.zyyoona7.easypopup.movie.MovieActivity;
import com.zyyoona7.easypopup.surface.SurfaceActivity;
import com.zyyoona7.easypopup.svg.SVGActivity;
import com.zyyoona7.easypopup.toast.ToastActivity;
import com.zyyoona7.easypopup.webp.WebpActivity;

public class MainActivity extends BaseActivity {

    //使用场景 QQ+号，直播礼物弹窗，微信朋友圈评论

    private Button mBasicBtn;

    private Button mEasyBtn;

    private Button mRvBtn;
    private Button mMoviewBtn;
    private Button mWebpBtn;
    private Button mSurface;
    private Button emoji;
    private Button keyboard;
    private Button svg;
    private Button resize;
    private Button toast;

    @Override
    protected int setLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mBasicBtn = (Button) findViewById(R.id.btn_basic);
        mEasyBtn = (Button) findViewById(R.id.btn_easy);
        mRvBtn = (Button) findViewById(R.id.btn_recycler);
        mMoviewBtn = (Button) findViewById(R.id.btn_movie);
        mWebpBtn = (Button) findViewById(R.id.btn_webp);
        mSurface = (Button) findViewById(R.id.surface);
        emoji = (Button) findViewById(R.id.emoji);
        keyboard = (Button) findViewById(R.id.keyboard);
        svg = (Button) findViewById(R.id.svg);
        resize = (Button) findViewById(R.id.resize);
        toast = (Button) findViewById(R.id.toast);
    }

    @Override
    protected void initEvents() {
        mBasicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(BasicActivity.class);
            }
        });
        mEasyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(EasyPopActivity.class);
            }
        });
        mRvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(RecyclerViewActivity.class);
            }
        });
        mMoviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(MovieActivity.class);
            }
        });
        mWebpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(WebpActivity.class);
            }
        });
        mSurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(SurfaceActivity.class);
            }
        });
        emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(EmojiActivity.class);
            }
        });
        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(KeybordActivity.class);
            }
        });
        svg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(SVGActivity.class);
            }
        });
        resize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(TestResizeActivity.class);
            }
        });
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(ToastActivity.class);
            }
        });
    }
}
