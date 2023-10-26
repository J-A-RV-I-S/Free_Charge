package com.example.freecharge.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freecharge.Activity.Verification;
import com.example.freecharge.ModelResponse.FetchPlanResponse;
import com.example.freecharge.R;

import java.util.List;
import java.util.Objects;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    List<FetchPlanResponse> planResponseList;
    Context context;
    String admin, password;

    public PlanAdapter(Context context, List<FetchPlanResponse> planResponseList) {
        this.context = context;
        this.planResponseList=planResponseList;
    }

    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.planentry, parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {
        holder.idShow.setText(planResponseList.get(position).getId());
        holder.validityShow.setText(planResponseList.get(position).getValidity());
        holder.dataShow.setText(planResponseList.get(position).getData());
        holder.priceShow.setText("₹" +planResponseList.get(position).getPrice());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(admin,"admin") && Objects.equals(password,"78019755")){
                    Toast.makeText(context, "Access Denied", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(context, Verification.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return planResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView idShow, validityShow, dataShow, priceShow;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idShow = itemView.findViewById(R.id.id_entry);
            validityShow = itemView.findViewById(R.id.validity_entry);
            dataShow = itemView.findViewById(R.id.data_entry);
            priceShow = itemView.findViewById(R.id.price_entry);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
