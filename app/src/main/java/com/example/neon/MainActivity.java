package com.example.neon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.neon.data.model.Login;
import com.example.neon.data.model.User;
import com.example.neon.service.UserClient;
import com.example.neon.ui.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button btnLogin;
    private int counter = 3;

//    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//    interceptor.;
//    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//    HttpLoggingInterceptor.Logger





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
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.etUserName);
        password = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                validate(userName.getText().toString(), password.getText().toString());
//                _loadAPI_POST();
                login();
            }
        });

    }

    private String token;

    private void login() {
        Login login = new Login("olivier@mail.com", "bestPassw0rd");
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this,response.body().getAccessToken(),Toast.LENGTH_LONG).show();
                    token = response.body().getAccessToken();
                    getFlights(token);


                }else {
                    Toast.makeText(MainActivity.this,"login not correct",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error :( w login",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getFlights(String token) {

        String authToken = "Bearer " + token;
//        Log.i("Token: ",authToken);

        Call<List<FlightList>> call = userClient.getFlights(authToken);

        call.enqueue(new Callback<List<FlightList>>() {
            @Override
            public void onResponse(Call<List<FlightList>> call, Response<List<FlightList>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_LONG).show();
//                    Log.i("flights z serwera ",response.body().toString());
                    List<FlightList> flightList = response.body();
                    Log.i("flights z serwera, lista ",flightList.toString());


                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("flight_json",flightList.toString().trim());
                    startActivity(intent);


                }else {
                    Toast.makeText(MainActivity.this,"token is not correct",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<FlightList>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error :( w getFlights",Toast.LENGTH_LONG).show();
            }
        });
    }


    private void validate (String name, String userPassword){
        if (name.equals("admin") && userPassword.equals("1234")){
//            fetchData fD = new fetchData();
//            String stringToJson = "\r\n    \"email\": \"+ name + \",\r\n    \"password\": \"+ userPassword +\"\r\n";
//            Gson g = (JsonToken) new Gson().toJson(stringToJson); .fromJson(stringToJson, Player.class)
            Intent intent = new Intent(this.getApplicationContext(), SecondActivity.class);
            startActivity(intent);
            finish();
            return;
        }else {
            counter--;
            if (counter == 0) {
                btnLogin.setEnabled(false);
            }else {
            Toast.makeText(MainActivity.this, "Bledne dane logowania!",Toast.LENGTH_LONG).show();


            }
        }
    }

    public void _loadAPI_POST() {
        // Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster.
        //Volley is not suitable for large download or streaming operations, since Volley holds all responses in memory during parsing.
        // Read https://developer.android.com/training/volley/index.html

        //Add New Address
        Map<String, String> jsonPOST = new HashMap<>();
        jsonPOST.put("email", userName.getText().toString());
        jsonPOST.put("password", password.getText().toString());

//        String loginJson = new Gson().toJson(jsonPOST, LoginJson.class);

//        Log.i("jsonPOST: ", loginJson);

        _SEND(jsonPOST); // Check INTERNET is ON or NOT ?

    }

    private void _SEND(Map<String, String> getPARAM) {
        try {

//            String URL = "http://www.mocky.io/v2/5dd322e93300002a007a4026";
            String URL = "http://10.0.2.2:3000/login";
            VolleyApiCAll volleyApiCAll = new VolleyApiCAll(MainActivity.this);
//            volleyApiCAll.Volley_GET(URL, new VolleyApiCAll.VolleyCallback() {
//
//                @Override
//                public void onSuccessResponse(String result) {
//
//
//                    try {
//                        if (result.matches("VOLLEY_NETWORK_ERROR")) {
//                            Toast.makeText(MainActivity.this, "NETWORK PROBLEM", Toast.LENGTH_SHORT).show();
//                        } else {
//                            try {
//                                System.out.println("RESULT" + result);
//                                // GET JSON THROUGH result
////                                JSONArray jArray = new JSONArray(result);
//
////                                Type collectionType = new TypeToken<Collection<Flight>>(){}.getType();
////                                Collection<Flight> postsList = new Gson().fromJson(result, collectionType);
//
////                                data = new Gson().fromJson(result, FlightList.class);
////                                List<Flight> postsList = Arrays.asList(new Gson().fromJson(result,Flight.class));
////                                data = new ArrayList<>(postsList);
////                                while (hasNext())
////                                    action.accept(next());
////                                 data  = new Gson().fromJson(result, Flight.class);
////                                adapter = new FlightsAdapter(SecondActivity.this, data);
////                                lvFlight.setAdapter(adapter);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
            volleyApiCAll.Volley_POST(getPARAM, URL, new VolleyApiCAll.VolleyCallback()
            {
                @Override
                public void onSuccessResponse(String result)
                {


                    try
                    {
                        if(result.matches("VOLLEY_NETWORK_ERROR"))
                        {
                            Toast.makeText(MainActivity.this, "NETWORK PROBLEM", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            try
                            {


                                System.out.println("RESULT"+result);
                                // GET JSON THROUGH result


                            }
                            catch (Exception e)
                            {

                                e.printStackTrace();
                            }
                        }
                    }
                    catch (Exception e)
                    {

                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
