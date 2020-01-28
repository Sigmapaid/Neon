package com.example.neon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FlightService {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("startDate")
    @Expose
    private String startDate;

    @SerializedName("stopDate")
    @Expose
    private String stopDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    @Override
    public String toString() {
        return name;
    }
}
