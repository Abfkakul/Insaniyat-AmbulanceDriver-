package com.example.insaniyatambulancedriver;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);

        /*Intent j = new Intent(this, PopUpWindow.class);
        startActivity(j);
*/
    }

}
