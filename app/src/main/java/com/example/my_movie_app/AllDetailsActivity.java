package com.example.my_movie_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout main_relative_detailed;
    private  RelativeLayout progress_relative_detailed;
    public CardView card1, card2, card3;
    public CardView cd1,cd2,cd3,cd4,cd5,cd6,cd7,cd8,cd9,cd10;
    String TAG = "";
    RequestQueue mQueue;
    private String mParam1, mParam2;
    public String type = "";
    public String hello = " ";
    public String movieId = "527774";
    public String ytid="";
    public String ytid_tv="";
    public int id;
    TextView recommended, heading, overview, gen, n1,n2,n3,n4,n5,n6,r1,r2,r3, over, review_heading;
    TextView rating1,rating2, rating3, name1, name2, name3, recommended_picks;
    ImageView rec1, rec2, rec3, rec4, rec5, rec6, rec7, rec8, rec9, rec10, iv;
    CircleImageView c1,c2,c3,c4,c5,c6;
    String Deets1Str = "";
    public JSONObject Deets1Res, current, current_tv;
    YouTubePlayerView yt;
    String str = "";
    ImageView facebook, twitter, watchlist, image_viewer, remove_watchlist;
    TextView txtYear;

//    public String URLL = "http://localhost:4000/movies/details?movieId=527774";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);

//        if(getArguments() != null)

System.out.print("createeee");

        Bundle bundle = getIntent().getExtras();
        System.out.println("BUNDLE"+bundle);
        if(bundle != null) {
            if (bundle.getString("media_type") != null) {
                //do something, we receive the keyword here.
                type = bundle.getString("media_type");
                id = bundle.getInt("id");
                System.out.print("****" + type + "" + id);
                // Log.i("TTTTTTTTTT",hello);
                //over.setText(""+id);
            }
        }

        main_relative_detailed = findViewById(R.id.main_relative_detailed);
        progress_relative_detailed = findViewById(R.id.progress_relative_detailed);

        over = (TextView) findViewById(R.id.over);
        card1 = (CardView) findViewById(R.id.review1);
        card2 = (CardView) findViewById(R.id.review2);
        card3 = (CardView) findViewById(R.id.review3);
        recommended = (TextView) findViewById(R.id.rec);
        rec1 = (ImageView) findViewById(R.id.rec1);
        rec2 = (ImageView) findViewById(R.id.rec2);
        rec3 = (ImageView) findViewById(R.id.rec3);
        rec4 = (ImageView) findViewById(R.id.rec4);
        rec5 = (ImageView) findViewById(R.id.rec5);
        rec6 = (ImageView) findViewById(R.id.rec6);
        rec7 = (ImageView) findViewById(R.id.rec7);
        rec8 = (ImageView) findViewById(R.id.rec8);
        rec9 = (ImageView) findViewById(R.id.rec9);
        rec10 = (ImageView) findViewById(R.id.rec10);
        heading = (TextView) findViewById(R.id.heading);
        overview = (TextView) findViewById(R.id.overview);
        yt = (YouTubePlayerView) findViewById(R.id.yt);
        gen = (TextView) findViewById(R.id.gen);
        c1 = (CircleImageView) findViewById(R.id.c1);
        c2 = (CircleImageView) findViewById(R.id.c2);
        c3 = (CircleImageView) findViewById(R.id.c3);
        c4 = (CircleImageView) findViewById(R.id.c4);
        c5 = (CircleImageView) findViewById(R.id.c5);
        c6 = (CircleImageView) findViewById(R.id.c6);
        n1 = (TextView) findViewById(R.id.n1);
        n2 = (TextView) findViewById(R.id.n2);
        n3 = (TextView) findViewById(R.id.n3);
        n4 = (TextView) findViewById(R.id.n4);
        n5 = (TextView) findViewById(R.id.n5);
        n6 = (TextView) findViewById(R.id.n6);
        r1 = (TextView) findViewById(R.id.r1);
        r2 = (TextView) findViewById(R.id.r2);
        r3 = (TextView) findViewById(R.id.r3);
        iv = (ImageView) findViewById(R.id.iv);
        facebook = (ImageView) findViewById(R.id.facebook);
        twitter = (ImageView) findViewById(R.id.iv);
        watchlist = (ImageView) findViewById(R.id.watchlist);
        image_viewer = (ImageView) findViewById(R.id.image_viewer);
        remove_watchlist = (ImageView) findViewById(R.id.remove_watchlist);
        review_heading = (TextView) findViewById(R.id.review_heading);
        rating1 = (TextView) findViewById(R.id.rating1);
        rating2 = (TextView) findViewById(R.id.rating2);
        rating3 = (TextView) findViewById(R.id.rating3);
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        cd1 = (CardView) findViewById(R.id.card1);
        cd2 = (CardView) findViewById(R.id.card2);
        cd3 = (CardView) findViewById(R.id.card3);
        cd4 = (CardView) findViewById(R.id.card4);
        cd5 = (CardView) findViewById(R.id.card5);
        cd6 = (CardView) findViewById(R.id.card6);
        cd7 = (CardView) findViewById(R.id.card7);
        cd8 = (CardView) findViewById(R.id.card8);
        cd9 = (CardView) findViewById(R.id.card9);
        cd10 = (CardView) findViewById(R.id.card10);
        recommended_picks = (TextView) findViewById(R.id.recommended_picks) ;
        getLifecycle().addObserver(yt);

        txtYear = findViewById(R.id.txtYear);


