/*
 * Copyright (c) 2017. BiliBili Inc.
 */

package com.zyyoona7.easypopup.gift;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Jungly
 * @mail jungly.ik@gmail.com
 * @date 16/1/13
 */
public class WrapContentHeightViewPager extends ViewPager {

    public WrapContentHeightViewPager(Context context) {
        super(context);
    }

    public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 0;
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View view = getChildAt(index);
            if (view != null) {
                view.measure(widthMeasureSpec, heightMeasureSpec);
                height = Math.max(height, measureHeight(heightMeasureSpec, view));
            }
        }
        setMeasuredDimension(getMeasuredWidth(), height);
    }

    private int measureHeight(int measureSpec, View view) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            // set the height from the base view if available
            if (view != null) {
                result = view.getMeasuredHeight();
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
