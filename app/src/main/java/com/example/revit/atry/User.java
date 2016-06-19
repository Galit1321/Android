package com.example.revit.atry;

import org.json.JSONException;
import org.json.JSONObject;

public class User{
    private String name;
    private String username;
    private String password;
    private String icon;
    private String email;

    public User(JSONObject object){
        try {
            this.name = object.getString("Name");
            this.password= object.getString("pass");
            this.icon=object.getString("icon");
            this.username=object.getString("username");
            this.email=object.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}