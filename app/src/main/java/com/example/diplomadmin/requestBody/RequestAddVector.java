package com.example.diplomadmin.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAddVector {

    @SerializedName("building_id")
    @Expose
    private String buildingId;
    @SerializedName("start_point")
    @Expose
    private String startPoint;
    @SerializedName("end_point")
    @Expose
    private String endPoint;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("direction")
    @Expose
    private String direction;

    public RequestAddVector(String buildingId, String startPoint, String endPoint, String distance, String direction) {
        this.buildingId = buildingId;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.direction = direction;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
