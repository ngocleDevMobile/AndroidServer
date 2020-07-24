package com.example.androidserver.Modal_SQL;

import java.util.Date;

public class Card {
    public String iduser;
    public double price;
    public String content;
    public String _idpreset;
    public String namesp;
    public String path;


    public Card(String iduser, double price, String content, String _idpreset, String namesp, String path) {
        this.iduser = iduser;
        this.price = price;
        this.content = content;
        this._idpreset = _idpreset;
        this.namesp = namesp;
        this.path = path;
    }

    public Card() {
    }

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
