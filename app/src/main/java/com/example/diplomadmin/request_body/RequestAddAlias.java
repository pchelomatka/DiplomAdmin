package com.example.diplomadmin.request_body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAddAlias {

    @SerializedName("point_id")
    @Expose
    private String pointId;
    @SerializedName("title")
    @Expose
    private String title;

    public RequestAddAlias(String pointId, String title) {
        this.pointId = pointId;
        this.title = title;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
