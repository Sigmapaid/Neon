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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button btnLogin;
    private int counter = 3;



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
                _loadAPI_POST();
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
