package com.qiku.sharedpreferencetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverTest extends BroadcastReceiver {
    private String TAG = "wanlihua ReceiverTest";
    @Override
    public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive : " + intent.getAction());
        if (intent.getAction().equals(Intent.ACTION_LOCKED_BOOT_COMPLETED) || intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) ) {
            Log.e(TAG, "Intent.ACTION_BOOT_COMPLETED");
            Log.d(TAG, "startService");
            //context.startService(new Intent(context, BootService.class));
            context.startForegroundService(new Intent(context, BootService.class));
        }
    }
}