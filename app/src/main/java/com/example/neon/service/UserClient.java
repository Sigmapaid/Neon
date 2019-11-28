package com.example.neon.service;

import com.example.neon.Flight;
import com.example.neon.FlightList;
import com.example.neon.Result;
import com.example.neon.data.model.Login;
import com.example.neon.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<User> login(@Body Login login);

    @GET("flight")
    Call<List<FlightList>> getFlights(@Header("Authorization") String authToken);
}
