package com.example.bipin.recyclervolley;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.bipin.recyclervolley.Constants.Constants;
import com.example.bipin.recyclervolley.adapters.MainAdapter;
import com.example.bipin.recyclervolley.pojos.User;
import com.example.bipin.recyclervolley.volley.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout pullToRef;
    private MainAdapter adapter;
    private ArrayList<User> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts = new ArrayList<>();

        pullToRef = findViewById(R.id.swipe_refresh_layout);
        pullToRef.setOnRefreshListener(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        adapter = new MainAdapter(this, posts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        jsonRequest();


    }

    private void jsonRequest() {
        enableProgress();

        JsonArrayRequest req = new JsonArrayRequest(Constants.ServerConstants.BASE_URL + Constants.ServerConstants.USERS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        String jsonOutput = response.toString();
                        Type listType = new TypeToken<ArrayList<User>>() {
                        }.getType();
                        posts = gson.fromJson(jsonOutput, listType);
                        adapter.updateData(posts);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                disableProgress();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        VolleySingleton.getInstance().getQueue().add(req);
    }


    private void enableProgress() {
        pullToRef.post(new Runnable() {
            @Override
            public void run() {
                pullToRef.setEnabled(true);
            }
        });
    }

    private void disableProgress() {
        pullToRef.post(new Runnable() {
            @Override
            public void run() {
                pullToRef.setEnabled(false);
            }
        });
    }


    @Override
    public void onRefresh() {
        enableProgress();
    }
}
