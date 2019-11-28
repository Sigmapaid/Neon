package com.example.neon.data.model;

import com.example.neon.data.model.Flight;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

//    @SerializedName("users")
//    @Expose
//    private List<User> users = null;
    @SerializedName("flight")
    @Expose
    private List<Flight> flight = null;

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    public List<Flight> getFlight() {
        return flight;
    }

    public void setFlight(List<Flight> flight) {
        this.flight = flight;
    }
}
