package com.example.neon;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.neon.data.model.FlightService;
import com.google.gson.Gson;

import java.util.Date;
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
            holder.startStopTime = (TextView) row2.findViewById(R.id.txtStartStop);
            holder.btnStartDate = (Button) row2.findViewById(R.id.btnStartDate);
            holder.btnStopDate = (Button) row2.findViewById(R.id.btnEndDate);

        ServiceHolder finalHolder = holder;

        holder.btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date(System.currentTimeMillis());
//                String dataTest = new Gson().toJson(date);
                Log.d("test Daty: ", String.valueOf(date));
                finalHolder.startStopTime.setText(String.valueOf(date));
                finalHolder.btnStartDate.setEnabled(false);
                flightServices.get(position).setStartDate(String.valueOf(date));
                notifyDataSetChanged();

            }
        });

        ServiceHolder finalHolder2 = holder;
        holder.btnStopDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Date date = new Date(System.currentTimeMillis());
//                String dataTest = new Gson().toJson(date);
                Log.d("test Daty: ", String.valueOf(date));
                finalHolder.startStopTime.setText(String.valueOf(date));
                finalHolder.btnStopDate.setEnabled(false);
                flightServices.get(position).setStopDate(String.valueOf(date));
                notifyDataSetChanged();

            }
        });



//        holder.serviceName.setText("test");
        holder.serviceName.setText(flightServices.get(position).getName());

        return row2;

    }

    static class ServiceHolder {
        TextView serviceName;
        TextView startStopTime;
        Button btnStartDate;
        Button btnStopDate;

    }
}
