package com.example.neon;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FlightList {

    @SerializedName("flight")
    private List<Flight> flights;

    public List<Flight> getFlights() {
        return flights;
    }
}
