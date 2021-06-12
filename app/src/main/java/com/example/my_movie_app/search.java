package com.example.my_movie_app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.my_movie_app.searchhelper.SearchAdapter;
import com.example.my_movie_app.searchhelper.SearchcardModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SearchView searchView;
    RecyclerView searchRecyclerView;
    SearchAdapter searchAdapter;
    TextView noResults;
    ArrayList<SearchcardModel> searchcards = new ArrayList<>();

    private RequestQueue mRequestQueue;

    public search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search.
     */
    // TODO: Rename and change types and number of parameters
    public static search newInstance(String param1, String param2) {
        search fragment = new search();
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

        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View searchFragmentView = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = searchFragmentView.findViewById(R.id.searchview);
        noResults = searchFragmentView.findViewById(R.id.noResult);
        noResults.setVisibility(View.GONE);
        searchRecyclerView = searchFragmentView.findViewById(R.id.searchRecyclerView);
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();

        ImageView icon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        icon.setColorFilter(Color.WHITE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                noResults.setVisibility(View.GONE);
                if(query.length() > 0) {
                    makeMultiSearchAPICall(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                noResults.setVisibility(View.GONE);
                if(newText.length() > 0) {
                    makeMultiSearchAPICall(newText);
                }
                return true;
            }
        });

        searchAdapter = new SearchAdapter(searchcards);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        searchRecyclerView.setAdapter(searchAdapter);

        return searchFragmentView;
    }

    void makeMultiSearchAPICall(String searchQuery) {
        searchQuery = searchQuery.replace(" ", "%20");
        String url = "http://usc-films.herokuapp.com/search/all?query=" + searchQuery;

        //final ArrayList<SearchcardModel> searchcards = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                searchRecyclerView.setVisibility(View.GONE);
                // show progress bar
                searchcards.clear();

                // add to searchcards
                try {
                    JSONObject _response = new JSONObject(response);
                    JSONArray results = _response.getJSONArray("data");

                    for (int i=0; i<results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);
                        searchcards.add(new SearchcardModel(result.getString("id"), result.getString("title"), result.getString("imageURL"), result.getString("voteAverage"), result.getString("releaseDate"), result.getString("mediaType")));
                    }

                    if(results.length() > 0) {
                        noResults.setVisibility(View.GONE);
                    }
                    else {
                        noResults.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                searchRecyclerView.getAdapter().notifyDataSetChanged();
                searchRecyclerView.setVisibility(View.VISIBLE);
                //hide progress bar
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Search API Response Error", "GET Request Error");
            }
        });

        mRequestQueue.add(stringRequest);

    }


}