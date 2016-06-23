package com.example.revit.atry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {
    private EditText mUserView;
    public static String cookie = null;
    private EditText mPassView;
    private View mProgressView;
    private View mLoginFormView;
    private UserLoginTask mAuthTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //onclick listener to register btn
        //if we press the register button we redirect to activity to register to data base
        final Button RegisterBnt=(Button) findViewById(R.id.registerBnt);
        RegisterBnt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);//move to the explition window
                startActivity(i);
            }
        });

        mUserView= (EditText) findViewById(R.id.username);
        mPassView=(EditText) findViewById(R.id.password);


        Button SignInButton = (Button) findViewById(R.id.sign_in_button);
        SignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * check to see if all required fields were fill,
     * else give foucs on the blank field
     */
    private void attemptLogin() {
        mUserView.setError(null);
        String user = mUserView.getText().toString();
        String pw=mPassView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check to check if user was input before clicking
        if (TextUtils.isEmpty(user)) {
            mUserView.setError(getString(R.string.error_field_required));
            focusView = mUserView;
            cancel = true;
        }
        //check to see if password was inpute before clicking
        if (TextUtils.isEmpty(pw)){
            mPassView.setError(getString(R.string.error_field_required));
            focusView=mPassView;
            cancel=true;
        }

        if (cancel) {
            focusView.requestFocus();//THE FIELD OF USER\password NAME WILL GET FOCUSE
        } else {
            View view = this.getCurrentFocus();
            if (view != null) {//hide the keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            showProgress(true);
            mAuthTask = new UserLoginTask(user,pw);//activate asyc commend of
            mAuthTask.execute();
        }
    }

    private void showProgress(boolean b) {
        if (b) {//hide the form and make progress bar visiable
            mLoginFormView.setVisibility(View.GONE);
            mProgressView.setVisibility(View.VISIBLE);
        }else {//reverese the rolls of first condition
            mLoginFormView.setVisibility(View.VISIBLE);
            mProgressView.setVisibility(View.GONE);
        }
    }


    public class UserLoginTask extends AsyncTask<Void, Void, User> {
        private final String user_name;
        private final  String password;


        UserLoginTask(String un,String pw) {
            user_name = un;
            password=pw;
        }
        @Override
        protected User doInBackground(Void... params) {///run in diff thread
            Log.i("doInBackground", Thread.currentThread().getName());//print the thread infomation
            try {
                URL url = new URL("http://10.0.2.2:8080//MyFormServlet?username="+this.user_name+"&password="+this.password);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();
                    String inputStr;
                    while ((inputStr = streamReader.readLine()) != null)
                        responseStrBuilder.append(inputStr);
                    JSONObject json = new JSONObject(responseStrBuilder.toString());
                    if ((!((boolean)json.get("exist"))|| (!((boolean)json.get("passwordRight"))))){
                      //user name dont exist or this isn't the correct password
                        return null;
                    }
                    return new User(json);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final User user) {
            Log.i("doInBackground", Thread.currentThread().getName());
            showProgress(false);

            if (user != null ) {
                SharedPreferences  sharedpreferences = getSharedPreferences("MyPrefs" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user",user.getUsername());
                editor.putString("password",user.getPassword());
                editor.commit();
                editor.apply();
                finish();
                Intent i=new Intent(LoginActivity.this,ChatActivity.class);//move to the explition window
                startActivity(i);

            } else
            {
                Toast.makeText(LoginActivity.this, R.string.incorrect_input, Toast.LENGTH_LONG).show();
            }
        }


        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

