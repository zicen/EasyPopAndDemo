package com.zyyoona7.easypopup.keyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.dialogfragment.ChatDialogFragment;
import com.zyyoona7.easypopup.dialogfragment.KeyboardDialog;

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
                ChatDialogFragment chatDialogFragment2 = new ChatDialogFragment();
                chatDialogFragment2.showNow(getSupportFragmentManager(),"ChatDialogFragment2");

                break;
            case R.id.btn_show2:
                KeyboardDialog dialog = new KeyboardDialog();
                dialog.show(getSupportFragmentManager(),KeyboardDialog.class.getSimpleName());
                break;
            default:
                break;
        }
    }
}
