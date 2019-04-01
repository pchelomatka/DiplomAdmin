package com.example.diplomadmin.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBodyAuth {


    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response")
    @Expose
    private ResponseAuth response;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ResponseAuth getResponse() {
        return response;
    }

    public void setResponse(ResponseAuth response) {
        this.response = response;
    }
}
