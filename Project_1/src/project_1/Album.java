package project_1;

/**
 *
 */
public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;

    public Album(String title, String artist, String genre, String date){
        this.releaseDate = new Date(date);
        this.genre = new Genre(genre);
        this.title = title;
        this.artist = artist;
        this.isAvailable = true;
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public String toString() {
        return "test String";
    }
}
