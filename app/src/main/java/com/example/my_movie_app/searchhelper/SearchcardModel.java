package com.example.my_movie_app.searchhelper;

public class SearchcardModel {

    private String name;
    private String backdropImage;
    private String rating;
    private String year;
    private String type;
    private String id;

    public SearchcardModel(String id, String name, String backdropImage, String rating, String year, String type) {
        this.name = name;
        this.backdropImage = backdropImage;
        this.rating = rating;
        this.year = year;
        this.type = type;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public void setBackdropImage(String backdropImage) {
        this.backdropImage = backdropImage;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
