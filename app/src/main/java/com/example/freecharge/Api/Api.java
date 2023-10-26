package com.example.freecharge.Api;

import com.example.freecharge.ModelResponse.DeleteResponse;
import com.example.freecharge.ModelResponse.FetchPlan;
import com.example.freecharge.ModelResponse.LoginResponse;
import com.example.freecharge.ModelResponse.PlanResponse;
import com.example.freecharge.ModelResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("plans.php")
    Call<PlanResponse> plan(
            @Field("id") String id,
            @Field("validity") String validity,
            @Field("data") String data,
            @Field("price") String price
    );

    @GET("fetchplans.php")
    Call<FetchPlan> fetchPlans();

    @FormUrlEncoded
    @POST("updateplans.php")
    Call<PlanResponse> updatePlan(
            @Field("id") String id,
            @Field("validity") String validity,
            @Field("data") String data,
            @Field("price") String price
    );

    @FormUrlEncoded
    @POST("deleteplans.php")
    Call<DeleteResponse> deletePlan(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("updateuser.php")
    Call<LoginResponse> updateUser(
            @Field("id") int id,
            @Field("username") String username,
            @Field("email") String email
    );
}