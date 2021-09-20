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

    public boolean getAvailable(){
        return isAvailable;
    }

    public void setAvailable(){
        isAvailable = !isAvailable;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }
        if(!(obj instanceof  Album)){
            return false;
        }
        Album comp = (Album) obj;
        return title.equals(comp.getTitle()) && artist.equals(comp.getArtist());
    }

    @Override
    public String toString() {
        return title + "::" + artist + "::"+ genre.toString() + "::" + releaseDate.toString() + "::"
                + (isAvailable ? "is available" : "is not available");
    }

    public Date getReleaseDate(){
        return releaseDate;
    }
}
