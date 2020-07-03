package com.zyyoona7.easypopup.service;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.zyyoona7.easypopup.R;

public class ServiceDemoActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);



        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivePlayerService.start(v.getContext());
                WsManagerService.start(v.getContext());
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivePlayerService.stop(v.getContext());
                WsManagerService.stop(v.getContext());
            }
        });


    }
}
