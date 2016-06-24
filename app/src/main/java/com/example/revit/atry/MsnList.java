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
        JSONObject jObj = null;
      //  ArrayList<Messages> res = new ArrayList<Msg>();
        try {
            JSONArray jArr = json.getJSONArray("list");
            for (int i=0; i < jArr.length(); i++) {
                JSONObject obj = jArr.getJSONObject(i);
                String msg = obj.getString("msn");
                int id=obj.getInt("id");
                String user = obj.getString("user");
                String timestamp = obj.getString("timestmp");
                Messages m = new Messages(msg, timestamp,user,id);
                list.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
    }
    }
    /**
     * GetList
     * @return list of messages
     */
    public List<Messages> GetList() {
        return list;
    }
    /**
     * SetList - set new messages list.
     * @param l - list
     */
    public void SetList(List<Messages> l) {
        list = l;
    }
}
