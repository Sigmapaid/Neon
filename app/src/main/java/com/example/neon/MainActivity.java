package com.example.neon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

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
                validate(userName.getText().toString(), password.getText().toString());
            }
        });

    }


    private void validate (String name, String userPassword){
        if (name.equals("admin") && userPassword.equals("1234")){
//            fetchData fD = new fetchData();
            Intent intent = new Intent(this.getApplicationContext(), GetDataFromServer.class);
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


}
