package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.freecharge.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Random;

public class Confirmation extends AppCompatActivity {

    private static final String CHANNEL_ID = "Recharge Anywhere";
    private static final int NOTIFICATION_ID = 1;
    TextInputEditText et_otp;
    Button btn_otp;
    String otp = new DecimalFormat("0000").format(new Random().nextInt(9999));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        et_otp = findViewById(R.id.et_otp);
        btn_otp = findViewById(R.id.btn_otp);


        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = et_otp.getText().toString();

                if (number.isEmpty()){
                    et_otp.requestFocus();
                    et_otp.setError("Enter Mobile Number");
                }
                else {
                    createNotification();
                    addNotification();
                    Intent i2 = new Intent(Confirmation.this, ConfirmOTP.class);
                    i2.putExtra("NUMBER", number);
                    i2.putExtra("otp",otp);
                    startActivity(i2);
                }
            }
        });
    }

    public void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "One step to continue";
//            String otp = new DecimalFormat("0000").format(new Random().nextInt(9999));

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(otp);

            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void addNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.notification_logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification_logo));
//        String otp = new DecimalFormat("0000").format(new Random().nextInt(9999));
        builder.setContentTitle("One step to continue");
        builder.setContentText("Your one time password is "+otp);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }
}