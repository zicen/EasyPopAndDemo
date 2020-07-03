package com.zyyoona7.easypopup.glide;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.keyboard.ScreenUtils;

public class GlideDemoActivity extends AppCompatActivity {
    private ImageView img;
    private Button btnTestSelect;
    private Button btnTestUnselect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        btnTestSelect = (Button) findViewById(R.id.btn_test_select);
        btnTestUnselect = (Button) findViewById(R.id.btn_test_unselect);
        img = (ImageView) findViewById(R.id.img);

        btnTestSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewStatus(true);

            }
        });
        btnTestUnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewStatus(false);
            }
        });


    }
    private void setViewStatus(boolean isSelected){
        int size = (int) ScreenUtils.dp2px(this,20);
        LogUtils.d( "size: " + size);
        // Glide.with(img.getContext())
        //         .load(R.drawable.svg_live_catalog_hot)
        //         .apply(RequestOptions.noAnimation().bitmapTransform(new CropTransformation(size,size,
        //                 isSelected ? CropTransformation.CropType.BOTTOM : CropTransformation.CropType.TOP)))
        //         .into(img);
        Glide.with(img.getContext())
                .load(R.drawable.svg_live_catalog_hot)
                .apply(new RequestOptions().optionalTransform(new CatalogTransformation(isSelected)))
                .into(img);
    }

}
