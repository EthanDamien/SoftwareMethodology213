package project_1;

public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection


    private int find(Album album) {
        return 0;
    } //find the album index, or return NOT_FOUND

    private void grow() {
    } //increase the capacity of the array list by 4

    public boolean add(Album album) {
        return false;
    }

    public boolean remove(Album album) {
        return false;
    }

    public boolean lendingOut(Album album) {
        return false;
    } //set to not available

    public boolean returnAlbum(Album album) {
        return false;
    } //set to available

    public void print() {

    } //display the list without specifying the order

    public void printByReleaseDate() {
    }

    public void printByGenre() {
    }
}