package com.example.neon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private ListView lvFlight;
    public List<Flight> data;
    public FlightsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvFlight = (ListView) findViewById(R.id.listFlight);
//        String accessToken = getIntent().getStringExtra("token");
//        Log.i("Token: ",accessToken);

//        _loadAPI_POST(accessToken);

//        String json2 = new Gson().toJson(data);
//        data = new Gson().fromJson(mResponse, FlightList.class);

        String flightJson = getIntent().getStringExtra("flight_json");
        Type collectionType = new TypeToken<Collection<Flight>>(){}.getType();
        Collection<Flight> postsList = new Gson().fromJson(flightJson, collectionType);

//                                data = new Gson().fromJson(result, FlightList.class);
//        List<Flight> postsList = Arrays.asList(new Gson().fromJson(flightJson,Flight.class));
        data = new ArrayList<>(postsList);


//        String flightJson = getIntent().getStringExtra("flight_json");
//        Log.i("String po intent ", flightJson);
//        data = new Gson().fromJson(flightJson, FlightList.class);
        adapter = new FlightsAdapter(this, data);
        lvFlight.setAdapter(adapter);


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

    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Naciśnięto przycisk wstecz", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPref = this.getSharedPreferences("com.example.neon.data.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        super.onBackPressed();
    }

    public void _loadAPI_POST( String token) {
        // Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster.
        //Volley is not suitable for large download or streaming operations, since Volley holds all responses in memory during parsing.
        // Read https://developer.android.com/training/volley/index.html

        //Add New Address
        Map<String, String> jsonPOST = new HashMap<>();
        jsonPOST.put("KEY", "VAL");
        jsonPOST.put("KEY1", "VAL1");

        _SEND(jsonPOST,token); // Check INTERNET is ON or NOT ?

    }

    private void _SEND(Map<String, String> getPARAM, String token) {
        try {

//            String URL = "http://www.mocky.io/v2/5dd322e93300002a007a4026";
            String URL = "http://10.0.2.2:3000/flight";
            VolleyApiCAll volleyApiCAll = new VolleyApiCAll(SecondActivity.this);
            volleyApiCAll.Volley_GET(URL, token, new VolleyApiCAll.VolleyCallback() {

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




