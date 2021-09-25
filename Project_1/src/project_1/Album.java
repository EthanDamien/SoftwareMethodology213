package project_1;


/**
 * The class that represents an album the following information: the title, artist, genre, and release date.
 * Every album will be tracked if it is available or not for a given Collection.
 * @author Ethan Chang, Kevin Cubillos
 */
public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;

    /**
     * Constructor of Album where all information is known.
     * isAvailable is set to true by default.
     * The genre parameter is converted from String to a Genre enum value.
     * @param title the title of album
     * @param artist the artist name
     * @param genre the genre as a String
     * @param date the release date of album
     */
    public Album(String title, String artist, String genre, String date){
        this.releaseDate = new Date(date);
        this.title = title;
        this.artist = artist;
        this.isAvailable = true;

        genre = genre.toUpperCase();
        if(genre == "CLASSICAL"){
            this.genre = Genre.Classical;
        }
        else if(genre == "COUNTRY"){
            this.genre = Genre.Country;
        }
        else if(genre == "JAZZ"){
            this.genre = Genre.Jazz;
        }
        else if(genre == "POP"){
            this.genre = Genre.Pop;
        }
        else{
            this.genre = Genre.Unknown;
        }
    }

    /**
     * Constructor of Album mainly for comparison purposes.
     * The release date is set to today's date by default.
     * The genre is set to unknown by default.
     * @param title the title of album
     * @param artist the artist of album
     */
    public Album(String title, String artist){
        this.title = title;
        this.artist = artist;
        this.releaseDate = new Date();
        this.isAvailable = true;
        this.genre = Genre.Unknown;
    }

    /**
     * Getter for title
     * @return title of album
     */
    public String getTitle(){
        return title;
    }

    /**
     * Getter for title
     * @return title of album
     */
    public String getArtist(){
        return artist;
    }

    /**
     * Getter for the album availability
     * @return boolean value album availability
     */
    public boolean getAvailable(){
        return isAvailable;
    }

    /**
     * Getter for release date
     * @return release date of album
     */
    public Date getReleaseDate(){
        return releaseDate;
    }

    /**
     * Getter for genre
     * @return genre enum value
     */
    public Genre getGenre(){return genre;}

    /**
     * Getter for title
     * @return title of album
     */
    public void setAvailable(){isAvailable = !isAvailable;}

    /**
     * Represents each value in Genre enum with a number in alphabetical order.
     * These values are used for comparison purposes.
     * @return int value of enum constant
     */
    public int genreOrder(){
        switch(genre){
            case Classical:
                return 1;
            case Country:
                return 2;
            case Jazz:
                return 3;
            case Pop:
                return 4;
            default:
                return 5;
        }
    }

    /**
     * Checks if two albums are equal by title and artist.
     * @param obj the other album that will be compared to.
     * @return true if Albums are equal, false if not.
     */
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

    /**
     * Returns information of the Album in the following format:
     * title::artist::genre::release date::availability
     * Example: Donda::Kanye West::08/29/2021::is available
     * @return String representation of Album data
     */
    @Override
    public String toString() {
        return title + "::" + artist + "::"+ genre + "::" + releaseDate.toString() + "::"
                + (isAvailable ? "is available" : "is not available");
    }

}
