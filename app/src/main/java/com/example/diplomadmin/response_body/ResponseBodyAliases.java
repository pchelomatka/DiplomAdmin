package com.example.diplomadmin.response_body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBodyAliases {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response")
    @Expose
    private List<ResponseAliases> response = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ResponseAliases> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseAliases> response) {
        this.response = response;
    }
}
