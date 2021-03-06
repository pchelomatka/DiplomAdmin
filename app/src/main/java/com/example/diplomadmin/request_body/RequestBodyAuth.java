package com.example.diplomadmin.request_body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestBodyAuth {

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("password")
    @Expose
    private String password;

    public RequestBodyAuth(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
