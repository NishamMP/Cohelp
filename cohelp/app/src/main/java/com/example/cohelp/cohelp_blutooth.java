package com.example.cohelp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class cohelp_blutooth extends AppCompatActivity {

    Switch myswitch;
    BluetoothAdapter bAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("SAFE DISTANCE");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#018786")));
        setContentView(R.layout.activity_cohelp_blutooth);
        myswitch = (Switch) findViewById(R.id.key_switch);
        ListView listView;
        
        int value = GetValue("DistanceKeep", 0);
        if (value == 1) {
            myswitch.setChecked(true);
        } else {
            myswitch.setChecked(false);
        }

        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (myswitch.isChecked() == true) {
                    myswitch.setText("ON");
                    SetValue("DistanceKeep", 1);
                  //  Intent discoverableIntent =
                          //  new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                 //   discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
                  //  startActivityForResult(discoverableIntent, 0);
                    startService(new Intent(cohelp_blutooth.this, TestService.class));
                } else {
                    myswitch.setText("OFF");
                    SetValue("DistanceKeep", 0);
                    stopService(new Intent(cohelp_blutooth.this, TestService.class));
                }
            }
        });
    }

    //code for set intiger value
    public void SetValue(String name, int val) {
        SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
        editor.putInt(name, val);
        editor.apply();
    }

    public int GetValue(String name, int def_val) {
        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        int myInt = prefs.getInt(name, def_val);
        return myInt;
    }


}