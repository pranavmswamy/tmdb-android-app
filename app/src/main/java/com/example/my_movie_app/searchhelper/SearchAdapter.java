package com.example.my_movie_app.searchhelper;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.example.my_movie_app.AllDetailsActivity;
import com.example.my_movie_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<SearchcardModel> searchcards = new ArrayList<>();

    public SearchAdapter(ArrayList<SearchcardModel> searchcards) {
        this.searchcards = searchcards;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card, parent, false);
        SearchcardViewHolder searchcardViewHolder = new SearchcardViewHolder(searchView);
        return searchcardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final SearchcardViewHolder searchcardViewHolder = (SearchcardViewHolder) holder;
        searchcardViewHolder.name.setText(searchcards.get(position).getName());
        double rating = Double.parseDouble(searchcards.get(position).getRating()) / 2;
        searchcardViewHolder.rating.setText(String.format("%.1f", rating));
        searchcardViewHolder.type.setText(searchcards.get(position).getType().toUpperCase());
        String year = searchcards.get(position).getYear();
        if(year.length() > 0) {
            searchcardViewHolder.year.setText("(" + searchcards.get(position).getYear().substring(0, 4) + ")");
        }
        else {
            searchcardViewHolder.year.setText("");
        }
        Picasso.get().load(searchcards.get(position).getBackdropImage()).into(searchcardViewHolder.backdropImage);

        searchcardViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(searchcardViewHolder.cardView.getContext(), AllDetailsActivity.class);
                detailsIntent.putExtra("id", Integer.parseInt(searchcards.get(position).getId()));
                if(searchcards.get(position).getType().toUpperCase().equalsIgnoreCase("TV")) {
                    detailsIntent.putExtra("media_type", "TV");
                }
                else {
                    detailsIntent.putExtra("media_type", "MOVIE");
                }
                searchcardViewHolder.cardView.getContext().startActivity(detailsIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchcards.size();
    }

}
