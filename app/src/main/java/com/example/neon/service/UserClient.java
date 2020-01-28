package com.example.neon.service;

import com.example.neon.data.model.Flight;
import com.example.neon.data.model.Login;
import com.example.neon.data.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserClient {

    @POST("login")
    Call<User> login(@Body Login login);

    @Headers("Content-Type: application/json")
    @GET("flight")
    Call<List<Flight>> getFlights(@Header("Authorization") String authToken);

    @Headers("Content-Type: application/json")
    @PATCH("/flight/?flightNumber={flightNumber}")
    Call<ResponseBody> updateFlights(@Path ("flightNumber") int flightNumber, @Header("Authorization") String authToken, @Body Flight updatedFlight);
}
