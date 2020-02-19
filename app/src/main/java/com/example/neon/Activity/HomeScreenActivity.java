package com.example.neon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.neon.R;
import com.example.neon.data.model.Flight;
import com.example.neon.service.UserClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreenActivity extends AppCompatActivity {

    private Button btnArrival;
    private Button btnDeparture;
    private Button btnNotification;
    private Button btnNotes;
    private Button btnNeonTools;
    private ProgressDialog pd;


    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
//            .client(new OkHttpClient().newBuilder()
//                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .build())
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btnArrival = (Button) findViewById(R.id.btnArrival);
        btnDeparture = (Button) findViewById(R.id.btnDeparture);
        btnNotification = (Button) findViewById(R.id.btnNotification);
        btnNotes = (Button) findViewById(R.id.btnNotes);
        btnNeonTools = (Button) findViewById(R.id.btnNeonTools);
        SharedPreferences sharedPref = this.getSharedPreferences("com.example.neon.data.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", "");

        Log.i("token: ", token);

        btnArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFlights(token);
            }
        });

        btnDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFlights(token);
            }
        });

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity.this, com.example.neon.MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void backToLoginActivity(View view) {

        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void getFlights(String token) {
        pd = ProgressDialog.show(this,"Please Wait...","Please Wait...");


        String authToken = "Bearer " + token;
//        Log.i("Token: ",authToken);

        Call<List<Flight>> call = userClient.getFlights(authToken);

        call.enqueue(new Callback<List<Flight>>() {
            @Override
            public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_LONG).show();
//                    Log.i("flights z serwera ",response.body().toString());
//                    List<Flight> flightList = response.body();
//                    for(int i=0; i<flightList.size();i++){
//                    Log.i("flight z serwera ", String.valueOf(flightList.get(i).getFlightNumber()));
//                    for (int j=0; j<flightList.get(i).getServicesList().size(); j++) {
//                        Log.i("service z serwera ", String.valueOf(flightList.get(i).getServicesList().get(j).getName()));
//                        }
//                    }
                    String json = new Gson().toJson(response.body());
//                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    Intent intent = new Intent(HomeScreenActivity.this, SecondActivity.class);
                    intent.putExtra("flight_json",json);
                    pd.dismiss();
                    startActivity(intent);


                }else {
                    Toast.makeText(HomeScreenActivity.this,"token is not correct",Toast.LENGTH_LONG).show();
                    pd.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<Flight>> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this,"error :( w getFlights",Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        });
    }
}
