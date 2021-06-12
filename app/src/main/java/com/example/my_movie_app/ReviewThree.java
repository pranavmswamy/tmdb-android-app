package com.example.my_movie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReviewThree extends AppCompatActivity {

    TextView tv3, show1_rating, show1_username;
    RequestQueue mQueue;
    String content, username, rating, formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_three);

        tv3 = (TextView) findViewById(R.id.tv3);
        show1_rating = (TextView) findViewById(R.id.show1_rating);
        show1_username = (TextView) findViewById(R.id.show1_username);

        Bundle bundle = getIntent().getExtras();


        if(bundle != null) {
            if (bundle.getString("content") != null) {
                //do something, we receive the keyword here.
                content = bundle.getString("content");

                String dateCreatedUser1 = bundle.getString("created_at");
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy", Locale.ENGLISH);

                LocalDate date1 = LocalDate.parse(dateCreatedUser1, inputFormatter);
                formattedDate = outputFormatter.format(date1);

                username = bundle.getString("username");
                rating = bundle.getString("rating");

            }
        }

        tv3.setText(content);
        show1_rating.setText(rating);
        show1_username.setText("by " + username + " on " + formattedDate);
    }


}