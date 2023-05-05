package com.example.cohelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class homewin extends AppCompatActivity implements View.OnClickListener {

    private CardView SafeDistance,CovUpd,LetVac,RrTeam,Declare;


    final BluetoothAdapter bAdapter=BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_homewin);

        SafeDistance = (CardView) findViewById(R.id.so_dis);
        CovUpd = (CardView) findViewById(R.id.co_up);
        LetVac = (CardView) findViewById(R.id.lt_vac);
        RrTeam= (CardView) findViewById(R.id.rap_res);
        Declare=(CardView) findViewById(R.id.exit);


        if (ContextCompat.checkSelfPermission(homewin.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(homewin.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(homewin.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(homewin.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }





        SafeDistance.setOnClickListener(this);
        CovUpd.setOnClickListener(this);
        LetVac.setOnClickListener(this);
        RrTeam.setOnClickListener(this);
        Declare.setOnClickListener(this);





    }


    public boolean statusCheck() {

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        } else {
            return true;
        }
    }
        private void buildAlertMessageNoGps() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }

    @Override
    public void onClick(View v) {
        Intent i;
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        boolean test=true;

                switch (v.getId()){
            case R.id.so_dis    :   if (bAdapter.isEnabled() &&  manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                                          i = new Intent(this,cohelp_blutooth.class);
                                          startActivity(i);

                                      }
                                      else {
                                       if (!bAdapter.isEnabled()){
                                              Toast.makeText(getApplicationContext(),"Please Turn On Bluetooth",Toast.LENGTH_SHORT).show();
                                           Intent discoverableIntent =
                                                   new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                                           discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
                                           startActivityForResult(discoverableIntent, 0);

                                        }
                                        else if(statusCheck()==false){
                                            Toast.makeText(getApplicationContext(),"Location is not enabled",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),"Turn On Bluetooth and Location",Toast.LENGTH_SHORT).show();
                                        }
                                      }
                                        break;

            case R.id.co_up          :   i = new Intent(this,covupd.class);
                                        startActivity(i);
                                        break;
            case R.id.lt_vac         :  i = new Intent(this,Let_vacs.class);
                                        startActivity(i);
                                        break;
            case R.id.rap_res        :  i = new Intent(this,contacts.class);
                                        startActivity(i);
                                        break;
            case R.id.exit           :  i=new Intent(this,Declare_Int.class);
                                        startActivity(i);
                                        break;
                                      }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(homewin.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }


    }


}
