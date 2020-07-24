package com.example.androidserver.Modal_SQL;

import com.google.gson.annotations.SerializedName;

public class Card_Res {


    @SerializedName("iduser")
    public String iduser;
    @SerializedName("price")
    public double price;
    @SerializedName("content")
    public String content;
    @SerializedName("_idpreset")
    public String _idpreset;
    @SerializedName("namesp")
    public String namesp;
    @SerializedName("path")
    public String path;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String get_idpreset() {
        return _idpreset;
    }

    public void set_idpreset(String _idpreset) {
        this._idpreset = _idpreset;
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
}
