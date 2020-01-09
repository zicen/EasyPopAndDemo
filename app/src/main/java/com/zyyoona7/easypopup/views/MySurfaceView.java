package com.zyyoona7.easypopup.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by zicen on 2019-12-30.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private ArrayList<View> mViews = new ArrayList<>();
    private DrawThread mDrawThread;


    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mDrawThread = new DrawThread();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mDrawThread == null) {
            mDrawThread = new DrawThread();
        }
        mDrawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mDrawThread != null) {
            mDrawThread.isRun = false;
            mDrawThread.interrupt();
            mDrawThread = null;
        }
    }

    class DrawThread extends Thread {
        boolean isRun = true;

        @Override
        public void run() {
            super.run();
            /**绘制的线程 死循环 不断的跑动*/
            while (isRun) {
                Canvas canvas = null;
                try {
                    synchronized (mSurfaceHolder) {
                        canvas = mSurfaceHolder.lockCanvas();
                        /**清除画面*/
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        boolean isEnd = true;

                        /**对所有心进行遍历绘制*/
                        for (int i = 0; i < mViews.size(); i++) {
                            // isEnd = mViews.get(i).isEnd;
                            // mViews.get(i).draw(canvas, p);
                        }
                        /**这里做一个性能优化的动作，由于线程是死循环的 在没有心需要的绘制的时候会结束线程*/
                        if (isEnd) {
                            isRun = false;
                            mDrawThread = null;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
                try {
                    /**用于控制绘制帧率*/
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop() {
        if (mDrawThread != null) {
            for (int i = 0; i < mViews.size(); i++) {
                // mViews.get(i).stop();
            }
            mDrawThread.isRun = false;
            mDrawThread = null;
        }

    }

    public void start() {
        if (mDrawThread == null) {
            //            for (int i = 0; i < zanBeen.size(); i++) {
            //                zanBeen.get(i).resume();
            //            }
            mDrawThread = new DrawThread();
            mDrawThread.start();
        }
    }

}
