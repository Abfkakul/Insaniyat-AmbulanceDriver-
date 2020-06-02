package com.example.insaniyatambulancedriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    boolean isGPS;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == MainActivity.AppConstants.GPS_REQUEST) {
                isGPS = true; // flag maintain before get location
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Button Login = findViewById(R.id.login);
        Button SignUp = findViewById(R.id.signup);

        //pop up of enable GPS at the launch time of the application
        GetGPS();

        //seesion logic
        mFirebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser!=null && mFirebaseUser.isEmailVerified())
        {
            Toast.makeText(MainActivity.this,""+mFirebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
            DocumentReference drefUser = db.collection("Ambulance Drivers").document(""+mFirebaseUser.getEmail());
            drefUser.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
            {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot)
                {
                    if(documentSnapshot.exists())
                    {
                        Toast.makeText(MainActivity.this,"User Logged in", Toast.LENGTH_SHORT).show();
                        Intent intToHome= new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intToHome);
                    }
                }
            }).addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    Toast.makeText(MainActivity.this,"Error Occurred!", Toast.LENGTH_SHORT).show();
                    Log.d("Session erroer: ",""+e);
                }
            });
        }
        else
        {
            SignUp.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    OpenSignUpPage();
                }
            });

            Login.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent i = new Intent(MainActivity.this,SignInActivity.class);
                    switchActivity(i);
                }
            });

        }

       /* Login.setOnClickListener(new View.OnClickListener() {
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
*/
    }

    void OpenSignUpPage(){
        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    void OpenLoginPage(){
        Intent i = new Intent(MainActivity.this, SignInActivity.class);
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

    @Override
    public void onBackPressed()
    {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

    private void switchActivity(Intent i)
    {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(i);
    }
}
