package project_1;

public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    public Collection(){
        albums = new Album[4];
    }

    private int find(Album album) {
        return 0;
    } //find the album index, or return NOT_FOUND

    private void grow() {
        int currentLength = albums.length;
        //Create new Albums array with 4 more entries
        Album[] newCollection = new Album[albums.length+4];
        //Copy old Album Array contents to new
        System.arraycopy(albums, 0, newCollection, 0, numAlbums);
        albums = newCollection;
    } //increase the capacity of the array list by 4

    public boolean add(Album album) {

        numAlbums++;
        return false;
    }

    public boolean remove(Album album) {

        numAlbums--;
        return false;
    }

    public boolean lendingOut(Album album) {
        return false;
    } //set to not available

    public boolean returnAlbum(Album album) {
        return false;
    } //set to available

    public void print() {
        for(Album entry: albums){
            //print list in specified format
        }
    } //display the list without specifying the order

    public void printByReleaseDate() {
        //copy the Album to a new sorted Album list
        Album[] sortedAlbum = new Album[numAlbums];
        System.arraycopy(albums, 0, sortedAlbum, 0, numAlbums);
        //This is where we sort by Release Date

    }

    public void printByGenre() {
        Album[] sortedAlbum = new Album[numAlbums];
        System.arraycopy(albums, 0, sortedAlbum, 0, numAlbums);
        //This is where we sort by Genre

    }
}