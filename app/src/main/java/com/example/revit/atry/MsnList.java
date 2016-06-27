package com.example.revit.atry;

import com.example.revit.atry.Messages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;





/**
 *
 * Keep 10 last msn.
 */
public class MsnList {
    private List<Messages> list;

    /**
     *
     * @param json
     */
    public MsnList(JSONObject json) {
     list = new ArrayList<Messages>();
        try {
            //int firstId = json.getInt("first");
            int lastId = json.getInt("size");
            for (int i = 0; i < lastId; i++) {
                String obj = json.getString(Integer.toString(i));
                if (obj!=null){//we have an i post in the json object
                Messages m =  desrlizeMg( obj);
                list.add(m);}
            }
        } catch (JSONException e) {
            e.printStackTrace();
    }
    }

    /**
     *
     * @param json
     * * @return a deselize object of given json string
     */
    public Messages desrlizeMg(String json){
        try {
            JSONObject obj = new JSONObject(json);
            String msg = obj.getString("msn");
            int id=obj.getInt("id");
            String user = obj.getString("user");
            String timestamp = obj.getString("timestmp");
            return new Messages(msg, timestamp,user,id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * GetList
     * @return list of messages
     */
    public List<Messages> getList() {
        return list;
    }
    /**
     * SetList - set new messages list.
     * @param l - list
     */
    public void setList(List<Messages> l) {
        list = l;
    }
}
