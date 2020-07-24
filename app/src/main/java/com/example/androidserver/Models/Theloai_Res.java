package com.example.androidserver.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Theloai_Res {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("data")
    @Expose
    private List<Theloai> data = null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Theloai> getData() {
        return data;
    }

    public void setData(List<Theloai> data) {
        this.data = data;
    }


}
