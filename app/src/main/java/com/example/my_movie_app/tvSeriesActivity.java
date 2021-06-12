package com.example.my_movie_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tvSeriesActivity extends AppCompatActivity {

    private static final String TAG = "";
    private TextView result_text;
    private RequestQueue mQueue;
    private ImageView headImage;

    private RecyclerView rec;
    private RecyclerView recTrending;
    private RecyclerView recPopular;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImagesURLs = new ArrayList<>();

    private ArrayList<String> mNamesTrending = new ArrayList<>();
    private ArrayList<String> mImagesURLsTrending = new ArrayList<>();

    private ArrayList<String> mNamesPopular = new ArrayList<>();
    private ArrayList<String> mImagesURLsPopular = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_series);

        TextView textView = (TextView) findViewById(R.id.gotoDetailsTV);
        result_text = findViewById(R.id.mainHeadingTV);
        headImage = findViewById(R.id.openingImageViewTV);

        rec = findViewById(R.id.recyclerOneTV);
        recTrending = findViewById(R.id.recyclerTwoTV);
        recPopular = findViewById(R.id.recyclerThreeTV);

        mQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

    private void jsonParse(){
        String urlOne = "http://10.0.2.2:4000/tv-series/trending";
        String urlTwo = "http://10.0.2.2:4000/tv-series/top-rated";
        String urlThree = "http://10.0.2.2:4000/tv-series/popular";


        JsonObjectRequest requestOne = new JsonObjectRequest(Request.Method.GET, urlOne, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    for(int i=0;i<1;i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        String title = curr_movie.getString("title");
                        String imageURL = curr_movie.getString("imageURL");

                        Picasso.get().load(imageURL).into(headImage);

                        result_text.append(title);
                    }

                    for(int i=1;i<data.length();i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        String title = curr_movie.getString("title");
                        String imageURL = curr_movie.getString("imageURL");

                        mNames.add(title);
                        mImagesURLs.add(imageURL);
                    }

//                    initRecyclerView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        JsonObjectRequest requestTwo = new JsonObjectRequest(Request.Method.GET, urlTwo, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    for(int i=0;i<data.length();i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        String title = curr_movie.getString("title");
                        String imageURL = curr_movie.getString("imageURL");

                        mNamesTrending.add(title);
                        mImagesURLsTrending.add(imageURL);
                    }

//                    initRecyclerView_trending();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        JsonObjectRequest requestThree = new JsonObjectRequest(Request.Method.GET, urlThree, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    for(int i=0;i<data.length();i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        String title = curr_movie.getString("title");
                        String imageURL = curr_movie.getString("imageURL");

                        mNamesPopular.add(title);
                        mImagesURLsPopular.add(imageURL);
                    }

//                    initRecyclerView_popular();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(requestOne);
        mQueue.add(requestTwo);
        mQueue.add(requestThree);
    }

//    private void initRecyclerView(){
//        Log.d(TAG, "initRecyclerView: called.");
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        rec.setLayoutManager(layoutManager);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNames,mImagesURLs);
//        rec.setAdapter(adapter);
//
//    }
//
//    private void initRecyclerView_trending(){
//        Log.d(TAG, "initRecyclerViewTwo: called.");
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recTrending.setLayoutManager(layoutManager);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNamesTrending,mImagesURLsTrending);
//        recTrending.setAdapter(adapter);
//
//    }
//
//    private void initRecyclerView_popular(){
//        Log.d(TAG, "initRecyclerViewTwo: called.");
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recPopular.setLayoutManager(layoutManager);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNamesPopular,mImagesURLsPopular);
//        recPopular.setAdapter(adapter);
//
//    }
}