package com.example.androidserver.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Giaodich {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("iduser")
    @Expose
    private String iduser;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("_idpreset")
    @Expose
    private String idpreset;
    @SerializedName("namesp")
    @Expose
    private String namesp;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIdpreset() {
        return idpreset;
    }

    public void setIdpreset(String idpreset) {
        this.idpreset = idpreset;
    }

    public String getNamesp() {
        return namesp;
    }

    public void setNamesp(String namesp) {
        this.namesp = namesp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
