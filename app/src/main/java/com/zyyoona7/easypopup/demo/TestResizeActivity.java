package com.zyyoona7.easypopup.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.zyyoona7.easypopup.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TestResizeActivity extends AppCompatActivity {
    public static final String gifImageUrl = "http://static.missevan.com/coversmini/202001/10/11357c1446e48c00fd0e3f075b90b8a3164144.gif?x-oss-process=image/format,webp";
    public static final String gifResizeImageUrl = "http://static.missevan.com/coversmini/202001/10/11357c1446e48c00fd0e3f075b90b8a3164144.gif?x-oss-process=image/format,webp";
    public static final String gifImageUrl2 = "http://static.missevan.com/coversmini/201911/29/4757432dfa6abdd4083bff6ce2499b50165625.gif?x-oss-process=image/format,webp";
    public static final String gifResizeImageUrl2 = "http://static.missevan.com/coversmini/201911/29/4757432dfa6abdd4083bff6ce2499b50165625.gif?x-oss-process=image/format,webp";

    public static final String testImage = "http:\\/\\/static.missevan.com\\/mimages\\/201803\\/23\\/7e92e95e05655902bd2c63505df3c663151300.png";

    private ImageView imgNoFitcenter;
    private ImageView imgScale;
    private ImageView imgScaleNoFitcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_resize);
        ImageView img = findViewById(R.id.img);


        ImageView imgTest = findViewById(R.id.img_test);
        Glide.with(this)
                .load(testImage)
                .into(imgTest);

        imgNoFitcenter = (ImageView) findViewById(R.id.img_no_fitcenter);
        imgScale = (ImageView) findViewById(R.id.img_scale);
        imgScaleNoFitcenter = (ImageView) findViewById(R.id.img_scale_no_fitcenter);
        Button btnClearCache = findViewById(R.id.btn_clear_cache);
        btnClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.get(TestResizeActivity.this).clearMemory();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(TestResizeActivity.this).clearDiskCache();
                    }
                }).start();
            }
        });
        // logResizeUrl();


        // Glide 加载 gif 转 webp 的图如果加了 fitCenter 会加载不出来，不知道为啥
        RequestOptions requestOptions = new RequestOptions().fitCenter(); // 这种写法不支持，后面三种支持

        RequestOptions requestOptions1 = new RequestOptions().optionalFitCenter();
        RequestOptions requestOptions2 = new RequestOptions().optionalTransform(new FitCenter());
        Transformation<Bitmap> fitCenter = new FitCenter();
        RequestOptions requestOptions3 = new RequestOptions().optionalTransform(WebpDrawable.class,new WebpDrawableTransformation(fitCenter));

        RequestOptions requestOptions11 = new RequestOptions().centerCrop();
        // new WebpDrawableTransformation()
        Glide.with(this)
                .load(gifImageUrl)
                .apply(requestOptions11)
                .into(img);

        Glide.with(this)
                .load(gifResizeImageUrl2)
                .into(imgNoFitcenter);

        Glide.with(this)
                .load(gifResizeImageUrl)
                .apply(new RequestOptions().fitCenter())
                .into(imgScale);

        Glide.with(this)
                .load(gifImageUrl2)
                .into(imgScaleNoFitcenter);
    }

    /**
     * 获取 resize 原图大小，对比 resize 之后的收益
     */
    private void logResizeUrl() {
        // final List<String> strings = ResourceUtils.readAssets2List("xxx.txt");
        final List<String> strings = ResourceUtils.readAssets2List("url.txt");
        for (int i = 0, size = strings.size(); i < size; i++) {
            // LogUtils.d("path: "+ strings.get(i));
            final String url = strings.get(i);
            String[] split = url.split("\\?");
            final String path = split[0];
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int[] imgWH = getImgWH(path);
                        String log = "resize url: " + url + "\n"
                                +"原图 width:"+imgWH[0]+"\n"
                                +"原图 height:"+imgWH[1];
                        LogUtils.d(log);
                        // FileIOUtils.writeFileFromString("/Users/zicen/Documents/result.txt", log, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 获取服务器上的图片尺寸
     */
    public static int[] getImgWH(String urls) throws Exception {

        URL url = new URL(urls);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.connect();
        InputStream is = conn.getInputStream();
        Bitmap image = BitmapFactory.decodeStream(is);

        int srcWidth = image.getWidth();      // 源图宽度
        int srcHeight = image.getHeight();    // 源图高度
        int[] imgSize = new int[2];
        imgSize[0] = srcWidth;
        imgSize[1] = srcHeight;

        //释放资源
        image.recycle();
        is.close();
        conn.disconnect();

        return imgSize;
    }
}
