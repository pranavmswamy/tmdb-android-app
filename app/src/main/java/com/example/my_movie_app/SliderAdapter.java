package com.example.my_movie_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import static android.graphics.BlurMaskFilter.*;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private final List<ImageSliderItem> mSliderItems;
    private final Context sContext;
    private String media_type;

    public SliderAdapter(Context sContext, List<ImageSliderItem> mSliderItems) {
        this.mSliderItems = mSliderItems;
        this.sContext = sContext;
    }


    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final SliderAdapterViewHolder viewHolder, final int position) {
        final ImageSliderItem sliderItem = mSliderItems.get(position);

        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImageURL())
                .fitCenter()
                .into(viewHolder.myImage);

        if(mSliderItems.get(position).isMovie()){
            media_type="MOVIE";
        }else{
            media_type="TV";
        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(sContext, "Image Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(sContext, AllDetailsActivity.class);
                i.putExtra("id",mSliderItems.get(position).getMovieID());
                i.putExtra("media_type",media_type);
                view.getContext().startActivity(i);
            }
        });

        Glide.with(viewHolder.itemView).load(sliderItem.getImageURL()).transform(new MultiTransformation(new jp.wasabeef.glide.transformations.BlurTransformation(25,2))).into(viewHolder.backImage);

//        Target target = new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                viewHolder.backImage.setImageBitmap();
//            }
//
//            @Override
//            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        };



    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        View itemView;
        ImageView myImage;
        ImageView backImage;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            myImage = itemView.findViewById(R.id.slidingImage);
            backImage = itemView.findViewById(R.id.ImageBlur);
            this.itemView = itemView;
        }
    }
}
