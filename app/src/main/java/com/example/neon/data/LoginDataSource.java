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
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import android.content.Context;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private String token;
    private String error;
    private Context context2;
    private String username2;
    private String password2;
//    private String result2;
//    private Result result;


    public Result<LoggedInUser> login(Context context, String username, String password) {

        context2 = context;
        username2 = username;
        password2 = password;

        try {
//             TODO: handle loggedInUser authentication
//            LoggedInUser loggedUser = loginUser (context, username, password);



//            if (!TextUtils.isEmpty(token)){
//                loggedUser =
//                        new LoggedInUser(
//                                java.util.UUID.randomUUID().toString(),
//                                username, token);
//            }
//            return loginUser (context, username, password);
            return new Result.Success<>(loginUser (context, username, password));
//            else if (!TextUtils.isEmpty(result2)){
//                Exception e = new Exception(result2);
//                return new Result.Error(e);}
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }

//        (loggedUser != null) return new Result.Success<>(loggedUser) : return new Result.Success<>(loggedUser);
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private LoggedInUser loginUser (Context context, String username, String password){

//Future
        Ion.with(context)
                .load("POST", "http://10.0.2.2:3000/login")
//                .uploadProgressBar(uploadProgressBar)
                .setLogging("MyLogs", Log.DEBUG)
                .setBodyParameter("email", username)
                .setBodyParameter("password", password)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        Log.i("\n", "\n");
                        Log.i("Username i haslo: ", username2 + " " + password2);
                        Log.i("\n", "\n");

                        if (e == null) {
                            try {
//                                    JSONObject o = (JSONObject) new JSONTokener(result).nextValue();
                                JSONObject o = new JSONObject(result);
//                                    Log.i("Result z serwera: ", result);
//                                    Toast.makeText(context.this, result, Toast.LENGTH_LONG).show();
//                                    token = (String) o.getString("accessToken");
//                                result2 = result;

                                token = o.optString("accessToken");
                                error = o.optString("error");
                                Log.i("accessToken : ", token);
                                Log.i("\n", "\n");
                                Log.i("error : ", error);
                                Log.i("\n", "\n");
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }

                            if (TextUtils.isEmpty(error)) {
                                //TODO: Save token
                                SharedPreferences sharedPref = context2.getSharedPreferences("com.example.neon.data.PREFERENCE_FILE_KEY",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("token", token);
                                editor.commit();
                                LoggedInUser loggedUser =
                                        new LoggedInUser(
                                                UUID.randomUUID().toString(),
                                                username2, token);
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
        LoggedInUser loggedUser = new LoggedInUser(
                UUID.randomUUID().toString(),
                username, token);

            return loggedUser;

    }
}
