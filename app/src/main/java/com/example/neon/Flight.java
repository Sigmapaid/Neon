package com.example.neon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    @SerializedName("flightNumber")
    @Expose
    private int flightNumber;
    @SerializedName("servicesList")
    @Expose
    private List<ServicesList> servicesList = null;

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<ServicesList> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<ServicesList> servicesList) {
        this.servicesList = servicesList;
    }

}
