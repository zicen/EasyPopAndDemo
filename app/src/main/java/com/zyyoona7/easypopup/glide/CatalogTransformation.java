package com.zyyoona7.easypopup.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

import jp.wasabeef.glide.transformations.BitmapTransformation;

/**
 * Created by zicen on 2020-04-03.
 */
public class CatalogTransformation extends BitmapTransformation {

    private boolean isSelected;

    public CatalogTransformation(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String key() {
        return "CatalogTransformation(isSelected=" + isSelected + ")";
    }

    @Override
    protected Bitmap transform(@NonNull Context context, @NonNull BitmapPool pool,
                               @NonNull Bitmap toTransform, int outWidth, int outHeight) {

        int width = toTransform.getWidth();
        int height = toTransform.getHeight();


        Bitmap.Config config =
                toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888;
        Bitmap bitmap = pool.get(outWidth, outHeight, config);
        bitmap.setHasAlpha(true);
        // 显示下半部分区域
        RectF targetRect = new RectF(0, getTop(height), width, getBottom(height));
        // 显示上半部分区域
        // RectF targetRect = new RectF(0, 0, 60, height);
        // 构造一个带有给定bitmap的画布，画东西到bitmap里面去，这个bitmap必须是可变的。画布的初始密度和给定的bitmap的密度一致。
        Canvas canvas = new Canvas(bitmap);
        // 把 Bitmap 画到指定矩形控件
        canvas.drawBitmap(toTransform, null, targetRect, null);

        return bitmap;
    }

    private int getTop(int height) {
        return isSelected ? -(height / 2) : 0;
    }

    private int getBottom(int height) {
        return isSelected ? (height / 2) : height;
    }


}
