package com.example.androidserver.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Giaodich_Res {
    @SerializedName("data")
    @Expose
    private List<Giaodich> data = null;

    public List<Giaodich> getData() {
        return data;
    }

    public void setData(List<Giaodich> data) {
        this.data = data;
    }
}
