package project_1;

import java.util.Locale;

public class Genre {

    public enum GenreName{
        CLASSICAL,
        COUNTRY,
        JAZZ,
        POP,
        UNKNOWN
    }

    private GenreName genre;

    public Genre(String genre){
        genre = genre.toUpperCase(Locale.ROOT);
        if(genre == "CLASSICAL"){
            this.genre = GenreName.CLASSICAL;
        }
        else if(genre == "COUNTRY"){
            this.genre = GenreName.COUNTRY;
        }
        else if(genre == "JAZZ"){
            this.genre = GenreName.JAZZ;
        }
        else if(genre == "POP"){
            this.genre = GenreName.POP;
        }
        else{
            this.genre = GenreName.UNKNOWN;
        }
    }

}
