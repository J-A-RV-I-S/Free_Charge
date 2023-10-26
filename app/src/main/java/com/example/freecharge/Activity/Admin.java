package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freecharge.Api.RetrofitClient;
import com.example.freecharge.ModelResponse.DeleteResponse;
import com.example.freecharge.ModelResponse.PlanResponse;
import com.example.freecharge.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin extends AppCompatActivity {

    EditText txt_id, txt_price, txt_data, txt_validity;
    Button btn_insert, btn_view, btn_update, btn_delete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        txt_id = findViewById(R.id.txt_id);
        txt_price = findViewById(R.id.txt_price);
        txt_data = findViewById(R.id.txt_data);
        txt_validity = findViewById(R.id.txt_validity);
        btn_view = findViewById(R.id.btn_display);
        btn_insert = findViewById(R.id.btn_insert);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Admin.this,Planlist.class);
                startActivity(i);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txt_id.getText().toString().trim();
                String price = txt_price.getText().toString().trim();
                String data = txt_data.getText().toString().trim();
                String validity = txt_validity.getText().toString().trim();

                if (id.isEmpty()){
                    txt_id.requestFocus();
                    txt_id.setError("Enter ID");
                    return;
                }

                if (price.isEmpty()){
                    txt_price.requestFocus();
                    txt_price.setError("Enter Price");
                    return;
                }

                if (data.isEmpty()){
                    txt_data.requestFocus();
                    txt_data.setError("Enter Data");
                    return;
                }

                if (validity.isEmpty()){
                    txt_validity.requestFocus();
                    txt_validity.setError("Enter Validity");
                    return;
                }

                Call<PlanResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .updatePlan(id,validity,data,price);

                call.enqueue(new Callback<PlanResponse>() {
                    @Override
                    public void onResponse(Call<PlanResponse> call, Response<PlanResponse> response) {
                        PlanResponse updatePlanResponse = response.body();
                        if (response.isSuccessful()){
                            if (updatePlanResponse.getError().equals("200")){
                                Toast.makeText(Admin.this, updatePlanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(Admin.this, updatePlanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Admin.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlanResponse> call, Throwable t) {
                        Toast.makeText(Admin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = txt_id.getText().toString().trim();

                if (id.isEmpty()){
                    txt_id.requestFocus();
                    txt_id.setError("Enter respective ID of a record that needs to be deleted");
                    return;
                }

                Call<DeleteResponse> call = RetrofitClient.getInstance().getApi().deletePlan(id);

                call.enqueue(new Callback<DeleteResponse>() {
                    @Override
                    public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                        DeleteResponse deleteResponse = response.body();
                        if (response.isSuccessful()){
                            if (deleteResponse.getError().equals("200")){
                                Toast.makeText(Admin.this, deleteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(Admin.this, deleteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Admin.this, "Failed", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<DeleteResponse> call, Throwable t) {
                        Toast.makeText(Admin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String planId = txt_id.getText().toString();
                String planPrice = txt_price.getText().toString();
                String planData = txt_data.getText().toString();
                String planValidity = txt_validity.getText().toString();

                if (planId.isEmpty()){
                    txt_id.requestFocus();
                    txt_id.setError("Enter ID");
                    return;
                }

                if (planPrice.isEmpty()){
                    txt_price.requestFocus();
                    txt_price.setError("Enter Price");
                    return;
                }

                if (planData.isEmpty()){
                    txt_data.requestFocus();
                    txt_data.setError("Enter Data");
                    return;
                }

                if (planValidity.isEmpty()){
                    txt_validity.requestFocus();
                    txt_validity.setError("Enter Validity");
                    return;
                }

                Call<PlanResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .plan(planId,planValidity,planData,planPrice);

                call.enqueue(new Callback<PlanResponse>() {
                    @Override
                    public void onResponse(Call<PlanResponse> call, Response<PlanResponse> response) {

                        PlanResponse planResponse = response.body();
                        if (response.isSuccessful()){
                            Toast.makeText(Admin.this, planResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Admin.this, planResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlanResponse> call, Throwable t) {
                        Toast.makeText(Admin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}