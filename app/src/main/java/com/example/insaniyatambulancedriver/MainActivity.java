package com.example.insaniyatambulancedriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Button Login = findViewById(R.id.login);
        Button SignUp = findViewById(R.id.signup);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLoginPage();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUpPage();
            }
        });

    }

    void OpenSignUpPage(){
        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    void OpenLoginPage(){
        Intent i = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(i);
    }
}
