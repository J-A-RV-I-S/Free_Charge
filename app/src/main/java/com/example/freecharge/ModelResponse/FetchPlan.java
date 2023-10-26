package com.example.freecharge.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchPlan {

    @SerializedName("plans")
    List<FetchPlanResponse> fetchPlanResponseList;
    String error;

    public FetchPlan(List<FetchPlanResponse> fetchPlanResponseList, String error) {
        this.fetchPlanResponseList = fetchPlanResponseList;
        this.error = error;
    }

    public List<FetchPlanResponse> getFetchPlanResponseList() {
        return fetchPlanResponseList;
    }

    public void setFetchPlanResponseList(List<FetchPlanResponse> fetchPlanResponseList) {
        this.fetchPlanResponseList = fetchPlanResponseList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
