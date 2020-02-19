package com.example.neon.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.neon.R;
import com.example.neon.data.model.Flight;
import com.example.neon.data.model.Login;
import com.example.neon.data.model.User;
import com.example.neon.service.UserClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button btnLogin;
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
        setContentView(R.layout.activity_main_org);

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

    private void saveToken(String token) {
        SharedPreferences sharedPref = this.getSharedPreferences("com.example.neon.data.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", token);
        editor.commit();
    }

    private void login() {
        pd = ProgressDialog.show(this,"Please Wait...","Please Wait...");

        Login login = new Login(userName.getText().toString(), password.getText().toString());
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(MainActivity.this,response.body().getAccessToken(),Toast.LENGTH_LONG).show();
                    token = response.body().getAccessToken();
                    saveToken(token);
//                    getFlights(token);

                    Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
                    pd.dismiss();
                    startActivity(intent);


                }else {
                    Toast.makeText(MainActivity.this,"login not correct",Toast.LENGTH_LONG).show();
                    pd.dismiss();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error :( w login",Toast.LENGTH_LONG).show();
                pd.dismiss();

            }
        });
    }

    private void getFlights(String token) {

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
                    Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
                    intent.putExtra("flight_json",json);
                    pd.dismiss();
                    startActivity(intent);


                }else {
                    Toast.makeText(MainActivity.this,"token is not correct",Toast.LENGTH_LONG).show();
                    pd.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<Flight>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error :( w getFlights",Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        });
    }


//    private void validate (String name, String userPassword){
//        if (name.equals("admin") && userPassword.equals("1234")){
////            fetchData fD = new fetchData();
////            String stringToJson = "\r\n    \"email\": \"+ name + \",\r\n    \"password\": \"+ userPassword +\"\r\n";
////            Gson g = (JsonToken) new Gson().toJson(stringToJson); .fromJson(stringToJson, Player.class)
//            Intent intent = new Intent(this.getApplicationContext(), SecondActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }else {
//            counter--;
//            if (counter == 0) {
//                btnLogin.setEnabled(false);
//            }else {
//            Toast.makeText(MainActivity.this, "Bledne dane logowania!",Toast.LENGTH_LONG).show();
//
//
//            }
//        }
//    }

//            String URL = "http://www.mocky.io/v2/5dd322e93300002a007a4026";
//            String URL = "http://10.0.2.2:3000/login";
//            VolleyApiCAll volleyApiCAll = new VolleyApiCAll(MainActivity.this);
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
}
