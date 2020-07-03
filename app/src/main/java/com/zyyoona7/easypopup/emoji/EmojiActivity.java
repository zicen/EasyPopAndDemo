package com.zyyoona7.easypopup.emoji;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.zyyoona7.easypopup.R;

public class EmojiActivity extends AppCompatActivity {
    private EditText mEdit;
    private Button mBtnAdd;
    private TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        mEdit = (EditText) findViewById(R.id.edit);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mText = (TextView) findViewById(R.id.text);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText.getPaint().setShader(new LinearGradient(
                        0, 0, mText.getWidth(), mText.getHeight(),
                        Color.parseColor("#eba3ff"), Color.parseColor("#75fffd"),
                        Shader.TileMode.CLAMP));
                mText.setText(mEdit.getText());
            }
        });
    }
}
