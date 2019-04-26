package com.qiku.sharedpreferencetest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

public class BootService extends Service {
    private String TAG = "wanlihua bootservice";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        readStatusFile();

        return super.onStartCommand(intent, flags, startId);
    }
    public boolean readStatusFile() {

        Context mContext = this.createDeviceProtectedStorageContext();

        try {
            FileInputStream fis = mContext.openFileInput(MainActivity.status_file_name);
            int c = -1;
            c = fis.read();
            Log.d(TAG,"read value is c = " + c );
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            //next time restart may be recovery
            Log.e(TAG, "file open and read failed");
        }
        return true;
    }

}
