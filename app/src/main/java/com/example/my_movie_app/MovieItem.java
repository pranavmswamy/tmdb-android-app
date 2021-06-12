package com.example.my_movie_app;

public class MovieItem {

    private String title;
    private String imageURL;
    private int movieID;
    private String releaseYear;
    private Boolean isMovie;


    public MovieItem(String title, String imageURL, int movieID, String releaseYear, boolean isMovie) {
        this.title = title;
        this.imageURL = imageURL;
        this.movieID = movieID;
        this.releaseYear = releaseYear;
        this.isMovie = isMovie;
    }

    public MovieItem() {
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public Boolean getMovie() {
        return isMovie;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setMovie(Boolean movie) {
        isMovie = movie;
    }
}

