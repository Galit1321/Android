package com.example.revit.atry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEmailView;
    private View mProgressView;
    private View mLoginFormView;
  //  private UserLoginTask mAuthTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

    /**
     * Keep the values and get out from page.
     */
    private void doneFunc() {
        boolean cancel = false;
        View focusView = null;

        //check if usernamse valid
        EditText username = (EditText) findViewById(R.id.username);
        if (username.getText().toString().length() == 0) {
            username.setError(getString(R.string.error_field_required));
            cancel = true;
        }
        //check if password is valid
        EditText password = (EditText) findViewById(R.id.password);
        if (password.getText().toString().length() == 0) {
            password.setError(getString(R.string.error_field_required));
            cancel = true;
        }
        //check if name is valid
        EditText name = (EditText) findViewById(R.id.name);
        if (name.getText().toString().length() == 0) {
            name.setError(getString(R.string.error_field_required));
           // cancel = true;
        }
        //check if email is valid.
        EditText email = (EditText) findViewById(R.id.email);
        if (email.getText().toString().length() == 0) {
            email.setError(getString(R.string.error_field_required));
            //cancel = true;
        }  else if (!isEmailValid(email.getText().toString())) {
            email.setError(getString(R.string.error_invalid_email));
            //cancel = true;
        }

        //check if choose icon.
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_reg);
        RadioButton lastRadioBtn = (RadioButton) findViewById(R.id.lastRB);
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            lastRadioBtn.setError(getString(R.string.error_no_icon));
            cancel = true;
        } else {
            lastRadioBtn.setError(null);
        }

        //if (cancel) {
        //    focusView.requestFocus();
      //  } //else {
          //  View view = this.getCurrentFocus();
            //if (view != null) {
              //  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            //}

//            showProgress(true);
      //      mAuthTask = new UserLoginTask(email);
    //        mAuthTask.execute();

  //      }

    }

    //check if email address valid
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private void showProgress(boolean b) {
        if (b) {
            mLoginFormView.setVisibility(View.GONE);
            mProgressView.setVisibility(View.VISIBLE);
        }else {
            mLoginFormView.setVisibility(View.VISIBLE);
            mProgressView.setVisibility(View.GONE);
        }
    }

   /* public class UserLoginTask extends AsyncTask<Void, Void, User> {
        private final String mEmail;
        UserLoginTask(String email) {
            mEmail = email;
        }*/
//        @Override
        /*protected User doInBackground(Void... params) {
            Log.i("doInBackground", Thread.currentThread().getName());
            try {
                URL url = new URL("http://www.google.com/");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();

                    String inputStr;
                    while ((inputStr = streamReader.readLine()) != null)
                        responseStrBuilder.append(inputStr);

                    JSONObject json = new JSONObject(responseStrBuilder.toString());

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
        }*/

}

