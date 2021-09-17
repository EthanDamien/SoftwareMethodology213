package project_1;

public class Collection {
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection
    private static final int NOT_FOUND = -1;

    public Collection(){
        albums = new Album[4];
    }

    private int find(Album album) {
        for(int i = 0; i < numAlbums - 1; i++){
            if(album.equals(albums[i])){
                return i;
            }
        }

        return NOT_FOUND;
    } //find the album index, or return NOT_FOUND

    private void grow() {
        Album[] temp = new Album[numAlbums + 4];
        for(int i  = 0; i < numAlbums - 1; i++){
            temp[i] = albums[i];
        }
        albums = temp;
    } //increase the capacity of the array list by 4

    public boolean add(Album album) {
        if(numAlbums % 4 == 0){
            this.grow();
        }
        albums[numAlbums] = album;
        numAlbums++;
        return true;
    }

    public boolean remove(Album album) {

        int i = find(album);
        for(int j = i; i < numAlbums - 2; i++){
            albums[i] = albums[i++];
        }
        albums[numAlbums-1] = null;
        numAlbums--;
        return true;
    }

    public boolean lendingOut(Album album) {
        if(album.getAvailable()){
            album.setAvailable();
        }

        return true;
    } //set to not available

    public boolean returnAlbum(Album album) {
        if(!album.getAvailable()){
            album.setAvailable();
        }
        return true;
    } //set to available

    public void print() {
        for(Album entry: albums){
            System.out.println(entry.toString());
        }
    } //display the list without specifying the order

    public void printByReleaseDate() {
        //copy the Album to a new sorted Album list
        Album[] sortedAlbum = new Album[numAlbums];
//        System.arraycopy(albums, 0, sortedAlbum, 0, numAlbums);
        //This is where we sort by Release Date

    }

    public void printByGenre() {
        Album[] sortedAlbum = new Album[numAlbums];
//        System.arraycopy(albums, 0, sortedAlbum, 0, numAlbums);
        //This is where we sort by Genre

    }
}