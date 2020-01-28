package com.example.neon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.neon.R;
import com.example.neon.ServicesAdapter;
import com.example.neon.data.model.Flight;
import com.example.neon.data.model.FlightService;
import com.example.neon.service.UserClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlightDetailActivity extends AppCompatActivity {

    private ListView lvFlightDetail;
    private List<FlightService> data2;
    private ServicesAdapter adapter2;
    private Button btnSave;
//    private List<FlightService> servicesList;

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


//    public FlightDetailActivity(List<FlightService> data) {
//        this.data = data;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);

        SharedPreferences sharedPref = this.getSharedPreferences("com.example.neon.data.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", "");


        lvFlightDetail =(ListView) findViewById(R.id.lvFlightDetail);
        btnSave = (Button) findViewById(R.id.btnSave);


        data2 = new ArrayList<FlightService>();



        String name = getIntent().getStringExtra("flight_url");

//        MyClass[] mcArray = gson.fromJson(jsonString, MyClass[].class);

//        data = Arrays.asList(new Gson().fromJson(name, FlightService[].class));

//        Create a empty class that extends a List of your object:
//
//        public class YourClassList extends ArrayList<YourClass> {}
//        And use it when parsing the JSON:

        data2 = new Gson().fromJson(name, Flight.FlightServiceList.class);

//        data = (List<FlightService>) getIntent().getSerializableExtra("flight_url");

//        Bundle bundle = getIntent().getExtras();

//        if (bundle != null){
//            data = bundle.getStringArrayList("Numer rejsu: )";
//        }

//        Toast.makeText(this, data2.get(1).toString(),Toast.LENGTH_SHORT).show();
        adapter2 = new ServicesAdapter(this, data2);

        lvFlightDetail.setAdapter(adapter2);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                updateFlight();

            }
        });

//        lvFlightDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(FlightDetailActivity.this, data2.get(position).toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private void updateFlight(String flightNumber, String token, Flight updatedFlight) {

        String authToken = "Bearer " + token;
//        Log.i("Token: ",authToken);

        Call<ResponseBody> call = userClient.updateFlights(Integer.parseInt(flightNumber), authToken, updatedFlight);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
//                    String json = new Gson().toJson(response.body());
                    Intent intent = new Intent(FlightDetailActivity.this, SecondActivity.class);
//                    intent.putExtra("flight_json",json);
//                    pd.dismiss();
                    startActivity(intent);


                }else {
                    Toast.makeText(FlightDetailActivity.this,"token is not correct",Toast.LENGTH_LONG).show();
//                    pd.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FlightDetailActivity.this,"error :( w getFlights",Toast.LENGTH_LONG).show();
//                pd.dismiss();
            }
        });
    }

}
