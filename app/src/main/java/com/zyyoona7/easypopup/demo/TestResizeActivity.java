package com.zyyoona7.easypopup.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zyyoona7.easypopup.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TestResizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_resize);
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


        // Fresco.
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
