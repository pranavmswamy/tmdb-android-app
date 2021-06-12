package com.example.my_movie_app;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_main extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG="";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home_main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_main.
     */
    // TODO: Rename and change types and number of parameters
    public static home_main newInstance(String param1, String param2) {
        home_main fragment = new home_main();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    private TextView result_text;
    private TextView result_textTV;
    private RequestQueue mQueue;
    private ImageView headImage;
    private ImageView headImageTV;

    private RecyclerView rec;
    private RecyclerView recTrending;
    private RecyclerView recPopular;

    private RecyclerView recTV;
    private RecyclerView recTrendingTV;
    private RecyclerView recPopularTV;


    private TextView toggleButton_one;
    private TextView toggleButton_two;

    private RelativeLayout movie;
    private RelativeLayout tv;

    private LinearLayout movie_showTV;
    private LinearLayout movie_showMovie;

    private LinearLayout tv_showMovie;
    private LinearLayout tv_showTV;

    private ArrayList<MovieItem> movies;
    private ArrayList<MovieItem> tvShows;
    private ArrayList<MovieItem> moviesTrending;
    private ArrayList<MovieItem> tvTrending;
    private ArrayList<MovieItem> moviesPopular;
    private ArrayList<MovieItem> tvPopular;

    private ArrayList<ImageSliderItem> movie_images = new ArrayList<>();
    private ArrayList<ImageSliderItem> tv_images = new ArrayList<>();

    private SliderView movie_view;
    private SliderView tv_view;
    private RelativeLayout main_relative;
    private  RelativeLayout progress_relative;
    private TextView tvpoweredby, moviepoweredby;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home_main, container, false);

        movie_showMovie = view.findViewById(R.id.movie_showMovie);
        movie_showTV = view.findViewById(R.id.movie_showTV);

        tv_showMovie = view.findViewById(R.id.tv_showMovie);
        tv_showTV = view.findViewById(R.id.tv_showTV);

        main_relative = view.findViewById(R.id.main_relative);
        progress_relative = view.findViewById((R.id.progress_relative));

        movies = new ArrayList<>();
        tvShows = new ArrayList<>();
        moviesTrending = new ArrayList<>();
        tvTrending = new ArrayList<>();
        moviesPopular = new ArrayList<>();
        tvPopular = new ArrayList<>();

        tv_view = view.findViewById(R.id.sliderTV);
        movie_view = view.findViewById(R.id.slider);

        tvpoweredby = view.findViewById(R.id.tvpoweredby);
        moviepoweredby = view.findViewById(R.id.moviepoweredby);

        tvpoweredby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/"));
                view.getContext().startActivity(intent1);
            }
        });

        moviepoweredby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/"));
                view.getContext().startActivity(intent1);
            }
        });


//        toggleButton_one = view.findViewById(R.id.toggleTV);
//        toggleButton_two = view.findViewById(R.id.toggleMovie);

        movie = view.findViewById(R.id.movieLayOut);
        tv = view.findViewById(R.id.TVLayOut);

        movie_showTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
            }
        });

        tv_showMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setVisibility(View.GONE);
                movie.setVisibility(View.VISIBLE);
            }
        });

        TextView textView = (TextView) view.findViewById(R.id.gotoDetails);
        TextView textViewTV = (TextView) view.findViewById(R.id.gotoDetailsTV);

//        Button send_request = (Button) view.findViewById(R.id.button_one);
        result_text = view.findViewById(R.id.mainHeading);
//        headImage = view.findViewById(R.id.openingImageView);

        result_textTV = view.findViewById(R.id.mainHeadingTV);
//        headImageTV = view.findViewById(R.id.openingImageViewTV);

        rec = view.findViewById(R.id.recyclerOne);
        recTrending = view.findViewById(R.id.recyclerTwo);
        recPopular = view.findViewById(R.id.recyclerThree);

        recTV = view.findViewById(R.id.recyclerOneTV);
        recTrendingTV = view.findViewById(R.id.recyclerTwoTV);
        recPopularTV = view.findViewById(R.id.recyclerThreeTV);





        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        String urlOne ="http://10.0.2.2:4000/movies/trending";
        String urlTwo ="http://10.0.2.2:4000/movies/top-rated";
        String urlThree ="http://10.0.2.2:4000/movies/popular";

        String urlOneTV="http://10.0.2.2:4000/tv-series/trending";
        String urlTwoTV="http://10.0.2.2:4000/tv-series/top-rated";
        String urlThreeTV="http://10.0.2.2:4000/tv-series/popular";

        jsonParse();
        jsonParseTV();


