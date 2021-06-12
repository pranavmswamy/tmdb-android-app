package com.example.my_movie_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Map;

import static com.example.my_movie_app.home_main.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG="RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImagesURLs = new ArrayList<>();
    private ArrayList<String> mYear = new ArrayList<>();
    private Context mContext;
    private ArrayList<MovieItem> item_data = new ArrayList<>();
    private String media_type;

    public RecyclerViewAdapter(Context mContext, ArrayList<MovieItem> item_data) {
//        this.mNames = mNames;
//        this.mImagesURLs = mImagesURLs;
        this.mContext = mContext;
//        this.mYear = mYear;

        this.item_data=item_data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final SharedPreferences sharedPreferences = mContext.getSharedPreferences("", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext).asBitmap().load(item_data.get(position).getImageURL()).into(holder.image);

        holder.name.setText(item_data.get(position).getTitle());

        if(item_data.get(position).getMovie()){
            media_type = "MOVIE";
        }else{
            media_type = "TV";
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext,item_data.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mContext, AllDetailsActivity.class);
                i.putExtra("id",item_data.get(position).getMovieID());
                i.putExtra("media_type",media_type);
                view.getContext().startActivity(i);

            }
        });


        String tempText = "("+item_data.get(position).getReleaseYear().substring(0,4)+")";

        holder.year.setText(tempText);

        //holder is made final. Please change this if the app breaks
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(final View view) {

                PopupMenu popUp = new PopupMenu(mContext, holder.buttonViewOption);

                Map<String, ?> allEntries = sharedPreferences.getAll();
                int movieIDinMap = item_data.get(position).getMovieID();

                    if(!allEntries.containsKey(String.valueOf(movieIDinMap))){
                        popUp.inflate(R.menu.long_press_menu);

                        popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId()) {

                                    case R.id.trailer_option:
                                        Intent browserIntent_youtube;
                                        if(item_data.get(position).getMovie()){
                                            browserIntent_youtube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/movie/"+item_data.get(position).getMovieID()));
                                        }else{
                                            browserIntent_youtube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/tv/"+item_data.get(position).getMovieID()));
                                        }
//                                        Intent browserIntent_yt = new Intent(Intent.ACTION_VIEW, Uri.parse(myurl));
                                        view.getContext().startActivity(browserIntent_youtube);
//
                                        break;
                                    case R.id.fb_option:
                                        String type="";
                                        String share_text="";

                                        if(item_data.get(position).getMovie()){
                                            type="https://www.themoviedb.org/movie/"+item_data.get(position).getMovieID();
                                        }else{
                                            type="https://www.themoviedb.org/tv/"+item_data.get(position).getMovieID();
                                        }
                                        share_text="Check this out!";
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/sharer.php?u="+type+"&t="+share_text));
                                        view.getContext().startActivity(browserIntent);
//
                                        break;
                                    case R.id.tw_option:
                                        if(item_data.get(position).getMovie()){
                                            type="https://www.themoviedb.org/movie/"+item_data.get(position).getMovieID();
                                        }else{
                                            type="https://www.themoviedb.org/tv/"+item_data.get(position).getMovieID();
                                        }
                                        share_text="Check this out! ";
                                        Intent browserIntent_tw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text="+share_text+"&url="+type));
                                        view.getContext().startActivity(browserIntent_tw);
                                        break;
                                    case R.id.watchlist_option:
                                        String title = item_data.get(position).getTitle();
                                        int movieID = item_data.get(position).getMovieID();
                                        String imageURL = item_data.get(position).getImageURL();
                                        String releaseYear = item_data.get(position).getReleaseYear();
                                        boolean isMovie = item_data.get(position).getMovie();
                                        String isMovieOrTV = "";
                                        if(isMovie) isMovieOrTV = "true";
                                        else isMovieOrTV = "false";

                                        String data = "@split@" + title + "@split@" + imageURL + "@split@" + movieID + "@split@" + releaseYear + "@split@" + isMovieOrTV;

                                        editor.putString(String.valueOf(movieID), data);
                                        editor.commit();
                                        Toast.makeText(mContext, title + " was added to Watchlist", Toast.LENGTH_LONG).show();
                                        //handle
                                        break;
                                    default:
                                        break;
                                }
                                return false;
                            }
                        });

                    } else {
                        popUp.inflate(R.menu.long_press_menu_remove);

                        popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId()) {
                                    case R.id.trailer_youtube:
                                        Intent browserIntent_youtube;
                                        if(item_data.get(position).getMovie()){
                                            browserIntent_youtube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/movie/"+item_data.get(position).getMovieID()));
                                        }else{
                                            browserIntent_youtube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/tv/"+item_data.get(position).getMovieID()));
                                        }
//                                        Intent browserIntent_yt = new Intent(Intent.ACTION_VIEW, Uri.parse(myurl));
                                        view.getContext().startActivity(browserIntent_youtube);
                                        break;
                                    case R.id.fb_share:
                                        //handle
                                        String type="";
                                        String share_text="";

                                        if(item_data.get(position).getMovie()){
                                            type="https://www.themoviedb.org/movie/"+item_data.get(position).getMovieID();
                                        }else{
                                            type="https://www.themoviedb.org/tv/"+item_data.get(position).getMovieID();
                                        }
                                        share_text="Check this out!";
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/sharer.php?u="+type+"&t="+share_text));
                                        view.getContext().startActivity(browserIntent);
                                        break;
                                    case R.id.tw_share:
//                                        String type="";
//                                        String share_text="";

                                        if(item_data.get(position).getMovie()){
                                            type="https://www.themoviedb.org/movie/"+item_data.get(position).getMovieID();
                                        }else{
                                            type="https://www.themoviedb.org/tv/"+item_data.get(position).getMovieID();
                                        }
                                        share_text="Check this out! ";
                                        Intent browserIntent_tw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text="+share_text+"&url="+type));
                                        view.getContext().startActivity(browserIntent_tw);
                                        break;
                                    case R.id.watchlist_remove:
                                        String title = item_data.get(position).getTitle();
                                        int movieID = item_data.get(position).getMovieID();
                                        notifyDataSetChanged();
                                        editor.remove(String.valueOf(movieID));
                                        editor.commit();
                                        Toast.makeText(mContext, title + " was removed from Watchlist", Toast.LENGTH_SHORT).show();
                                        //handle
                                        break;
                                }
                                return false;
                            }
                        });
                    }
                popUp.show();

//                    return true;
            }
        });


    }


    @Override
    public int getItemCount() {
        return item_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView buttonViewOption;
        TextView year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.cardImageOne);
            name=itemView.findViewById(R.id.nameOne);
            buttonViewOption = itemView.findViewById(R.id.textViewOptions);
            year = itemView.findViewById(R.id.release_year);
        }

    }

}
