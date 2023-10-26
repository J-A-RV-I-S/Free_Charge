package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freecharge.R;

public class Feedback extends AppCompatActivity {

    RatingBar ratingBar;
    TextView textView;
    Button button;
    Toast toast;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar = findViewById(R.id.rating);
        button = findViewById(R.id.btn_rate);
        textView = findViewById(R.id.homeLink);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                View v = getLayoutInflater().inflate(R.layout.toast_custom,(ViewGroup)findViewById(R.id.custom_toast_call));
                toast.setView(v);
                toast.show();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}