package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freecharge.Api.RetrofitClient;
import com.example.freecharge.ModelResponse.LoginResponse;
import com.example.freecharge.R;
import com.example.freecharge.SharedPreference.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText etemail, etpassword;
    Button btnlogin, btnregister;
    SharedPrefManager sharedPrefManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btn_login);
        btnregister = findViewById(R.id.btn_register);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = etemail.getText().toString();
                String userPassword = etpassword.getText().toString();

                // FOR ADMIN LOGIN //

                if (etemail.getText().toString().equals("admin") && etpassword.getText().toString().equals("78019755")){
                    Intent i = new Intent(Login.this,Admin.class);
                    i.putExtra("EMAIL", userEmail);
                    i.putExtra("PASSWORD", userPassword);
                    startActivity(i);
                    finish();
                    return;
                }

                // FOR ADMIN LOGIN //

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

                Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(userEmail,userPassword);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();

                        if (response.isSuccessful()){
                            if (loginResponse.getError().equals("200")){
                                sharedPrefManager.saveUser(loginResponse.getUser());
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                                Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (loginResponse.getError().equals("400")){
                                    Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPrefManager.isLoggedIn()){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }
}