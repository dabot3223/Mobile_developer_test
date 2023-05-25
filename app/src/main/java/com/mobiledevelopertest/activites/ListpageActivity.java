package com.mobiledevelopertest.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mobiledevelopertest.R;
import com.mobiledevelopertest.adapters.ProfileListAdapter;
import com.mobiledevelopertest.models.Profiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ListpageActivity extends AppCompatActivity {

    int ct;
    RecyclerView recyclerView;
    Map<String,Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpage);

        ct = getIntent().getIntExtra("ct",0);

        getSupportActionBar().setTitle("Open counter : "+ct);

        recyclerView = findViewById(R.id.profilelist);

        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                ,"https://reqres.in/api/users",null, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            Profiles profiles = new Profiles(jsonArray);
                            ProfileListAdapter profileListAdapter = new ProfileListAdapter(profiles,ListpageActivity.this);
                            recyclerView.setAdapter(profileListAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(ListpageActivity.this));

                            //Log.d("pololo","got "+ profiles.getSize());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("pololo","error");
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}