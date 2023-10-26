package com.example.freecharge.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.freecharge.Adapter.PlanAdapter;
import com.example.freecharge.Api.RetrofitClient;
import com.example.freecharge.ModelResponse.FetchPlan;
import com.example.freecharge.ModelResponse.FetchPlanResponse;
import com.example.freecharge.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Planlist extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FetchPlanResponse> fetchPlanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planlist);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // API work

        Call<FetchPlan> call = RetrofitClient.getInstance().getApi().fetchPlans();

        call.enqueue(new Callback<FetchPlan>() {
            @Override
            public void onResponse(Call<FetchPlan> call, Response<FetchPlan> response) {

                if (response.isSuccessful()){
                    fetchPlanList = response.body().getFetchPlanResponseList();
                    recyclerView.setAdapter(new PlanAdapter(getApplication(),fetchPlanList));
                }
                else {
                    Toast.makeText(Planlist.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchPlan> call, Throwable t) {
                Toast.makeText(Planlist.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
