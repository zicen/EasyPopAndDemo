package com.zyyoona7.easypopup.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.zyyoona7.easypopup.R;

/**
 * Created by zicen on 2020-01-30.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    // 线程运行的标志，用于控制线程
    private volatile boolean flag;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint paint;
    private Bitmap bg;
    private Bitmap header;
    private Bitmap footer;
    private String text = "哈哈哈哈哈哈哈哈哈哈你好啊，我来字中国！哈哈哈哈哈哈哈哈哈哈你好啊，我来字中国！";
    private final int mTotalWidth;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Thread thread = new Thread(this);
        flag = true;
        thread.start();
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
        mHolder.removeCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void run() {
        while (flag) {
            synchronized (mHolder) {
                drawPic();
            }
        }
    }

    private int mWidth;

    private int mViewWidth;

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(SizeUtils.dp2px(15));
        paint.setStyle(Paint.Style.FILL);
        Bitmap tempBg = ImageUtils.getBitmap(R.drawable.bg_global_notice_level_7);
        int newWidth = (int) paint.measureText(text) - SizeUtils.dp2px(10);
        LogUtils.d("newWidth:" + newWidth);
        bg = ImageUtils.scale(tempBg, newWidth, (int) SizeUtils.dp2px(40), true);
        LogUtils.d("bg width:" + bg.getWidth());
        Bitmap tempHeader = ImageUtils.getBitmap(R.drawable.header_global_notice_level_7);
        header = ImageUtils.scale(tempHeader, (int) SizeUtils.dp2px(80), (int) SizeUtils.dp2px(40), true);

        Bitmap tempFooter = ImageUtils.getBitmap(R.drawable.footer_global_notice_level_7);
        footer = ImageUtils.scale(tempFooter, (int) SizeUtils.dp2px(30), (int) SizeUtils.dp2px(40), true);
        mWidth = ScreenUtils.getScreenWidth();
        mViewWidth = bg.getWidth() + header.getWidth() + footer.getWidth();
        mTotalWidth = ScreenUtils.getScreenWidth() + mViewWidth;
    }

    protected void drawPic() {
        mCanvas = mHolder.lockCanvas();
        if (mCanvas != null) {
            mWidth -= 6;
            if (mWidth <= (-mViewWidth)) {
                mWidth = ScreenUtils.getScreenWidth();
            }
            //清屏
            paint.reset();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            mCanvas.drawPaint(paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            mCanvas.drawBitmap(header, mWidth, 50, paint);
            mCanvas.drawBitmap(bg, mWidth + header.getWidth(), 50, paint);
            mCanvas.drawBitmap(footer, mWidth + header.getWidth() + bg.getWidth(), 50, paint);


            // 设置文字 的 paint
            paint.setColor(Color.WHITE);
            paint.setTextSize(SizeUtils.sp2px(15));
            paint.setStyle(Paint.Style.FILL);

            mCanvas.drawText(text, mWidth + header.getWidth(), 50 + SizeUtils.dp2px(25), paint);
            mHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}
