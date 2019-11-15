package com.example.neon;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class fetchDataOrg extends AsyncTask<Void, Void, Void> {

    private List<Flight> responseStr;
    private String dataString;
//    private ListView lvFlight;
    public FlightsAdapter adapter;


    @Override
    protected Void doInBackground(Void... voids) {

        responseStr = new ArrayList<>();

        try {
        URL url = new URL("https://api.myjson.com/bins/1gxwoi");
//        URL url = new URL("http://10.0.2.2:3000/flight");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = " ";
        while (line != null) {
            line = bufferedReader.readLine();
            dataString = dataString + line;
        }
            responseStr = new Gson().fromJson(dataString, FlightList.class);
//        SecondActivity.adapter.addAll(responseStr);

//        SecondActivity.data = responseStr;
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    @Override
    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);

//        adapter = new FlightsAdapter(this, responseStr);

//        lvFlight = (ListView) findViewById(R.id.listFlight);

//        SecondActivity.lvFlight.setAdapter(adapter);
//            for (Flight flight : responseStr
//            ) {
////                SecondActivity.adapter.add(flight);

            }


    }

