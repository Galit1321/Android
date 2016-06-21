package com.example.revit.atry;

/**
 * Created by גליתונופר on 21/06/2016.
 */
public class Post {
    private String msn;
    private String timeStmp;

    public String getTimeStmp() {
        return timeStmp;
    }

    public void setTimeStmp(String timeStmp) {
        this.timeStmp = timeStmp;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;
    private int num;
}
