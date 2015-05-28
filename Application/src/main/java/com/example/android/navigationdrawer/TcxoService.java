package com.example.android.navigationdrawer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class TcxoService extends Service implements LocationListener {
    private final static String TAG = "TcxoService";
    private Handler mHandler = new Handler();
    private int intCounter = 0;
    private LocationManager mLocationManagerService = null;
    private PowerManager.WakeLock wakeLock;
    
    private Runnable mTasks = new Runnable() {
        public void run() {
            intCounter++;
            Log.d(TAG, "Counter:" + Integer.toString(intCounter));
            mHandler.postDelayed(mTasks, 60000);
            if(intCounter % 2 != 0) {
                Log.d(TAG, "Start GPS");
                acquireWakeLock();
                mLocationManagerService.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, TcxoService.this);
            } else {
                Log.d(TAG, "Stop GPS");
                releaseWakeLock();
                mLocationManagerService.removeUpdates(TcxoService.this);
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        mHandler.postDelayed(mTasks, 5000);
        mLocationManagerService = (LocationManager)this.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void acquireWakeLock() {
        if (wakeLock == null) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            //wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, this.getClass().getCanonicalName());
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                    PowerManager.ACQUIRE_CAUSES_WAKEUP |
                    PowerManager.ON_AFTER_RELEASE, this.getClass().getCanonicalName());
            wakeLock.acquire();
        }
    }

    private void releaseWakeLock() {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
            wakeLock = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(mTasks);
        mLocationManagerService.removeUpdates(TcxoService.this);
        releaseWakeLock();
        super.onDestroy();
    }

    public void onLocationChanged(Location location) {
//        Log.d(TAG, "Longitude = " + location.getLongitude() + "\n");
//        Log.d(TAG, "Latitude = " + location.getLatitude() + "\n");
//        Log.d(TAG, "Accuracy = " + location.getAccuracy() + "\n");
//        Log.d(TAG, "Altitude = " + location.getAltitude() + "\n");
//        Log.d(TAG, "Time = " + location.getTime() + "\n\n");
//        Log.d(TAG, "Speed = " + location.getSpeed() + "\n");
//        Log.d(TAG, "Bearing = " + location.getBearing() + "\n");
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}