//        movie_details.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), AllDetailsActivity.class);
//                //intent.putExtra("some", "somedata");
//                startActivity(intent);
//            }
//        });
//
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllDetailsActivity.class);
                //intent.putExtra("some", "somedata");
                startActivity(intent);
            }
        });
        return view;

    }


    private void jsonParseTV(){
        String urlOne = "http://10.0.2.2:4000/tv-series/trending";
        String urlTwo = "http://10.0.2.2:4000/tv-series/top-rated";
        String urlThree = "http://10.0.2.2:4000/tv-series/popular";


        JsonObjectRequest requestOne = new JsonObjectRequest(Request.Method.GET, urlOne, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    JSONObject dataSent = new JSONObject();

//                    tvShows.clear();

                    tv_images.clear();

                    for(int i=0;i<6;i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        ImageSliderItem img_item = new ImageSliderItem(curr_movie.getString("imageURL"),curr_movie.getInt("id"),false);
                        tv_images.add(img_item);

                    }

                    initsliderrec_tv(tv_images);

//                    for(int i=1;i<10;i++){
//                        JSONObject curr_movie = data.getJSONObject(i);
//
//                        MovieItem new_item = new MovieItem();
//
//                        new_item.setImageURL(curr_movie.getString("imageURL"));
//                        new_item.setTitle(curr_movie.getString("title"));
//                        new_item.setMovieID(curr_movie.getInt("id"));
//                        new_item.setReleaseYear(curr_movie.getString("releaseDate"));
//
//                        tvShows.add(new_item);
//
//
//                    }
//                    initRecyclerViewTV(tvShows);

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

                    for(int i=0;i<10;i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        MovieItem new_item = new MovieItem();

                        new_item.setImageURL(curr_movie.getString("imageURL"));
                        new_item.setTitle(curr_movie.getString("title"));
                        new_item.setMovieID(curr_movie.getInt("id"));
                        new_item.setReleaseYear(curr_movie.getString("releaseDate"));
                        new_item.setMovie(false);

                        tvTrending.add(new_item);
                    }


                    initRecyclerView_trendingTV(tvTrending);

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

                    for(int i=0;i<10;i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        MovieItem new_item = new MovieItem();

                        new_item.setImageURL(curr_movie.getString("imageURL"));
                        new_item.setTitle(curr_movie.getString("title"));
                        new_item.setMovieID(curr_movie.getInt("id"));
                        new_item.setReleaseYear(curr_movie.getString("releaseDate"));
                        new_item.setMovie(false);

                        tvPopular.add(new_item);
                    }

                    initRecyclerView_popularTV(tvPopular);

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



        requestOne.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mQueue.add(requestOne);
        mQueue.add(requestTwo);
        mQueue.add(requestThree);


    }

    private void jsonParse(){
        String urlOne = "http://10.0.2.2:4000/movies/now-playing";
        String urlTwo = "http://10.0.2.2:4000/movies/top-rated";
        String urlThree = "http://10.0.2.2:4000/movies/popular";


        JsonObjectRequest requestOne = new JsonObjectRequest(Request.Method.GET, urlOne, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    progress_relative.setVisibility(View.GONE);
                    main_relative.setVisibility(View.VISIBLE);

                    movie_images.clear();
                    for(int i=0;i<6;i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        ImageSliderItem img_item = new ImageSliderItem(curr_movie.getString("imageURL"),curr_movie.getInt("id"),true);
                        movie_images.add(img_item);

                    }

                    initsliderrec_movie(movie_images);

//                    movies.clear();

//                    for(int i=1;i<10;i++){
//                        JSONObject curr_movie = data.getJSONObject(i);
//
//
//                        MovieItem new_item = new MovieItem();
//
//                        new_item.setImageURL(curr_movie.getString("imageURL"));
//                        new_item.setTitle(curr_movie.getString("title"));
//                        new_item.setMovieID(curr_movie.getInt("id"));
//                        new_item.setReleaseYear(curr_movie.getString("releaseDate"));
//
//                        movies.add(new_item);
//                    }
//
//                    initRecyclerView(movies);


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

//                    moviesTrending.clear();
                    for(int i=0;i<10;i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        MovieItem new_item = new MovieItem();

                        new_item.setImageURL(curr_movie.getString("imageURL"));
                        new_item.setTitle(curr_movie.getString("title"));
                        new_item.setMovieID(curr_movie.getInt("id"));
                        new_item.setReleaseYear(curr_movie.getString("releaseDate"));
                        new_item.setMovie(true);

                        moviesTrending.add(new_item);
                    }


                    initRecyclerView_trending(moviesTrending);

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

//                    moviesPopular.clear();
                    for(int i=0;i<10;i++){
                        JSONObject curr_movie = data.getJSONObject(i);

                        MovieItem new_item = new MovieItem();

                        new_item.setImageURL(curr_movie.getString("imageURL"));
                        new_item.setTitle(curr_movie.getString("title"));
                        new_item.setMovieID(curr_movie.getInt("id"));
                        new_item.setReleaseYear(curr_movie.getString("releaseDate"));
                        new_item.setMovie(true);

                        moviesPopular.add(new_item);

                    }

                    initRecyclerView_popular(moviesPopular);

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

        requestOne.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestTwo.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        mQueue.add(requestOne);
        mQueue.add(requestTwo);
        mQueue.add(requestThree);


    }

    private void initsliderrec_tv(ArrayList<ImageSliderItem> currentList){
        SliderAdapter adapter = new SliderAdapter(getActivity().getApplicationContext(), currentList);
        tv_view.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        tv_view.setSliderAdapter(adapter);
        tv_view.setScrollTimeInSec(3);
        tv_view.setAutoCycle(true);
        tv_view.startAutoCycle();
    }

    private void initsliderrec_movie(ArrayList<ImageSliderItem> currentList){
        SliderAdapter adapter = new SliderAdapter(getActivity().getApplicationContext(), currentList);
        movie_view.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        movie_view.setSliderAdapter(adapter);
        movie_view.setScrollTimeInSec(3);
        movie_view.setAutoCycle(true);
        movie_view.startAutoCycle();
    }

    private void initRecyclerView(ArrayList<MovieItem> currentList){
        Log.d(TAG, "initRecyclerView: called.");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rec.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity().getApplicationContext(),currentList);
        rec.setAdapter(adapter);

    }

    private void initRecyclerView_trending(ArrayList<MovieItem> currentList){
        Log.d(TAG, "initRecyclerViewTwo: called.");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recTrending.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity().getApplicationContext(),currentList);
        recTrending.setAdapter(adapter);

    }

    private void initRecyclerView_popular(ArrayList<MovieItem> currentList){
        Log.d(TAG, "initRecyclerViewTwo: called.");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recPopular.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity().getApplicationContext(),currentList);
        recPopular.setAdapter(adapter);

    }

    private void initRecyclerViewTV(ArrayList<MovieItem> currentList){
        Log.d(TAG, "initRecyclerView: called.");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recTV.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity().getApplicationContext(),currentList);
        recTV.setAdapter(adapter);

    }

    private void initRecyclerView_trendingTV(ArrayList<MovieItem> currentList){
        Log.d(TAG, "initRecyclerViewTwo: called.");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recTrendingTV.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity().getApplicationContext(),currentList);
        recTrendingTV.setAdapter(adapter);

    }

    private void initRecyclerView_popularTV(ArrayList<MovieItem> currentList){
        Log.d(TAG, "initRecyclerViewTwo: called.");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recPopularTV.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity().getApplicationContext(),currentList);
        recPopularTV.setAdapter(adapter);

    }


}

