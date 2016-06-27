package com.example.revit.atry;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEmailView;
    private View mProgressView;
    private View mLoginFormView;
    private UserRegisterTask mAuthTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        //listener for done button.
        Button doneButton = (Button) findViewById(R.id.done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneFunc();
            }
        });
        //listener for reset button.
        Button resetButton = (Button) findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFunc();
            }
        });
    }

    /**
     * clean all the page
     */
    private void resetFunc() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    /***
     *  hide or show the progress bar according to arg b
     * @param b that indicate if
     *          there is need to show progress bar
     */
    private void showProgress(boolean b) {

        if (b) {//hide the form and make progress bar visiable
            mLoginFormView.setVisibility(View.GONE);
            mProgressView.setVisibility(View.VISIBLE);
        }else {//reverese the rolls of first condition
            mLoginFormView.setVisibility(View.VISIBLE);
            mProgressView.setVisibility(View.GONE);
        }
    }

    /**
     * Keep the values and get out from page.
     */
    private void doneFunc() {
        User user = new User();
        boolean cancel = false;
        View focusView = null;

        //check if usernamse valid
        EditText username = (EditText) findViewById(R.id.username);
        if (username.getText().toString().length() == 0) {
            username.setError(getString(R.string.error_field_required));
            cancel = true;
            focusView = username;
        } else {
            user.setUsername(username.getText().toString());
        }
        //check if password is valid
        EditText password = (EditText) findViewById(R.id.password);
        if (password.getText().toString().length() == 0) {
            password.setError(getString(R.string.error_field_required));
            cancel = true;
            focusView = password;
        } else {
            user.setPassword(password.getText().toString());
        }
        //check if name is valid
        EditText name = (EditText) findViewById(R.id.name);
        if (name.getText().toString().length() == 0) {
            name.setError(getString(R.string.error_field_required));
            cancel = true;
            focusView = name;
        } else {
            user.setName(name.getText().toString());
        }
        //check if email is valid.
        EditText email = (EditText) findViewById(R.id.email);
        if (email.getText().toString().length() == 0) {
            email.setError(getString(R.string.error_field_required));
            cancel = true;
            focusView = email;
        } else if (!isEmailValid(email.getText().toString())) {
            email.setError(getString(R.string.error_invalid_email));
            cancel = true;
            focusView = email;
        } else {
            user.setEmail(email.getText().toString());
        }
        user.setIcon("ddd");
        //check if choose icon.
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_reg);
        RadioButton lastRadioBtn = (RadioButton) findViewById(R.id.lastRB);
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            lastRadioBtn.setError(getString(R.string.error_no_icon));
            cancel = true;
        } else {
            lastRadioBtn.setError(null);
        }

        if (cancel) {
            focusView.requestFocus();//get focus on empty field
        } else {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            showProgress(true);
            mAuthTask = new UserRegisterTask(user);
            mAuthTask.execute();

        }

    }

    //check if email address valid
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }


    /***
     * inner class that
     */
    public class UserRegisterTask extends AsyncTask<Void, Void,String> {
        private final User user;
        UserRegisterTask(User u) {
            user=u;
        }

        @Override
        protected String doInBackground(Void... params) {
            Log.i("doInBackground", Thread.currentThread().getName());
            try {//Change to 8080 becuase 8080 dont works in my netbeans
                URL url = new URL("http://advprog.cs.biu.ac.il:8080/Ex4web/SubscribeServlet?username="+this.user.getUsername()+"&password="+this.user.getPassword()
                        +"&email="+user.getEmail()+"&icon="+user.getIcon()+"&name="+user.getName());
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

                    return json.getString("works");
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
        protected void onPostExecute(final String userRec) {
            Log.i("doInBackground", Thread.currentThread().getName());
            if (userRec.equals("yes")){
                finish();
                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);//move to the explition window
                startActivity(i);

            }
            else
            {
               Toast.makeText(RegisterActivity.this, R.string.Fail, Toast.LENGTH_LONG).show();
            }
        }


        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }

    }

}

