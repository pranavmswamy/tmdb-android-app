package com.example.my_movie_app.searchhelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_movie_app.R;

public class SearchcardViewHolder extends RecyclerView.ViewHolder {

    TextView name, rating, year, type;
    ImageView backdropImage;
    CardView cardView;

    public SearchcardViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.searchName);
        rating = itemView.findViewById(R.id.searchRating);
        backdropImage = itemView.findViewById(R.id.searchImage);
        year = itemView.findViewById(R.id.searchYear);
        type = itemView.findViewById(R.id.searchType);
        cardView = itemView.findViewById(R.id.searchCardView);

    }
}
