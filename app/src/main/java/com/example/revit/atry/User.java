package com.example.revit.atry;

import org.json.JSONException;
import org.json.JSONObject;

public class User{
    private String name;
    private String username;
    private String password;
    private String icon;
    private String email;


    public  User(){}
    public User(JSONObject object){
        try {
            this.name = object.getString("name");
            this.password= object.getString("password");
            this.icon=object.getString("icon");
            this.username=object.getString("user_name");
            this.email=object.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}