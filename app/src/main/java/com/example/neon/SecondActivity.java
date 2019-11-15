package com.example.neon;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView lvFlight;
    public List<Flight> data;
    public FlightsAdapter adapter;
    private List<FlightService> servicesList;
    private List<FlightService> servicesList2;
    private static String tag = "123";

    Handler handler;

//    private static final int PERMISSION_INTERNET = 1;
//    private static final int PERMISSION_ACCESS_NETWORK_STATE = 2;
//
//    private void requestPermission(String permission, int requestId) {
//        if (ContextCompat.checkSelfPermission(this,
//                permission)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{permission},
//                    requestId);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_INTERNET: {
//                if (grantResults.length <= 0
//                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    requestPermission(Manifest.permission.INTERNET, PERMISSION_INTERNET);
//                }
//                return;
//            }
//            case PERMISSION_ACCESS_NETWORK_STATE: {
//                if (grantResults.length <= 0
//                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    requestPermission(Manifest.permission.ACCESS_NETWORK_STATE, PERMISSION_ACCESS_NETWORK_STATE);
//                }
//                return;
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvFlight = (ListView) findViewById(R.id.listFlight);
//        handler = new Handler(getApplicationContext().getMainLooper());

        data = new ArrayList<Flight>();
//        Bundle bundle = savedInstanceState;
//        data = bundle.getSerializable("flight_json");

        String flightJson = getIntent().getStringExtra("flight_json");
//        data = getIntent().getSerializableExtra(("flight_json");

        data = new Gson().fromJson(flightJson, FlightList.class);

//        Log.i("data w second: " + data.);
//        servicesList = new ArrayList<FlightService>();
//        servicesList2 = new ArrayList<FlightService>();

//        servicesList.add(new FlightService("autobus"));
//        servicesList.add(new FlightService("tankowanie"));
//        servicesList.add(new FlightService("wypychanie"));
//        servicesList.add(new FlightService("rozladowanie"));
//        servicesList.add(new FlightService("sprzatanie"));
//        servicesList2.add(new FlightService("autobus"));
//        servicesList2.add(new FlightService("sprzatanie"));
//        servicesList2.add(new FlightService("tankowanie"));
//        servicesList2.add(new FlightService("wypychanie"));
//        servicesList2.add(new FlightService("rozladowanie"));
//
//
//        data.add(new Flight(1, servicesList));
//        data.add(new Flight(2, servicesList2));
//        data.add(new Flight(3, servicesList));
//        data.add(new Flight(4, servicesList));
//        data.add(new Flight(5, servicesList));
//        data.add(new Flight(6, servicesList));

//        String json2 = new Gson().toJson(data);
//        Log.i("onCreate: ", json2);

//        requestPermission(Manifest.permission.INTERNET, PERMISSION_INTERNET);
//        requestPermission(Manifest.permission.ACCESS_NETWORK_STATE, PERMISSION_ACCESS_NETWORK_STATE);

//          ApplicationController.getInstance().cancelPendingRequests("my");
//        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
//        String mResponse = m.getString("Response", "");
//        data = new Gson().fromJson(mResponse, FlightList.class);


        adapter = new FlightsAdapter(this, data);


//        adapter.notifyDataSetChanged();

//        lvFlight.setAdapter(adapter);
//            Log.i("Zawartosc data: ", String.valueOf(data.size()));
        lvFlight.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //odswiezenie ekranu
        lvFlight.postInvalidate();

//            Log.i("Zawartosc data: ",data.get(0).getFlightNumber());

//        for (int i=0; i<data.size(); i++){
////            Log.i("JsonResponse: ", String.valueOf(responseStr.getServicesList().get(i)));
//            Log.i("Zawartosc data po metodzie: ",data.get(i).getFlightNumber());
//        }

//        Log.i("wielkosc data: ", String.valueOf(data.size()));
//        fetchData process = new fetchData();
//        process.execute();
//        for(int i=0; i<data.size(); i++){
////            JSONObject flight = response.getJSONObject(i);
//
////            String jsonResponse = flight.toString();
////            Log.i("JsonResponse: ", String.valueOf(flight1.getServicesList().get(1)));
//        }


        lvFlight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SecondActivity.this, data.get(position).getFlightNumber(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FlightDetailActivity.class);
                String json = new Gson().toJson(data.get(position).getServicesList());
//                Log.i("onItemClick: ",json);
                intent.putExtra("flight_url", json);
//                intent.putExtra("flight_url", (Serializable) data.get(position).getServicesList());
                startActivity(intent);
            }
        });

    }


//    private void sharedResponse(String response) {
//        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = m.edit();
//        editor.putString("Response", response);
//        editor.commit();
//    }
//}
//
//    public void flightClick (AdapterView<?> parent, View view, int position, long id){
//        Toast.makeText(SecondActivity.this, data.get(position).getFlightNumber(),Toast.LENGTH_SHORT).show();
//    }
//        protected void flightClick(FlightsAdapter adapter, View v,
//                                   int position, long id) {
//            super.flightClick(l, v, position, id);
//            String text = " Position: " + position + " " + data.get(position);
//        }
}




