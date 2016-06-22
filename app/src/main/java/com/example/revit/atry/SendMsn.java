package com.example.revit.atry;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;


/***
 * class to send the messge that this wrote
 */
public class SendMsn extends AsyncTask<Void, Void, String> {
    private Messages p;

    /**
     * constructor
     * @param item
     */
    public SendMsn(Messages item){
        this.p=item;
    }

    /**
     * send this.p object to the servlet
     * in the web server that add to the database of massges
     */
    public void sendPost(){
        try{
        URL url = new URL("http://10.0.2.2:8080//RecMsnServlet?msn="+this.p.getMsn()+"&timeStmp="+this.p.getTimeStmp()
                +"&user"+this.p.getUser());
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL("http://10.0.2.2:8080//RecMsnServlet?msn=" + this.p.getMsn() + "&timeStmp=" + this.p.getTimeStmp()
                    + "&user" + this.p.getUser());
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
        }return null;
    }

    @Override
    protected void onPostExecute(final String userRec) {
        Log.i("doInBackground", Thread.currentThread().getName());
        if (userRec.equals("yes")){
            //finish();


        }
        else {
        }
    }
}
