package com.example.my_movie_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class favouriteAdapter extends RecyclerView.Adapter<favouriteAdapter.HomeViewHolder> implements View.OnLongClickListener{

    private Context mContext;
    private  favouriteAdapter favouriteAdapter;
    private ArrayList<MovieItem> mMovieList;
    private OnItemClickListener mListener;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    @Override
    public boolean onLongClick(View v) {

        return false;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void removeItem(MovieItem hm, String key) {
        mMovieList.remove(hm);
        checkSize();
        notifyDataSetChanged();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public favouriteAdapter(Context context, ArrayList<MovieItem> movieList) {
        mContext = context;
        favouriteAdapter = this;
        mMovieList = movieList;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.favourite_items, parent, false);

        return new HomeViewHolder(v);
    }

    public void swap(ArrayList<MovieItem> items){
        mMovieList = items;
        notifyDataSetChanged();
    }

    public void refresh(){
        ((favorite) mListener).loadData();
        swap(((favorite) mListener).mMovieList);
        checkSize();
    }

    public void checkSize(){
        if(mMovieList.isEmpty()) {
            TextView textView = ((favorite) mListener).view.findViewById(R.id.textBookmark);
            textView.setText("Nothing saved to Watchlist");
        }
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        View v = holder.itemView;
        MovieItem currentItem = mMovieList.get(position);
        holder.item = currentItem;

        String imageUrl = currentItem.getImageURL();
        boolean isMovie = currentItem.getMovie();
        String releaseYear = currentItem.getReleaseYear();

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("",Context.MODE_PRIVATE);

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
        if(isMovie){
            holder.mIsMovie.setText("Movie");
        }else{
            holder.mIsMovie.setText("TV");
        }
//        holder.mReleaseYear.setText(releaseYear);

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public MovieItem item;
        public TextView mIsMovie;
        public TextView mReleaseYear;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        public HomeViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mIsMovie = itemView.findViewById(R.id.text_view_isMovie);
            mReleaseYear = itemView.findViewById(R.id.text_view_releaseYear);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                    favouriteAdapter.notifyDataSetChanged();
                }
            });

            final ImageView removeView = (ImageView) itemView.findViewById(R.id.remove);

            removeView.setOnClickListener(new View.OnClickListener() {
                int positionForFavourite;
                @Override
                public void onClick(View v) {

                    positionForFavourite = getAdapterPosition();

                    MovieItem currentItemToRemove = mMovieList.get(positionForFavourite);
                    String title = currentItemToRemove.getTitle();

                    int movieID = currentItemToRemove.getMovieID();

                    Toast.makeText(mContext,'"'+ title + '"' + " was removed from favourites", Toast.LENGTH_SHORT).show();
                    removeItem(currentItemToRemove, String.valueOf(movieID));
                }
            });

        }
    }
}
