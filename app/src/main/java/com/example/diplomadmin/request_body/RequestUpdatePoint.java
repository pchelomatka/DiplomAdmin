package com.example.diplomadmin.request_body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestUpdatePoint {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("building_id")
    @Expose
    private String buildingId;

    public RequestUpdatePoint(String id, String deviceId, String title, String buildingId) {
        this.id = id;
        this.deviceId = deviceId;
        this.title = title;
        this.buildingId = buildingId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
