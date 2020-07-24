package com.example.androidserver.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Product_Type_Response {

@SerializedName("data")
@Expose
private List<Product_Type> data = null;

    public List<Product_Type> getData() {
        return data;
    }

    public void setData(List<Product_Type> data) {
        this.data = data;
    }
}
