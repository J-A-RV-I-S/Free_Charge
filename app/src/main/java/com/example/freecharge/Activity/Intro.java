package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.freecharge.R;

public class Intro extends AppCompatActivity {

    ImageView bg;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        bg = findViewById(R.id.bgimg);
        lottieAnimationView = findViewById(R.id.lottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1 = new Intent(Intro.this, About.class);
                startActivity(i1);
                finish();
            }
        },5000);

        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
    }
}