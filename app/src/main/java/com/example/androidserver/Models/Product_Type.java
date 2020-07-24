package com.example.androidserver.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Product_Type {

@SerializedName("_id")
@Expose
private String id;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("namesp")
    @Expose
    private String namesp;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("theloai")
    @Expose
    private String theloai;
    @SerializedName("_idAdmin")
    @Expose
    private String idAdmin;
    @SerializedName("date_news")
    @Expose
    private String dateNews;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Product_Type(String id, String path, String namesp, Integer price, String content, String theloai, String idAdmin, String dateNews, Integer v) {
        this.id = id;
        this.path = path;
        this.namesp = namesp;
        this.price = price;
        this.content = content;
        this.theloai = theloai;
        this.idAdmin = idAdmin;
        this.dateNews = dateNews;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return "http://192.168.1.7:5000"+path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNamesp() {
        return namesp;
    }

    public void setNamesp(String namesp) {
        this.namesp = namesp;
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

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getDateNews() {
        return dateNews;
    }

    public void setDateNews(String dateNews) {
        this.dateNews = dateNews;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
