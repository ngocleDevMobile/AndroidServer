package com.example.androidserver.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Theloai {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("sanphams")
    @Expose
    private List<String> sanphams = null;
    @SerializedName("namely")
    @Expose
    private String namely;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("_nameAdmin")
    @Expose
    private String nameAdmin;
    @SerializedName("_idAdmin")
    @Expose
    private String idAdmin;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getSanphams() {
        return sanphams;
    }

    public void setSanphams(List<String> sanphams) {
        this.sanphams = sanphams;
    }

    public String getNamely() {
        return namely;
    }

    public void setNamely(String namely) {
        this.namely = namely;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
