package com.example.insaniyatambulancedriver;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class PopUpWindow extends Activity {


    MediaPlayer alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window);

        //play alert music
        alert =  MediaPlayer.create(PopUpWindow.this,R.raw.sms);
        alert.start();

        display();

        Button RideAccept = findViewById(R.id.accept);
        //Button RideReject = findViewById(R.id.reject);

        RideAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show details of customer



                //music stops
                onPause();

                //remove the pop-up screen
                finish();
            }
        });


    }


    public void display(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * .8), (int) (height * .4));

    }



    @Override
    public void onPause () {
        if (alert != null){
            alert.pause();
            alert.stop();
        }
        super.onPause();
    }


}

