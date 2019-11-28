package com.example.neon.Activity;

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

import com.example.neon.FlightsAdapter;
import com.example.neon.R;
import com.example.neon.data.model.Flight;
import com.example.neon.data.model.FlightList;
import com.google.gson.Gson;

import java.util.List;

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

//        String json2 = new Gson().toJson(data);
//        data = new Gson().fromJson(mResponse, FlightList.class);

//        String flightJson = getIntent().getStringExtra("flight_json");
//        Type collectionType = new TypeToken<Collection<Flight>>(){}.getType();
//        Collection<Flight> postsList = new Gson().fromJson(flightJson, collectionType);
//        data = new ArrayList<>(postsList);

//        data = new Gson().fromJson(result, FlightList.class);
//        List<Flight> postsList = Arrays.asList(new Gson().fromJson(flightJson,Flight.class));

        String flightJson = getIntent().getStringExtra("flight_json");
//        Log.i("String po intent ", flightJson);
        data =  new Gson().fromJson(flightJson, FlightList.class);
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


}




