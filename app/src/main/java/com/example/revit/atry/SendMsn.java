package com.example.revit.atry;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/***
 * class to send the messge that this wrote
 */
public class SendMsn {
    private Post p;

    /**
     * constructor
     * @param item
     */
    public SendMsn(Post item){
        this.p=item;
    }

    /**
     * send this.p object to the servlet
     * in the web server that add to the database of massges
     */
    public void sendPost(){
        try{
        URL url = new URL("http://10.0.2.2:36182//RecMsnServlet?msn="+this.p.getMsn()+"&timeStmp="+this.p.getTimeStmp()
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
}
