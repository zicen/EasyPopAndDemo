package com.zyyoona7.easypopup.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.blankj.utilcode.util.KeyboardUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.dialogfragment.KeyboardDialog;

public class KeybordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keybord);

        mBtnShow = findViewById(R.id.btn_show);
        mBtnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                // KeyboardDialog dialog = new KeyboardDialog();
                // dialog.show(getSupportFragmentManager(),KeyboardDialog.class.getSimpleName());
                EmoticonsKeyBoardPopWindow popWindow = new EmoticonsKeyBoardPopWindow(this);
                popWindow.showPopupWindow();
                break;
            default:
                break;
        }
    }
}