//        BottomNavigationView navView = findViewById(R.id.mainnavbar);
//        navView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
//        openFragment(home_main.newInstance("", ""));


        SharedPreferences sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.contains(String.valueOf(id)))
        {
            remove_watchlist.setVisibility(View.VISIBLE);
            watchlist.setVisibility(View.GONE);
        }
        else
        {
            remove_watchlist.setVisibility(View.GONE);
            watchlist.setVisibility(View.VISIBLE);
        }







        //String URLL1 = "http://10.0.2.2:4000/movies/recommended?movieId="+id;

//            over.setText(URLL1);




            getRecommended(); //done for tv also
            getDetails(); //done for tv also
            getCast();
            getReviews();



        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        facebook.setOnClickListener(this);
        twitter.setOnClickListener(this);
        watchlist.setOnClickListener(this);
        remove_watchlist.setOnClickListener(this);
        //rec1.setOnClickListener(this);



    }

//    @Override
//    public void onBackPressed(){
//        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
//            finish();
//        }
//        else {
//            super.onBackPressed();
//        }
//    }
//
//    public void openFragment(Fragment fragment) {
//        String backStateName = fragment.getClass().getName();
//        FragmentManager manager = getSupportFragmentManager();
//        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
//        if(!fragmentPopped){
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container, fragment);
//            transaction.addToBackStack(backStateName);
//            transaction.commit();
//        }
//
//        TextView textView = (TextView)findViewById(R.id.gotoDetails);
//    }
//
//    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    switch (item.getItemId()) {
//                        case R.id.navigation_home:
//                            openFragment(home_main.newInstance("", ""));
//                            return true;
//                        case R.id.navigation_search:
//                            openFragment(search.newInstance("", ""));
//                            return true;
//                        case R.id.navigation_favourites:
//                            openFragment(favorite.newInstance("", ""));
//                            return true;
//                    }
//                    return false;
//                }
//            };

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("INSIDE START");
    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {
            case R.id.review1 :
                i = new Intent(this, ReviewOne.class);
                startActivity(i);
                break;

            case R.id.review2 :
                i = new Intent(this, ReviewTwo.class);
                startActivity(i);
                break;

            case R.id.review3 :
                i = new Intent(this, ReviewThree.class);
                startActivity(i);
                break;

            case R.id.facebook :
                String typee="";
                String share_text="";
                typee="https://www.themoviedb.org/movie/"+id;

//                if(type == "MOVIE"){
//                    typee="https://www.themoviedb.org/movie/"+id;
//                }else{
//                    typee="https://www.themoviedb.org/tv/"+id;
//                }
                share_text="Check this out!";
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/sharer.php?u="+typee+"&t="+share_text));
                startActivity(i);
                break;

            case R.id.iv :  //twitter
                String typee1="";
                String share_text1="";
                typee1="https://www.themoviedb.org/movie/"+id;
//                if(item_data.get(position).getMovie()){
//                    type="https://www.themoviedb.org/movie/"+item_data.get(position).getMovieID();
//                }else{
//                    type="https://www.themoviedb.org/tv/"+item_data.get(position).getMovieID();
//                }
                share_text="Check this out! ";
                i= new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text="+share_text+"&url="+typee1));
                startActivity(i);
                break;

            case  R.id.watchlist :

                add_to_watchlist(); //done for tv also
                break;
                
            case R.id.remove_watchlist :
                remove_from_watchlist();
                break;
//
//            case  R.id.rec1 :
//
//                i = new Intent(this, AllDetailsActivity.class);
//                i.putExtra("id", id);
//                i.putExtra("media_type", type);
//                startActivity(i);
//                break;

        }

    }

    public void remove_from_watchlist() {

        final SharedPreferences sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if (type.equalsIgnoreCase("MOVIE")) {

            System.out.print("Removing from watchlist!");
            String URLL = "http://10.0.2.2:4000/movies/details?movieId=" + id;

            mQueue = Volley.newRequestQueue(this);

            JsonObjectRequest requestTwo = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        current = response.getJSONObject("data");
                        //        String title = item_data.get(position).getTitle();
//        int movieID = item_data.get(position).getMovieID();
//        notifyDataSetChanged();
//        editor.remove(String.valueOf(movieID));
//        editor.commit();
//        Toast.makeText(mContext, title + " was removed from Watchlist", Toast.LENGTH_SHORT).show();
                        String w_title = current.getString("title");
                        int w_id = id;
                        if(sharedPreferences.contains(String.valueOf(id))) {
                            editor.remove(String.valueOf(id));
                            editor.commit();
                        }

                        Toast.makeText(getApplicationContext(), w_title + " was removed from Watchlist", Toast.LENGTH_LONG).show();

                        watchlist.setVisibility(View.VISIBLE);
                        remove_watchlist.setVisibility(View.GONE);


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
            requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestTwo);
        }

        else if (type.equalsIgnoreCase("TV"))
        {

            System.out.print("Adding to watchlist!");
            String URLL = "http://10.0.2.2:4000/tv-series/details?tvId="+id;

            mQueue = Volley.newRequestQueue(this);

            JsonObjectRequest requestTwo = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        current_tv = response.getJSONObject("data");
                        String w_title = current_tv.getString("title");
                        int w_id = id;
                        if (sharedPreferences.contains(String.valueOf(id))) {
                            editor.remove(String.valueOf(id));
                            editor.commit();
                        }
                        Toast.makeText(getApplicationContext(), w_title + " was removed from Watchlist", Toast.LENGTH_LONG).show();

                        watchlist.setVisibility(View.VISIBLE);
                        remove_watchlist.setVisibility(View.GONE);

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
            requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestTwo);
        }

    }

    public void add_to_watchlist() {
        SharedPreferences sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if (type.equalsIgnoreCase("MOVIE")) {

            System.out.print("Adding to watchlist!");
            String URLL = "http://10.0.2.2:4000/movies/details?movieId=" + id;

            mQueue = Volley.newRequestQueue(this);

            JsonObjectRequest requestTwo = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        current = response.getJSONObject("data");
                        String w_title = current.getString("title");
                        String w_imageURL = current.getString("imageURL");
                        int w_id = id;
                        String w_release = current.getString("release_date").substring(0, 4);
                        boolean isMovieOrTV;
                        if (type == "MOVIE") {
                            isMovieOrTV = false;
                        } else {
                            isMovieOrTV = true;
                        }

                        String w_info = "@split@" + w_title + "@split@" + w_imageURL + "@split@" + w_id + "@split@" + w_release + "@split@" + isMovieOrTV;
                        editor.putString(String.valueOf(id), w_info);
//
                        editor.commit();

                        Toast.makeText(getApplicationContext(), w_title + " was added to Watchlist", Toast.LENGTH_LONG).show();

                        watchlist.setVisibility(View.GONE);
                        remove_watchlist.setVisibility(View.VISIBLE);


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
            requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestTwo);
        }

        else if (type.equalsIgnoreCase("TV"))
        {
//            SharedPreferences sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
//            final SharedPreferences.Editor editor = sharedPreferences.edit();
            System.out.print("Adding to watchlist!");
            String URLL = "http://10.0.2.2:4000/tv-series/details?tvId="+id;

            mQueue = Volley.newRequestQueue(this);

            JsonObjectRequest requestTwo = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        current_tv = response.getJSONObject("data");
                        String w_title = current_tv.getString("title");
                        String w_imageURL = current_tv.getString("imageURL");
                        int w_id = id;
                        String w_release = current_tv.getString("releaseDate").substring(0, 4);
                        boolean isMovieOrTV = false;
                        if (type.equalsIgnoreCase("MOVIE")) {
                            isMovieOrTV = true;

                        } else if (type.equalsIgnoreCase("TV")){
                            isMovieOrTV = false;
                        }

                        String w_info_tv = "@split@" + w_title + "@split@" + w_imageURL + "@split@" + w_id + "@split@" + w_release + "@split@" + isMovieOrTV;
                        editor.putString(String.valueOf(id), w_info_tv);
//
                        editor.commit();

                        Toast.makeText(getApplicationContext(), w_title + " was added to Watchlist", Toast.LENGTH_LONG).show();

                        watchlist.setVisibility(View.GONE);
                        remove_watchlist.setVisibility(View.VISIBLE);


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
            requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestTwo);
        }
    }

    public void getRecommended() {
        if (type.equalsIgnoreCase("MOVIE")) {
            Log.i(TAG, "STR 3:Inside function");
            mQueue = Volley.newRequestQueue(this);
            String URLL = "http://10.0.2.2:4000/movies/recommended?movieId=" + id;

            JsonObjectRequest requestOne = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //JSONObject rec_result = response;
                    //Log.i(TAG, "STR 3:"+rec_result);

                    try {
                        JSONArray data = response.getJSONArray("data");
//                    for (int i = 0; i < 1; i++) {
                        if(data.length() == 0)
                        {
                            cd1.setVisibility(View.GONE);
                            cd2.setVisibility(View.GONE);
                            cd3.setVisibility(View.GONE);
                            cd4.setVisibility(View.GONE);
                            cd5.setVisibility(View.GONE);
                            cd6.setVisibility(View.GONE);
                            cd7.setVisibility(View.GONE);
                            cd8.setVisibility(View.GONE);
                            cd9.setVisibility(View.GONE);
                            cd10.setVisibility(View.GONE);
                            recommended_picks.setVisibility(View.GONE);
                        }
                        else {
                            final JSONObject curr_movie = data.getJSONObject(0);
//                    recommended.setText(curr_movie.getString("title"));
                            Glide.with(rec1.getContext()).load(curr_movie.getString("imageURL")).into(rec1);

                            final JSONObject curr_movie1 = data.getJSONObject(1);
                            Glide.with(rec2.getContext()).load(curr_movie1.getString("imageURL")).into(rec2);

                            final JSONObject curr_movie2 = data.getJSONObject(2);
                            Glide.with(rec3.getContext()).load(curr_movie2.getString("imageURL")).into(rec3);

                            final JSONObject curr_movie3 = data.getJSONObject(3);
                            Glide.with(rec4.getContext()).load(curr_movie3.getString("imageURL")).into(rec4);

                            final JSONObject curr_movie4 = data.getJSONObject(4);
                            Glide.with(rec5.getContext()).load(curr_movie4.getString("imageURL")).into(rec5);

                            final JSONObject curr_movie5 = data.getJSONObject(5);
                            Glide.with(rec6.getContext()).load(curr_movie5.getString("imageURL")).into(rec6);

                            final JSONObject curr_movie6 = data.getJSONObject(6);
                            Glide.with(rec7.getContext()).load(curr_movie6.getString("imageURL")).into(rec7);

                            final JSONObject curr_movie7 = data.getJSONObject(7);
                            Glide.with(rec8.getContext()).load(curr_movie7.getString("imageURL")).into(rec8);

                            final JSONObject curr_movie8 = data.getJSONObject(8);
                            Glide.with(rec9.getContext()).load(curr_movie8.getString("imageURL")).into(rec9);

                            final JSONObject curr_movie9 = data.getJSONObject(9);
                            Glide.with(rec10.getContext()).load(curr_movie9.getString("imageURL")).into(rec10);


//                    Glide.with(iv.getContext()).load("https://img.icons8.com/fluent/48/000000/twitter.png").into(iv);
//                    }

                            rec1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });

                            rec2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie1.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie1.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });


                            rec3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie2.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie2.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });


                            rec4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie3.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie3.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });


                            rec5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie4.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie4.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });


                            rec6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie5.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie5.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });


                            rec7.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie6.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie6.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });


                            rec8.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie7.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie7.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });


                            rec9.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie8.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie8.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            });


                            rec10.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                    try {
                                        intent.putExtra("id", Integer.parseInt(curr_movie9.getString("id")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("media_type", curr_movie9.getString("category").toUpperCase());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);


                                }
                            });
                        }



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


            requestOne.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestOne);
        }

        else if (type.equalsIgnoreCase("TV"))
        {
            mQueue = Volley.newRequestQueue(this);
            String URLL = "http://10.0.2.2:4000/tv-series/recommended?tvId=" + id;

            JsonObjectRequest requestOne = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //JSONObject rec_result = response;
                    //Log.i(TAG, "STR 3:"+rec_result);

                    try {
                        JSONArray data_tv = response.getJSONArray("data");
//                    for (int i = 0; i < 1; i++) {
                        final JSONObject curr_show = data_tv.getJSONObject(0);
//                    recommended.setText(curr_movie.getString("title"));
                        Glide.with(rec1.getContext()).load(curr_show.getString("imageURL")).into(rec1);

                        final JSONObject curr_movie1 = data_tv.getJSONObject(1);
                        Glide.with(rec2.getContext()).load(curr_movie1.getString("imageURL")).into(rec2);

                        final JSONObject curr_movie2 = data_tv.getJSONObject(2);
                        Glide.with(rec3.getContext()).load(curr_movie2.getString("imageURL")).into(rec3);

                        final JSONObject curr_movie3 = data_tv.getJSONObject(3);
                        Glide.with(rec4.getContext()).load(curr_movie3.getString("imageURL")).into(rec4);

                        final JSONObject curr_movie4 = data_tv.getJSONObject(4);
                        Glide.with(rec5.getContext()).load(curr_movie4.getString("imageURL")).into(rec5);

                        final JSONObject curr_movie5 = data_tv.getJSONObject(5);
                        Glide.with(rec6.getContext()).load(curr_movie5.getString("imageURL")).into(rec6);

                        final JSONObject curr_movie6 = data_tv.getJSONObject(6);
                        Glide.with(rec7.getContext()).load(curr_movie6.getString("imageURL")).into(rec7);

                        final JSONObject curr_movie7 = data_tv.getJSONObject(7);
                        Glide.with(rec8.getContext()).load(curr_movie7.getString("imageURL")).into(rec8);

                        final JSONObject curr_movie8 = data_tv.getJSONObject(8);
                        Glide.with(rec9.getContext()).load(curr_movie8.getString("imageURL")).into(rec9);

                        final JSONObject curr_movie9 = data_tv.getJSONObject(9);
                        Glide.with(rec10.getContext()).load(curr_movie9.getString("imageURL")).into(rec10);

//                    Glide.with(iv.getContext()).load("https://img.icons8.com/fluent/48/000000/twitter.png").into(iv);
//                    }

                        rec1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_show.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_show.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });

                        rec2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie1.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie1.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });


                        rec3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie2.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie2.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });


                        rec4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie3.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie3.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });


                        rec5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie4.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie4.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });


                        rec6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie5.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie5.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });


                        rec7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie6.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie6.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });


                        rec8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie7.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie7.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });


                        rec9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie8.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie8.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });


                        rec10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


