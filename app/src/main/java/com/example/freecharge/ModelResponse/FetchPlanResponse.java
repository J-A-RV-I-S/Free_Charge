package com.example.freecharge.ModelResponse;


public class FetchPlanResponse {

    String id, validity, data, price;

    public FetchPlanResponse(String id, String validity, String data, String price) {
        this.id = id;
        this.validity = validity;
        this.data = data;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
