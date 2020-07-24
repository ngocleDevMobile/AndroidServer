package com.example.androidserver.Models;

public class LoginResponse {

    private boolean err;
    private String message;
    private User user;

    public LoginResponse(boolean err, String message, User user) {
        this.err = err;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return err;
    }

    public void setError(boolean error) {
        this.err = err;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
