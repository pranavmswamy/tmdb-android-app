public class MovieItem {

    private String title;
    private String imageURL;
    private String movieID;
    private String releaseYear;

    public MovieItem(String title, String imageURL, String movieID, String releaseYear) {
        this.title = title;
        this.imageURL = imageURL;
        this.movieID = movieID;
        this.releaseYear = releaseYear;
    }

    public MovieItem() {
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}
