package com.example.revit.atry;

/**
 * Post class - keep the messages.
 */
public class Messages {
    private String msn;
    private String timeStmp;
    private String user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public Messages(String msn, String timeStmp, String user,int id) {
        this.msn = msn;
        this.id=id;
        this.timeStmp = timeStmp;
        this.user = user;
    }

    public Messages() {
    }

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
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



}
