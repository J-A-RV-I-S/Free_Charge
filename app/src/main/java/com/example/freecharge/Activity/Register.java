package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freecharge.Api.RetrofitClient;
import com.example.freecharge.ModelResponse.RegisterResponse;
import com.example.freecharge.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText etname, etemail, etpassword;
    Button btnlogin, btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etname = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        btnregister = findViewById(R.id.btn_register);
        btnlogin = findViewById(R.id.btn_login);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etname.getText().toString();
                String userEmail = etemail.getText().toString();
                String userPassword = etpassword.getText().toString();

                if (userName.isEmpty()){
                    etname.requestFocus();
                    etname.setError("Enter Name");
                    return;
                }

                if (userEmail.isEmpty()){
                    etemail.requestFocus();
                    etemail.setError("Enter Email");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    etemail.requestFocus();
                    etemail.setError("Enter correct Email");
                    return;
                }

                if (userPassword.isEmpty()){
                    etpassword.requestFocus();
                    etpassword.setError("Enter Password");
                    return;
                }

                Call<RegisterResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .register(userName,userEmail,userPassword);

                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        RegisterResponse registerResponse = response.body();

                        if (response.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                            Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Register.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}