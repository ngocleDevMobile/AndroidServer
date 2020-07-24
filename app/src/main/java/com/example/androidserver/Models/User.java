package com.example.androidserver.Models;

import org.bson.types.ObjectId;

public class User {
    private String _id;
    private String email, username;

    public User(String _id, String email, String username) {
        this._id = _id;
        this.email = email;
        this.username = username;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
