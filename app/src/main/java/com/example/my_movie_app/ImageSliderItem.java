package com.example.my_movie_app;

public class ImageSliderItem {
    String imageURL;
    int movieID;
    boolean isMovie;

    //movie getter
    public boolean isMovie() {
        return isMovie;
    }

    //movie_setter
    public void setMovie(boolean movie) {
        isMovie = movie;
    }

    public ImageSliderItem(String imageURL, int movieID, boolean isMovie) {
        this.imageURL = imageURL;
        this.movieID = movieID;
        this.isMovie = isMovie;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
