package com.example.neon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightDetailActivity extends AppCompatActivity {

    private ListView lvFlightDetail;
    private List<FlightService> data2;
    private ServicesAdapter adapter2;
//    private List<FlightService> servicesList;


//    public FlightDetailActivity(List<FlightService> data) {
//        this.data = data;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);


        lvFlightDetail =(ListView) findViewById(R.id.lvFlightDetail);

        data2 = new ArrayList<FlightService>();



        String name = getIntent().getStringExtra("flight_url");

//        MyClass[] mcArray = gson.fromJson(jsonString, MyClass[].class);

//        data = Arrays.asList(new Gson().fromJson(name, FlightService[].class));

//        Create a empty class that extends a List of your object:
//
//        public class YourClassList extends ArrayList<YourClass> {}
//        And use it when parsing the JSON:

        data2 = new Gson().fromJson(name, FlightServiceList.class);

//        data = (List<FlightService>) getIntent().getSerializableExtra("flight_url");

//        Bundle bundle = getIntent().getExtras();

//        if (bundle != null){
//            data = bundle.getStringArrayList("Numer rejsu: )";
//        }

        Toast.makeText(this, data2.get(1).toString(),Toast.LENGTH_SHORT).show();
        adapter2 = new ServicesAdapter(this, data2);

        lvFlightDetail.setAdapter(adapter2);

//        lvFlightDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(FlightDetailActivity.this, data2.get(position).toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
