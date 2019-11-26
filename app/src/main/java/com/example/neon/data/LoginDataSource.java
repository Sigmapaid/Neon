package com.example.neon.data;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.neon.MainActivity;
import com.example.neon.R;
import com.example.neon.data.model.LoggedInUser;
import com.example.neon.ui.login.LoginActivity;
import com.example.neon.ui.login.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import android.content.Context;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public String token;
    private String error;
    private Context context2;


    public Result<LoggedInUser> login(Context context, String username, String password) {

        context2 = context;

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser loggedUser;


            Ion.with(context)
                    .load("POST", "http://10.0.2.2:3000/login")
                    .setBodyParameter("email", username)
                    .setBodyParameter("password", password)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            if (e == null) {
                                try {
//                                    JSONObject o = (JSONObject) new JSONTokener(result).nextValue();
                                    JSONObject o = new JSONObject(result);
//                                    Log.i("Result z serwera: ", result);
//                                    Toast.makeText(context.this, result, Toast.LENGTH_LONG).show();
//                                    token = (String) o.getString("accessToken");

                                    token = o.optString("accessToken");
                                    error = o.optString("error");
//                                    Log.i("Token z serwera: ", token);
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }

                                if (TextUtils.isEmpty(error)) {
                                    //TODO: Save token
//                                    Log.i("Token z serwera: ", token);
                                    SharedPreferences sharedPref = context2.getSharedPreferences("com.example.neon.data.PREFERENCE_FILE_KEY",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("token", token);
                                    editor.commit();
                                } else {
                                    //Show error
//                                    Snackbar.make(username2,
//                                            error,
//                                            Snackbar.LENGTH_SHORT)
//                                            .show();
                                }
                            } else {
//                                Snackbar.make(username2,
//                                        "ERROR",
//                                        Snackbar.LENGTH_SHORT)
//                                        .show();
                            }
//                            showProgress(false);
                        }
                    });
                                    loggedUser =
                                            new LoggedInUser(
                                                    java.util.UUID.randomUUID().toString(),
                                                    username, token);


            return new Result.Success<>(loggedUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
