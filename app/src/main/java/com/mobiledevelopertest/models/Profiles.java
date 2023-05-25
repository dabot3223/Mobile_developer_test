package com.mobiledevelopertest.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Profiles implements Serializable {
    JSONArray jsonArray;
    JSONObject jsonObject;
    String first_name,last_name,avatar;

    public Profiles(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public JSONObject getJsonObject(int i) throws JSONException {
        jsonObject = jsonArray.getJSONObject(i);
        return jsonObject;
    }

    public String getFirst_name(int i) throws JSONException {
        first_name = jsonArray.getJSONObject(i).getString("first_name");
        return first_name;
    }

    public String getLast_name(int i) throws JSONException {
        last_name = jsonArray.getJSONObject(i).getString("last_name");
        return last_name;
    }

    public String getAvatar(int i) throws JSONException {
        avatar = jsonArray.getJSONObject(i).getString("avatar");
        return avatar;
    }

    public int getSize() {
        return jsonArray.length();
    }
}
