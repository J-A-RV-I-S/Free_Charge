package com.example.freecharge.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freecharge.Activity.Login;
import com.example.freecharge.Api.RetrofitClient;
import com.example.freecharge.ModelResponse.LoginResponse;
import com.example.freecharge.R;
import com.example.freecharge.SharedPreference.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    EditText et_nameProfile, et_emailProfile;
    Button btn_updateProfile, btn_logOut;
    SharedPrefManager sharedPrefManager;
    int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        et_nameProfile = view.findViewById(R.id.et_nameProfile);
        et_emailProfile = view.findViewById(R.id.et_emailProfile);
        btn_updateProfile = view.findViewById(R.id.btn_updateProfile);
        btn_logOut = view.findViewById(R.id.btn_logOut);

        sharedPrefManager = new SharedPrefManager(getActivity());
        userId = sharedPrefManager.getUser().getId();

        et_nameProfile.setText(sharedPrefManager.getUser().getUsername());
        et_emailProfile.setText(sharedPrefManager.getUser().getEmail());

        btn_updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_nameProfile.getText().toString().trim();
                String email = et_emailProfile.getText().toString().trim();

                Call<LoginResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .updateUser(userId,username,email);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        LoginResponse updateResponse = response.body();
                        if (response.isSuccessful()){
                            if (updateResponse.getError().equals("200")){
                                sharedPrefManager.saveUser(updateResponse.getUser());
                                Toast.makeText(getActivity(), updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getActivity(), updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.LogOut();
                Intent i = new Intent(getActivity(), Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

}