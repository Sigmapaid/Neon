package com.example.neon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ServicesAdapter extends ArrayAdapter<FlightService> {

    private List<FlightService> flightServices;
    private Context context;

    public ServicesAdapter(@NonNull Context context, List<FlightService> flightServices) {
        super(context, R.layout.flght_service_row);
        this.flightServices = flightServices;
        this.context = context;
    }

    @Override
    public int getCount() {
        return flightServices.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row2 = convertView;
        ServiceHolder holder = null;

        if (row2 == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row2 = inflater.inflate(R.layout.flght_service_row, null);

            holder = new ServiceHolder();
            holder.serviceName = (TextView) row2.findViewById(R.id.txtFlightServiceRow);

            row2.setTag(holder);
        } else {
            holder = (ServiceHolder) row2.getTag();
        }

//        holder.serviceName.setText("test");
        holder.serviceName.setText(flightServices.get(position).getName());

        return row2;

    }

    static class ServiceHolder {
        TextView serviceName;
    }
}
