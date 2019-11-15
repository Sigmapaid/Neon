package com.example.neon;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class fetchData extends AppCompatActivity {

        List<Flight> responseStr = new ArrayList<>();
//                requestQueue = Volley.newRequestQueue(this);
                final String URL = "http://10.0.2.2:3000/flight";
                String jsonResponse;
//                Context context;
    RequestQueue requestQueue;

//    public fetchData(Context context) {
//        this.context = context;
//    }

    private String getFlightFromServer() {
        requestQueue = Volley.newRequestQueue(this);
        new Thread(new Runnable() {
            @Override
            public void run() {

                JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

//                responseStr = response.toString();
                        //sharedResponse(response.toString());

//                    for (int i = 0; i < response.length(); i++) {
                        try {
                             jsonResponse = response.toString();
//                            Log.i("jsonResponse z serwera: ", jsonResponse);

//                            responseStr.addAll(new Gson().fromJson(jsonResponse, FlightList.class));

//                            Log.i("z data : ", responseStr.get(1).getFlightNumber());


//parse you Json here
//                            JSONObject flight = response.getJSONObject(i);
//                            String jsonResponse = flight.toString();
//                            Flight flight1 = new Gson().fromJson(jsonResponse, Flight.class);
//                            responseStr.add(flight1);
//                            adapter = new FlightsAdapter(getApplicationContext(), responseStr);
//                        adapter.addAll(responseStr);
//                            adapter.notifyDataSetChanged();


                        } catch (ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace();
//                        }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                    }
                });

//                try {
//                    for(int i=0; i<response.length(); i++){
//                        JSONObject flight = response.getJSONObject(i);
//
//                        String jsonResponse = flight.toString();
//                        Flight flight1 = new Gson().fromJson(jsonResponse, Flight.class);
//                        Log.i("JsonResponse: ",flight1.getFlightNumber());
//                        for (int j=0; j<flight1.getServicesList().size(); j++) {
//                            Log.i("JsonResponse: ", String.valueOf(flight1.getServicesList().get(j)));
//                        }
//                        data.add(flight1);
//                    }
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//
//            }
//
//        },
//                new Response.ErrorListener(){
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Rest Response", error.toString());
//
//                    }
//
//                }
//
//                );

//        Log.i("wielkosc data: ", String.valueOf(data.size()));

//        for(int i=0; i<data.size(); i++){
////            JSONObject flight = response.getJSONObject(i);
//
////            String jsonResponse = flight.toString();
////            Log.i("JsonResponse: ", String.valueOf(flight1.getServicesList().get(1)));
//            Log.i("Zawartosc data: ",data.get(i).getFlightNumber());
//        }
                objectRequest.setTag("my");
                requestQueue.add(objectRequest);
//        requestQueue.cancelAll("my");

//                for (int i=0; i<data.size(); i++){
////            Log.i("JsonResponse: ", String.valueOf(responseStr.getServicesList().get(i)));
//                    Log.i("Zawartosc responseStr przed metoda: ",responseStr.get(i).getFlightNumber());
//                }


//                Log.i("Size responseStr: ", String.valueOf(responseStr.size()));
            }
        }).start();


//        for (int i = 0; i < responseStr.size(); i++) {
////            Log.i("JsonResponse: ", String.valueOf(responseStr.getServicesList().get(i)));
//            Log.i("Zawartosc responseStr: ", responseStr.get(i).getFlightNumber());
//            for (int j = 0; j < responseStr.get(i).getServicesList().size(); j++) {
//
//                Log.i("Zawartosc responseStr Lista uslug: ", responseStr.get(i).getServicesList().get(j).getName());
//
//            }
//
//        }

            return jsonResponse;

//                        adapter.clear();


    }
    }

