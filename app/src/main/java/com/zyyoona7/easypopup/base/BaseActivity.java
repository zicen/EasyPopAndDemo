package com.zyyoona7.easypopup.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zyyoona7 on 2017/8/2.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setLayoutId() != 0) {
            setContentView(setLayoutId());
        }

        initVariables();
        initViews();
        initEvents();
    }

    protected abstract int setLayoutId();

    protected abstract void initVariables();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected void goTo(Class clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
    }
}
