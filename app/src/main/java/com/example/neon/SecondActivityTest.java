package com.example.neon;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
//import com.kpbird.volleytest.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondActivityTest extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private ListView lvFlight;
    private RequestQueue mRequestQueue;
    private List<Flight> data;
    private LayoutInflater lf;
    private VolleyAdapter va;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lf = LayoutInflater.from(this);

        data = new ArrayList<Flight>();
        va = new VolleyAdapter();
//        va = new VolleyAdapter(this,data);

        lvFlight = (ListView) findViewById(R.id.listFlight);
        mRequestQueue =  Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000/flight";
        pd = ProgressDialog.show(this,"Please Wait...","Please Wait...");
        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }
        JsonObjectRequest jr = new
                JsonObjectRequest(Request.Method.GET,url,null,new

                Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        parseJSON(response);
                        va.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), response.toString(),

                                Toast.LENGTH_LONG).show();

                        pd.dismiss();
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,error.getMessage());
            }
        });
        mRequestQueue.add(jr);

        lvFlight.setAdapter(va);
    }



    private void parseJSON(JSONObject json){
        try{
            JSONObject value = json.getJSONObject("flightNumber");
            JSONArray items = value.getJSONArray("servicesList");
            for(int i=0;i<items.length();i++){
                JSONObject item = items.getJSONObject(i);
                Flight nm = new Flight();
                nm.setFlightNumber(Integer.parseInt(item.optString("flightNumber")));
//                nm.setServicesList(item.getJSONArray("servicesList"));
                data.add(nm);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }


//    class Flight {
//        private String title;
//
//        void setTitle(String title) {
//            this.title = title;
//        }
//
//        String getTitle() {
//
//            return title;
//        }
//
//        public String getFlightNumber() {
//        }
//    }


    class VolleyAdapter extends BaseAdapter {
//    class VolleyAdapter extends ArrayAdapter<com.example.neon.Flight> {

        private List<Flight> data;
        private Context context;


//        public VolleyAdapter(@NonNull Context context, List<Flight> data) {
//            super(context, R.layout.layout_list_row);
//            this.data = data;
//            this.context = context;
//
//        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Flight getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            FlightHolder holder ;
            if(view == null){
                holder = new FlightHolder();
                view = lf.inflate(R.layout.layout_list_row,null);
                holder.flightNumber = (TextView) view.findViewById(R.id.flight_number);

                view.setTag(holder);
            }
            else{
                holder = (FlightHolder) view.getTag();
            }

//            Flight nm = data.get(position);
            holder.flightNumber.setText("Rejs: " + data.get(position).getFlightNumber());
            return view;
        }

        class FlightHolder {
            TextView flightNumber;
        }

    }
}