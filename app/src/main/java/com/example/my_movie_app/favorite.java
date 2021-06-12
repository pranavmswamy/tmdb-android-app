package com.example.my_movie_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favorite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favorite extends Fragment implements favouriteAdapter.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //vars
    View view;
    private RecyclerView mRecyclerView;
    public ArrayList<MovieItem> mMovieList;
    private favouriteAdapter mFavouriteAdapter;
    private Context mContext;
    private String media_type;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favorite() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favorite.
     */
    // TODO: Rename and change types and number of parameters
    public static favorite newInstance(String param1, String param2) {
        favorite fragment = new favorite();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_favorite, container, false);
        view = inflater.inflate(R.layout.fragment_favorite, container, false);

        boolean var = loadData();

        if(var == true){
            mRecyclerView = view.findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);

            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

            mFavouriteAdapter = new favouriteAdapter(getActivity(), mMovieList);
            mRecyclerView.setAdapter(mFavouriteAdapter);
            mFavouriteAdapter.setOnItemClickListener(favorite.this);

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(mRecyclerView);
        }
        else{
            TextView textView = view.findViewById(R.id.textBookmark);
            textView.setText("Nothing saved to Watchlist");
        }
        return view;
    }
    public boolean loadData() {

        boolean flag = false;
        mMovieList = new ArrayList<>();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("", Context.MODE_PRIVATE);

        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String textToDis = sharedPreferences.getString(entry.getKey(), "default value");

            if(textToDis.compareToIgnoreCase("default value")!=0){
                String[] dataFetched = textToDis.split("@split@");

                String title = dataFetched[1];
                String imageUrl = dataFetched[2];
                String movieID = dataFetched[3];
                String releaseYear = dataFetched[4];
                String isMovieOrTV = dataFetched[5];

                boolean temp = true;
                if(isMovieOrTV.equalsIgnoreCase("true")) {
                    temp = true;
                }else{
                    temp = false;
                }


                mMovieList.add(new MovieItem(title, imageUrl, Integer.parseInt(movieID), releaseYear, temp));

                flag = true;
            }
        }

        return flag;
    }

    @Override
    public void onResume() {
        if(mFavouriteAdapter!=null) {
            loadData();
            if(mMovieList.isEmpty()){
                TextView textView = view.findViewById(R.id.textBookmark);
                textView.setText("No Bookmarked Articles");
            }

            mFavouriteAdapter.swap(mMovieList);
        }
        super.onResume();
    }

    @Override
    public void onItemClick(int position) {
        // on item click method to detailed activity left to write

//        Toast.makeText(getContext(), "Title " + title, Toast.LENGTH_LONG).show();
//        Toast.makeText(getContext(), "MovieID " + movieID, Toast.LENGTH_LONG).show();

        final Intent detailIntent = new Intent(this.getActivity(), AllDetailsActivity.class);
        final MovieItem clickedItem = mMovieList.get(position);

        if(clickedItem.getMovie()){
            media_type = "MOVIE";
        }else{
            media_type = "TV";
        }

        detailIntent.putExtra("id", clickedItem.getMovieID());
        detailIntent.putExtra("media_type", media_type);
        startActivity(detailIntent);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN |
            ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(mMovieList, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
}