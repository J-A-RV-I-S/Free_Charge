package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.freecharge.R;

public class Verification extends AppCompatActivity {

    LottieAnimationView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        progress = findViewById(R.id.progress_lottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1 = new Intent(Verification.this,Confirmation.class);
                startActivity(i1);
                finish();
            }
        },5000);

        progress.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

    }
}