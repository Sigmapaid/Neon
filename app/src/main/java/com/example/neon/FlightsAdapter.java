package com.example.neon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.neon.data.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightsAdapter extends ArrayAdapter<Flight> {

    private List<Flight> data = new ArrayList<>();
    private Context context;

    public FlightsAdapter(@NonNull Context context, List<Flight> data) {
        super(context, R.layout.layout_list_row);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
//        if (data != null){
//        return data.size();}
//        else {return 0;}
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FlightHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.layout_list_row, null);

            holder = new FlightHolder();
            holder.flightNumber = (TextView) row.findViewById(R.id.flight_number);


            row.setTag(holder);
        } else {
            holder = (FlightHolder) row.getTag();
        }

        holder.flightNumber.setText("Rejs: " + data.get(position).getFlightNumber());

        return row;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    static class FlightHolder {
        TextView flightNumber;
    }
}
