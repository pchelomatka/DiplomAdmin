package com.example.diplomadmin.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDeleteAlias {
    @SerializedName("id")
    @Expose
    private String id;

    public RequestDeleteAlias(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
