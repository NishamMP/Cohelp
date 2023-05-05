package com.example.cohelp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.cohelp.cohelp_blutooth;
public class TestService extends Service {
    private MediaPlayer player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    BluetoothAdapter btAdapter;
 


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    //Somethng is here

                    int value = GetValue("DistanceKeep", 0);
                    if (value == 1) {
                        boolean status = CheckGPSandBluethoothStatus();
                        if (status) {

                            btAdapter = BluetoothAdapter.getDefaultAdapter();
                            btAdapter.startDiscovery();

                            IntentFilter filter = new IntentFilter();

                            filter.addAction(BluetoothDevice.ACTION_FOUND);
                            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

                            registerReceiver(mReceiver, filter);

                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            SetValue("DistanceKeep", 0);
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                }

            }
        });
        t.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(mReceiver);
            //Register or UnRegister your broadcast receiver here

        } catch(IllegalArgumentException e) {

            e.printStackTrace();
        }

    }


    public boolean CheckGPSandBluethoothStatus(){
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean bluetooth_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter.isEnabled()) {
                bluetooth_enabled = true;
            }
        } catch(Exception ex) {}

        if (gps_enabled && bluetooth_enabled){
            return true;
        }else{
            showNotification(TestService.this,"Please Turn On GPS and Bluetooth","Please Turn On GPS and Bluetooth",new Intent(TestService.this,cohelp_blutooth.class));
            return false;
        }

    }

    public void SetValue(String name,int val){
        SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
        editor.putInt(name,val);
        editor.apply();
    }
    public int GetValue(String name,int def_val){
        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        int myInt = prefs.getInt(name, def_val);
        return myInt;
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                Log.i("BT", device.getName() + "\n" + device.getAddress());
                if (rssi <= -10 && rssi >= -75) {
                    showNotification(TestService.this,"Please Maintain Distance","Please Maintain Distance",new Intent(TestService.this,cohelp_blutooth.class));
                    // Vibrate for 500 milliseconds

                }
            }

        }
    };


    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
        Vibrator v=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        if (title=="Please Maintain Distance")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(5);
        }

    }
}