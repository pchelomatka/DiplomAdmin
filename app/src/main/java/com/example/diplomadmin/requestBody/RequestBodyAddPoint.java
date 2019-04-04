package com.example.diplomadmin.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestBodyAddPoint {

    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("building_id")
    @Expose
    private String buildingId;

    public RequestBodyAddPoint(String deviceId, String title, String buildingId) {
        this.deviceId = deviceId;
        this.title = title;
        this.buildingId = buildingId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
}
