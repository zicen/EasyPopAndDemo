package com.zyyoona7.easypopup.surface;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.ToastUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zyyoona7.easypopup.R;

public class SurfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        final LinearLayout container = findViewById(R.id.container_layout);

        // MySurfaceView surfaceView = findViewById(R.id.surface);
        // surfaceView.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         ToastUtils.showShort("daddad");
        //     }
        // });
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySurfaceView surfaceView = new MySurfaceView(SurfaceActivity.this);
                container.addView(surfaceView);
            }
        });
    }
}
