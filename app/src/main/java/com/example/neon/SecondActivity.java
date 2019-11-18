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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private ListView lvFlight;
    public List<Flight> data = new ArrayList<Flight>();
    public FlightsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvFlight = (ListView) findViewById(R.id.listFlight);

        _loadAPI_POST();

//        String json2 = new Gson().toJson(data);
//        data = new Gson().fromJson(mResponse, FlightList.class);

//        String flightJson = getIntent().getStringExtra("flight_json");
//
//        data = new Gson().fromJson(flightJson, FlightList.class);
//
//
//
//
//        adapter = new FlightsAdapter(this, data);
//        lvFlight.setAdapter(adapter);


        lvFlight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SecondActivity.this, data.get(position).getFlightNumber(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FlightDetailActivity.class);
                String json = new Gson().toJson(data.get(position).getServicesList());
                intent.putExtra("flight_url", json);
//                intent.putExtra("flight_url", (Serializable) data.get(position).getServicesList());
                startActivity(intent);
            }
        });

    }

    public void _loadAPI_POST() {
        // Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster.
        //Volley is not suitable for large download or streaming operations, since Volley holds all responses in memory during parsing.
        // Read https://developer.android.com/training/volley/index.html

        //Add New Address
        Map<String, String> jsonPOST = new HashMap<>();
        jsonPOST.put("KEY", "VAL");
        jsonPOST.put("KEY1", "VAL1");

        _SEND(jsonPOST); // Check INTERNET is ON or NOT ?

    }

    private void _SEND(Map<String, String> getPARAM) {
        try {

//            String URL = "http://www.mocky.io/v2/5dd322e93300002a007a4026";
            String URL = "http://10.0.2.2:3000/flight";
            VolleyApiCAll volleyApiCAll = new VolleyApiCAll(SecondActivity.this);
            volleyApiCAll.Volley_GET(URL, new VolleyApiCAll.VolleyCallback() {

                @Override
                public void onSuccessResponse(String result) {


                    try {
                        if (result.matches("VOLLEY_NETWORK_ERROR")) {
                            Toast.makeText(SecondActivity.this, "NETWORK PROBLEM", Toast.LENGTH_SHORT).show();
                        } else {
                            try {


//                                System.out.println("RESULT" + result);
                                // GET JSON THROUGH result
//                                JSONArray jArray = new JSONArray(result);

                                Type collectionType = new TypeToken<Collection<Flight>>(){}.getType();
                                Collection<Flight> postsList = new Gson().fromJson(result, collectionType);

//                                data = new Gson().fromJson(result, FlightList.class);
//                                List<Flight> postsList = Arrays.asList(new Gson().fromJson(result,Flight.class));
                                data = new ArrayList<>(postsList);
//                                while (hasNext())
//                                    action.accept(next());
//                                 data  = new Gson().fromJson(result, Flight.class);
                                adapter = new FlightsAdapter(SecondActivity.this, data);
                                lvFlight.setAdapter(adapter);
                            } catch (Exception e) {

                                e.printStackTrace();


                            }


                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                }
            });
//            volleyApiCAll.Volley_POST(getPARAM, URL, new VolleyApiCAll.VolleyCallback()
//            {
//                @Override
//                public void onSuccessResponse(String result)
//                {
//
//
//                    try
//                    {
//                        if(result.matches("VOLLEY_NETWORK_ERROR"))
//                        {
//                            Toast.makeText(SecondActivity.this, "NETWORK PROBLEM", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            try
//                            {
//
//
//                                System.out.println("RESULT"+result);
//                                // GET JSON THROUGH result
//
//
//                            }
//                            catch (Exception e)
//                            {
//
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    catch (Exception e)
//                    {
//
//                        e.printStackTrace();
//                    }
//                }
//            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




