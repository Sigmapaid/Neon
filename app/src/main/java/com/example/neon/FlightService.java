package com.example.neon;

import java.io.Serializable;

public class FlightService {
    private String name;

    public FlightService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