//
                                Intent intent = new Intent(getApplicationContext(), AllDetailsActivity.class);
                                try {
                                    intent.putExtra("id", Integer.parseInt(curr_movie9.getString("id")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    intent.putExtra("media_type", curr_movie9.getString("category").toUpperCase());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });




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


            requestOne.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestOne);

        }
    }

//    @Override
//    protected void onNewIntent(final Intent intent) {
//        super.onNewIntent(intent);
//        this.setIntent(intent);
//    }

    public void getDetails()
    {
        if ((type.equalsIgnoreCase("MOVIE")))
        {
            System.out.println("uuuu" + id);
            String URLL = "http://10.0.2.2:4000/movies/details?movieId=" + id;

            System.out.println("uuuu" + URLL);

            mQueue = Volley.newRequestQueue(this);

            JsonObjectRequest requestTwo = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //JSONObject rec_result = response;
                    //Log.i(TAG, "STR 3:"+rec_result);

                    try {
                        current = response.getJSONObject("data");
                        heading.setText(current.getString("title"));
                        overview.setText(current.getString("overview"));
                        System.out.print("-----" + current.getString("overview"));
                        final JSONObject videodetails = current.getJSONObject("video_details");
                        ytid = videodetails.getString("video_id");
                        String year="";
                        try{
                            year = current.getString("release_date");
                            Log.e("This is the year", year);
                            if(year!=null && year.length() > 0) {
                                txtYear.setText(year.substring(0, 4));
                            }
                            else {
                                txtYear.setText("--");
                            }
                        }catch(Exception e){
                            txtYear.setText("--");
                        }

//                        txtYear.setText(current.getString("releaseDate").substring(0, 4));

                        System.out.println("HAAH"+ytid);
//                    over.setText(ytid);

                        // yt.addYouTubePlayerListener(new AbstractYouTubePlayerListener()

                        JSONArray movie_genres = current.getJSONArray("genres");

                        for (int i = 0; i < movie_genres.length(); i++) {
                            str += ", " + movie_genres.get(i).toString();
                        }
                        str = str.substring(2);


                        System.out.println("now!" + ytid);
                        gen.setText(str);
                        if (ytid.equals("tzkWB85ULJY"))
                        {
                            yt.setVisibility(View.GONE);
                            image_viewer.setVisibility(View.VISIBLE);
                            Glide.with(image_viewer.getContext()).load("https://image.tmdb.org/t/p/w780/"+current.getString("backdrop_path")).into(image_viewer);

                        }
                        else if (ytid.equals(""))
                        {
                            yt.setVisibility(View.GONE);
                            image_viewer.setVisibility(View.VISIBLE);
                            Glide.with(image_viewer.getContext()).load("https://image.tmdb.org/t/p/w780/"+current.getString("backdrop_path")).into(image_viewer);
                        }
                        else{
                            yt.setVisibility(View.VISIBLE);
                            image_viewer.setVisibility(View.GONE);
                        }



//                    yt.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                        @Override
//                        public void onApiChange(YouTubePlayer youTubePlayer) {
//                            super.onApiChange(youTubePlayer);
//                           System.out.println("rrr");
//
//                        }
//                    });


                        yt.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(YouTubePlayer youTubePlayer) {
                                super.onReady(youTubePlayer);
//                                youTubePlayer.pause();
                                Log.e("Before youtube loads","abcd");
                                youTubePlayer.cueVideo(ytid, 0);
                                Log.e("After youtube loads","abcd");

                            }
                        });
                        Log.e("Before showing details","abcd");
                        progress_relative_detailed.setVisibility(View.GONE);
                        main_relative_detailed.setVisibility(View.VISIBLE);
                        Log.e("After showing details","abcd");

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


            requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestTwo);

            System.out.println("hiii");


            System.out.println("bye");


        }

        else if ((type.equalsIgnoreCase("TV")))
        {
            String URLL = "http://10.0.2.2:4000/tv-series/details?tvId=" + id;

            System.out.println("uuuu" + URLL);

            mQueue = Volley.newRequestQueue(this);

            JsonObjectRequest requestTwo = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //JSONObject rec_result = response;
                    //Log.i(TAG, "STR 3:"+rec_result);

                    try {
                        current = response.getJSONObject("data");
                        heading.setText(current.getString("title"));
                        overview.setText(current.getString("overview"));
                        final JSONObject videodetails_tv = current.getJSONObject("video_details");
                        ytid_tv = videodetails_tv.getString("video_id");
//                        System.out.println("HAAH"+ytid);
//                        String year = current.getString("releaseDate");
                        String year="";
                        try{
                            year = current.getString("releaseDate");
                            Log.e("This is the year", year);
                            System.out.println(year);
                            if(year!=null && year.length() > 0) {
                                txtYear.setText(year.substring(0, 4));
                            }
                            else {
                                txtYear.setText("--");
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                            txtYear.setText("--");
                        }
//                        txtYear.setText(current.getString("releaseDate").substring(0, 4));


                        System.out.print("-----" + current.getString("overview"));

                        if (ytid_tv.equals("tzkWB85ULJY"))
                        {
                            yt.setVisibility(View.GONE);
                            image_viewer.setVisibility(View.VISIBLE);
                            Glide.with(image_viewer.getContext()).load("https://image.tmdb.org/t/p/w780/"+current.getString("backdrop_path")).into(image_viewer);

                        }
                        else if (ytid_tv.equals(""))
                        {
                            yt.setVisibility(View.GONE);
                            image_viewer.setVisibility(View.VISIBLE);
                            Glide.with(image_viewer.getContext()).load("https://image.tmdb.org/t/p/w780/"+current.getString("backdrop_path")).into(image_viewer);
                        }
                        else{
                            yt.setVisibility(View.VISIBLE);
                            image_viewer.setVisibility(View.GONE);
                        }

                        yt.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(YouTubePlayer youTubePlayer) {
                                super.onReady(youTubePlayer);
//                                youTubePlayer.pause();
                                youTubePlayer.cueVideo(ytid_tv, 0);

                            }
                        });

//                        Glide.with(mContext).asBitmap().load(item_data.get(position).getImageURL()).into(holder.image);
                        
//                        Glide.with(image_viewer.getContext()).load(current.getString("imageURL")).into(image_viewer);
//                        image_viewer.setVisibility(image_viewer.VISIBLE);
//                        yt.setVisibility(View.GONE);

                        // making progress bar invisible
                        progress_relative_detailed.setVisibility(View.GONE);
                        main_relative_detailed.setVisibility(View.VISIBLE);


//                        yt.removeYouTubePlayerListener()
//                        yt.setVisibility(yt.gone);
//                        yt.setVisibility(yt.gone);

                        //final JSONObject videodetails = current.getJSONObject("video_details");
                        //ytid = videodetails.getString("video_id");
//                    over.setText(ytid);

                        // yt.addYouTubePlayerListener(new AbstractYouTubePlayerListener()

                        JSONArray movie_genres = current.getJSONArray("genres");

                        for (int i = 0; i < movie_genres.length(); i++) {
                            str +=  ", " + movie_genres.get(i).toString();
                        }
                        str = str.substring(2);

                        //System.out.println("now!" + ytid);
                        gen.setText(str);

//                    yt.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                        @Override
//                        public void onApiChange(YouTubePlayer youTubePlayer) {
//                            super.onApiChange(youTubePlayer);
//                           System.out.println("rrr");
//
//                        }
//                    });


//                        yt.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                            @Override
//                            public void onReady(YouTubePlayer youTubePlayer) {
//                                super.onReady(youTubePlayer);
//
//                                youTubePlayer.loadVideo(ytid, 0);
//                                youTubePlayer.pause();
//
//                            }
//                        });

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


            requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestTwo);

        }
    }


    public void getCast()
    {

        if(type.equalsIgnoreCase("MOVIE")) {

            mQueue = Volley.newRequestQueue(this);
            String URLL = "http://10.0.2.2:4000/movies/cast?movieId=" + id;

            JsonObjectRequest requestThree = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //JSONObject rec_result = response;
                    //Log.i(TAG, "STR 3:"+rec_result);

                    try {
                        JSONArray cast_data = response.getJSONArray("data");
//                    for (int i = 0; i < 6; i++) {
                        JSONObject curr_cast = cast_data.getJSONObject(0);
                        Glide.with(c1.getContext()).load(curr_cast.getString("imageURL")).into(c1);
                        n1.setText(curr_cast.getString("name"));

                        JSONObject curr_cast2 = cast_data.getJSONObject(1);
                        Glide.with(c2.getContext()).load(curr_cast2.getString("imageURL")).into(c2);
                        n2.setText(curr_cast2.getString("name"));

                        JSONObject curr_cast3 = cast_data.getJSONObject(2);
                        Glide.with(c3.getContext()).load(curr_cast3.getString("imageURL")).into(c3);
                        n3.setText(curr_cast3.getString("name"));

                        JSONObject curr_cast4 = cast_data.getJSONObject(3);
                        Glide.with(c4.getContext()).load(curr_cast4.getString("imageURL")).into(c4);
                        n4.setText(curr_cast4.getString("name"));

                        JSONObject curr_cast5 = cast_data.getJSONObject(4);
                        Glide.with(c5.getContext()).load(curr_cast5.getString("imageURL")).into(c5);
                        n5.setText(curr_cast5.getString("name"));

                        JSONObject curr_cast6 = cast_data.getJSONObject(5);
                        Glide.with(c6.getContext()).load(curr_cast6.getString("imageURL")).into(c6);
                        n6.setText(curr_cast6.getString("name"));

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


            requestThree.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestThree);

        }

        else if (type.equalsIgnoreCase("TV")){

            mQueue = Volley.newRequestQueue(this);
            String URLL = "http://10.0.2.2:4000/tv-series/cast?tvId="+id;

            JsonObjectRequest requestThree = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //JSONObject rec_result = response;
                    //Log.i(TAG, "STR 3:"+rec_result);

                    try {
                        JSONArray cast_data = response.getJSONArray("data");
//                    for (int i = 0; i < 6; i++) {
                        JSONObject curr_cast = cast_data.getJSONObject(0);
                        Glide.with(c1.getContext()).load(curr_cast.getString("imageURL")).into(c1);
                        n1.setText(curr_cast.getString("name"));

                        JSONObject curr_cast2 = cast_data.getJSONObject(1);
                        Glide.with(c2.getContext()).load(curr_cast2.getString("imageURL")).into(c2);
                        n2.setText(curr_cast2.getString("name"));

                        JSONObject curr_cast3 = cast_data.getJSONObject(2);
                        Glide.with(c3.getContext()).load(curr_cast3.getString("imageURL")).into(c3);
                        n3.setText(curr_cast3.getString("name"));

                        JSONObject curr_cast4 = cast_data.getJSONObject(3);
                        Glide.with(c4.getContext()).load(curr_cast4.getString("imageURL")).into(c4);
                        n4.setText(curr_cast4.getString("name"));

                        JSONObject curr_cast5 = cast_data.getJSONObject(4);
                        Glide.with(c5.getContext()).load(curr_cast5.getString("imageURL")).into(c5);
                        n5.setText(curr_cast5.getString("name"));

                        JSONObject curr_cast6 = cast_data.getJSONObject(5);
                        Glide.with(c6.getContext()).load(curr_cast6.getString("imageURL")).into(c6);
                        n6.setText(curr_cast6.getString("name"));

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


            requestThree.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestThree);
        }

    }



    public void getReviews()
    {
        if (type.equalsIgnoreCase("MOVIE")) {


            mQueue = Volley.newRequestQueue(this);
            String URLL = "http://10.0.2.2:4000/movies/reviews?movieId=" + id;

            JsonObjectRequest requestFour = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //JSONObject rec_result = response;
                    //Log.i(TAG, "STR 3:"+rec_result);

                    try {

                        JSONObject current_reviews = response.getJSONObject("data");
                        int total_results = current_reviews.getInt("total_results");
                        if(total_results >= 3) {
                            JSONArray results = current_reviews.getJSONArray("results");
                            final JSONObject first = results.getJSONObject(0);
                            final JSONObject second = results.getJSONObject(1);
                            final JSONObject third = results.getJSONObject(2);
                            final JSONObject author_details1 = first.getJSONObject("author_details");
                            final JSONObject author_details2 = second.getJSONObject("author_details");
                            final JSONObject author_details3 = third.getJSONObject("author_details");
                            r1.setText(first.getString("content"));
                            r2.setText(second.getString("content"));
                            r3.setText(third.getString("content"));

                            //recent
                            String dateCreatedUser1 = first.getString("created_at");
                            String dateCreatedUser2 = second.getString("created_at");
                            String dateCreatedUser3 = third.getString("created_at");

                            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy", Locale.ENGLISH);

                            LocalDate date1 = LocalDate.parse(dateCreatedUser1, inputFormatter);
                            String formattedDate1 = outputFormatter.format(date1);

                            LocalDate date2 = LocalDate.parse(dateCreatedUser2, inputFormatter);
                            String formattedDate2 = outputFormatter.format(date2);

                            LocalDate date3 = LocalDate.parse(dateCreatedUser3, inputFormatter);
                            String formattedDate3 = outputFormatter.format(date3);

                            name1.setText("by " + author_details1.getString("username") + " on " + formattedDate1);
                            name2.setText("by " + author_details2.getString("username") + " on " + formattedDate2);
                            name3.setText("by " + author_details3.getString("username") + " on " + formattedDate3);



//                            name1.setText(author_details1.getString("username"));
//                            name2.setText(author_details2.getString("username"));
//                            name3.setText(author_details3.getString("username"));


//                            int five1 = ((Integer.parseInt(author_details1.getString("rating"))));
                            rating1.setText(String.valueOf((Integer.parseInt(author_details1.getString("rating"))/2))+"/"+"5");
                            rating2.setText(String.valueOf((Integer.parseInt(author_details2.getString("rating"))/2))+"/"+"5");
                            rating3.setText(String.valueOf((Integer.parseInt(author_details3.getString("rating"))/2))+"/"+"5");

                            card1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), ReviewOne.class);
                                    try {
                                        intent.putExtra("rating", String.valueOf((Integer.parseInt(author_details1.getString("rating"))/2))+"/"+"5");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("content", first.getString("content"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("username", author_details1.getString("username"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("created_at", first.getString("created_at"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }



                            });

                            card2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), ReviewOne.class);
                                    try {
                                        intent.putExtra("rating", String.valueOf((Integer.parseInt(author_details2.getString("rating"))/2))+"/"+"5");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("content", second.getString("content"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("username", author_details2.getString("username"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("created_at", second.getString("created_at"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }



                            });

                            card3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), ReviewOne.class);
                                    try {
                                        intent.putExtra("rating", String.valueOf((Integer.parseInt(author_details3.getString("rating"))/2))+"/"+"5");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("content", third.getString("content"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("username", author_details3.getString("username"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("created_at", third.getString("created_at"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }



                            });
                        }
                        else
                        {
                            review_heading.setVisibility(View.GONE);
                            card1.setVisibility(View.GONE);
                            card2.setVisibility(View.GONE);
                            card3.setVisibility(View.GONE);

                        }

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


            requestFour.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestFour);

        }

        else if (type.equalsIgnoreCase("TV")){

            mQueue = Volley.newRequestQueue(this);
            String URLL = "http://10.0.2.2:4000/tv-series/reviews?tvId=" + id;

            JsonObjectRequest requestFour = new JsonObjectRequest(Request.Method.GET, URLL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //JSONObject rec_result = response;
                    //Log.i(TAG, "STR 3:"+rec_result);

                    try {

                        JSONObject current_reviews = response.getJSONObject("data");
                        int total_results = current_reviews.getInt("total_results");
                        if(total_results >= 3) {
                            JSONArray results = current_reviews.getJSONArray("results");
                            final JSONObject first = results.getJSONObject(0);
                            final JSONObject second = results.getJSONObject(1);
                            final JSONObject third = results.getJSONObject(2);
                            final JSONObject author_details1 = first.getJSONObject("author_details");
                            final JSONObject author_details2 = second.getJSONObject("author_details");
                            final JSONObject author_details3 = third.getJSONObject("author_details");
                            r1.setText(first.getString("content"));
                            r2.setText(second.getString("content"));
                            r3.setText(third.getString("content"));

                            //recent
                            String dateCreatedUser1 = first.getString("created_at");
                            String dateCreatedUser2 = second.getString("created_at");
                            String dateCreatedUser3 = third.getString("created_at");

                            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy", Locale.ENGLISH);

                            LocalDate date1 = LocalDate.parse(dateCreatedUser1, inputFormatter);
                            String formattedDate1 = outputFormatter.format(date1);

                            LocalDate date2 = LocalDate.parse(dateCreatedUser2, inputFormatter);
                            String formattedDate2 = outputFormatter.format(date2);

                            LocalDate date3 = LocalDate.parse(dateCreatedUser3, inputFormatter);
                            String formattedDate3 = outputFormatter.format(date3);

                            name1.setText("by " + author_details1.getString("username") + " on " + formattedDate1);
                            name2.setText("by " + author_details2.getString("username") + " on " + formattedDate2);
                            name3.setText("by " + author_details3.getString("username") + " on " + formattedDate3);


//                            name1.setText(author_details1.getString("username"));
//                            name2.setText(author_details2.getString("username"));
//                            name3.setText(author_details3.getString("username"));

//                            int five1 = ((Integer.parseInt(author_details1.getString("rating"))));
                            rating1.setText(String.valueOf((Integer.parseInt(author_details1.getString("rating"))/2))+"/"+"5");
                            rating2.setText(String.valueOf((Integer.parseInt(author_details2.getString("rating"))/2))+"/"+"5");
                            rating3.setText(String.valueOf((Integer.parseInt(author_details3.getString("rating"))/2))+"/"+"5");

                            card1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), ReviewOne.class);
                                    try {
                                        intent.putExtra("rating", String.valueOf((Integer.parseInt(author_details1.getString("rating"))/2))+"/"+"5");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("content", first.getString("content"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("username", author_details1.getString("username"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("created_at", first.getString("created_at"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }



                            });

                            card2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), ReviewOne.class);
                                    try {
                                        intent.putExtra("rating", String.valueOf((Integer.parseInt(author_details2.getString("rating"))/2))+"/"+"5");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("content", second.getString("content"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("username", author_details2.getString("username"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("created_at", second.getString("created_at"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }



                            });

                            card3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


//
                                    Intent intent = new Intent(getApplicationContext(), ReviewOne.class);
                                    try {
                                        intent.putExtra("rating", String.valueOf((Integer.parseInt(author_details3.getString("rating"))/2))+"/"+"5");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("content", third.getString("content"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("username", author_details3.getString("username"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        intent.putExtra("created_at", third.getString("created_at"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }



                            });
                        }
                        else
                        {
                            review_heading.setVisibility(View.GONE);
                            card1.setVisibility(View.GONE);
                            card2.setVisibility(View.GONE);
                            card3.setVisibility(View.GONE);

                        }

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


            requestFour.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            mQueue.add(requestFour);

        }

    }





        // prepare the Request
//        @SuppressLint("SetTextI18n") StringRequest stringRequest = new StringRequest(Request.Method.GET, URLL, (new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //textView3.setText("Response is 3: "+response);
//                Log.i(TAG, "RESPONSE 3" + response);
//
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    Log.i(TAG, "JSON RESPONSE 3:" + obj);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }), new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                recommended.setText("didn't work");
//                Log.i(TAG, "error: " + error);
//
//            }
//        });
//        // add it to the RequestQueue
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(stringRequest);
//    }

//    public void setActivityBackgroundColor(int color) {
//        View view = this.getWindow().getDecorView();
//        view.setBackgroundColor(color);
//    }
}

