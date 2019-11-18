package com.example.neon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private int flightNumber;
    private List<FlightService> servicesList = new ArrayList<>();


    public Flight(int flightNumber, List<FlightService> servicesList) {
        this.flightNumber = flightNumber;
        this.servicesList = servicesList;
    }

    public String getFlightNumber() {
        return String.valueOf(flightNumber);
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<FlightService> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<FlightService> servicesList) {
        this.servicesList = servicesList;
    }

}
