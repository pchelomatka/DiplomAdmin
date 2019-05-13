package com.example.diplomadmin.request_body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDeletePoint {

    @SerializedName("id")
    @Expose
    private String id;

    public RequestDeletePoint(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
