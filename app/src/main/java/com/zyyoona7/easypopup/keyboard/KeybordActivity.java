package com.zyyoona7.easypopup.keyboard;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.zyyoona7.easypopup.R;

public class KeybordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnShow;
    private Button mBtnShow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keybord);
        mBtnShow = findViewById(R.id.btn_show);
        mBtnShow.setOnClickListener(this);
        mBtnShow2 = findViewById(R.id.btn_show2);
        mBtnShow2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                startActivity(new Intent(KeybordActivity.this,UnresolvedActivity.class));
                break;
            case R.id.btn_show2:
                startActivity(new Intent(KeybordActivity.this,ResolvedActivity.class));
                break;
            default:
                break;
        }
    }
}
