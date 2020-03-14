package com.zyyoona7.easypopup.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.blankj.utilcode.util.ToastUtils;
import com.zyyoona7.easypopup.R;

public class ToastActivity extends AppCompatActivity {
    private Button btnToast;
    private Button btnToast2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);



        btnToast = (Button) findViewById(R.id.btn_toast);
        btnToast2 = (Button) findViewById(R.id.btn_toast2);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("aaaa");
            }
        });
        btnToast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort("aaaa");
            }
        });
    }
}
