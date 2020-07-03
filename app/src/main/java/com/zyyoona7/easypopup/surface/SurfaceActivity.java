package com.zyyoona7.easypopup.surface;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
