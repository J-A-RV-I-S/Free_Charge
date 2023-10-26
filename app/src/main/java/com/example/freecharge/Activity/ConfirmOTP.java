package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freecharge.R;
import com.google.android.material.textfield.TextInputEditText;

public class ConfirmOTP extends AppCompatActivity {

    TextView user_number;
    TextInputEditText et_otpConfirm;
    Button btn_otpConfirm;
    ProgressBar progressBar;
    String getOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_otp);

        user_number = findViewById(R.id.user_number);
        et_otpConfirm = findViewById(R.id.et_otpConfirm);
        btn_otpConfirm = findViewById(R.id.btn_otpConfirm);

        progressBar = findViewById(R.id.confirmProgressBar);

        btn_otpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_otpConfirm.getText().toString().equals(getOtp)){
                    Intent last = new Intent(ConfirmOTP.this, Success.class);
                    startActivity(last);
                }
                else{
                    Toast.makeText(ConfirmOTP.this, "Enter valid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent i2 = getIntent();
        String number = i2.getStringExtra("NUMBER");
        getOtp = getIntent().getExtras().getString("otp");

        user_number.setText(number);
    }
}
