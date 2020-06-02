package com.example.insaniyatambulancedriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    boolean isGPS;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstants.GPS_REQUEST) {
                isGPS = true; // flag maintain before get location
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //open mapbox
        openMap();

        //hide title bar
        getSupportActionBar().hide();

        //pop up of enable GPS at the launch time of the application
        GetGPS();


    }


    public void openMap(){
        Intent i = new Intent(this, LocationComponentActivity.class);
        startActivity(i);
    }

    public void GetGPS(){

        Log.d("GETGPS1","1");

        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
                Log.d("GETGPS2","2");

            }
        });
        Log.d("GETGPS3","3");

    }


    public class AppConstants
    {
        public static final int LOCATION_REQUEST = 1000;
        public static final int GPS_REQUEST = 1001;
    }


}
