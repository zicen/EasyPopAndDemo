package com.zyyoona7.easypopup.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ServiceUtils;

/**
 * Created by zicen on 2020-02-03.
 * Websocket 消息服务
 */
public class WsManagerService extends Service {

    public static final String TAG = "WsManagerService";

    public static void start(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), WsManagerService.class);
        ContextCompat.startForegroundService(context.getApplicationContext(), intent);
    }

    /**
     * 停止服务
     */
    public static void stop(Context context) {
        if (isRunning()) {
            Intent intent = new Intent(context.getApplicationContext(), WsManagerService.class);
            context.stopService(intent);
        }
    }

    /**
     * 服务是否正在运行
     *
     * @return
     */
    public static boolean isRunning() {
        return ServiceUtils.isServiceRunning(WsManagerService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreate");
        showNotification();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        showNotification();
    }

    private void showNotification() {
        startForeground(hashCode(), new Notification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtils.d(TAG, "onDestroy");
        super.onDestroy();
        stopForeground(true); // 停止前台服务--参数：表示是否移除之前的通知
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
