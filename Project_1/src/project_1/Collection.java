package project_1;

/**
 * The class that represents a collection of albums with accompanying methods to update the collection.
 * @author Ethan Chang, Kevin Cubillos
 */
public class Collection {
    private Album[] albums;
    private int numAlbums;
    private static final int NOT_FOUND = -1;


    /**
     * Basic constructor that initializes the list of Albums to size 4.
     */
    public Collection(){
        albums = new Album[4];
    }

    /**
     * Search for an album in the collection using Sequential Search
     * @param album target album
     * @return index of target album, NOT_FOUND (-1) otherwise
     */
    private int find(Album album) {
        for(int i = 0; i < numAlbums - 1; i++){
            if(album.equals(albums[i])){
                return i;
            }
        }

        return NOT_FOUND;
    } //find the album index, or return NOT_FOUND

    /**
     * Increase the size of the collection by 4 whenever current capacity is reached.
     */
    private void grow() {
        Album[] temp = new Album[numAlbums + 4];
        for(int i  = 0; i < numAlbums - 1; i++){
            temp[i] = albums[i];
        }
        albums = temp;
    }

    /**
     * Adds an album if it does not exist in the collection already.
     * The new album is added at the end of the collection.
     * Increase the size of collection if adding album exceeds current capacity.
     * @param album the album to be added.
     * @return true if album is added, false if album is already in collection.
     */
    public boolean add(Album album) {
        if(find(album) != NOT_FOUND){
            return false;
        }
        if(numAlbums / albums.length == 1){
            this.grow();
        }
        albums[numAlbums] = album;
        numAlbums++;
        return true;
    }

    /**
     * Deletes an album if it exists in the collection.
     * Shift all albums in collection after deleted album to the left by one.
     * @param album the target album
     * @return true if album was deleted, false if album does not exist.
     */
    public boolean remove(Album album) {

        int i = find(album);
        if(i == NOT_FOUND){
            return false;
        }
        for(int j = i; i < numAlbums - 2; i++){
            albums[i] = albums[i++];
        }
        albums[numAlbums-1] = null;
        numAlbums--;
        return true;
    }

    /**
     * Lends out an album, updating availability to not available
     * @param album the target album
     * @return true if album was lent out, false if album does not exist.
     */
    public boolean lendingOut(Album album) {
        int index = find(album);
        if(index == NOT_FOUND){
            return false;
        }
        if(albums[index].getAvailable()){
            albums[index].setAvailable();
        }

        return true;
    } //set to not available

    /**
     * Returns an album to the collection, updating availability to is available.
     * @param album the target album.
     * @return returns true if album is returned, false if album does not exist.
     */
    public boolean returnAlbum(Album album) {
        int index = find(album);
        if(index == NOT_FOUND){
            return false;
        }

        if(!albums[index].getAvailable()){
            albums[index].setAvailable();
        }
        return true;
    }

    /**
     * Prints all albums in collection in no particular order.
     */
    public void print() {
        for(Album entry: albums){
            System.out.println(entry.toString());
        }
    }

    /**
     * Prints all albums in collection in order of release dates.
     * Ordered from oldest to youngest.
     * Uses MergeSort to sort a copy of the collection.
     */
    public void printByReleaseDate() {
        //copy the Album to a new sorted Album list
        Album[] sortedAlbums = new Album[numAlbums];

        for(int i = 0; i < numAlbums; i++){
            sortedAlbums[i] = albums[i];
        }
        mergeSort(sortedAlbums, 0, numAlbums-1, 1);

        for(Album entry: sortedAlbums){
            System.out.println(entry.toString());
        }


    }

    /**
     * Prints all albums in collection in order of genre (alphabetically).
     * Uses MergeSort to sort a copy of the collection.
     */
    public void printByGenre() {
        Album[] sortedAlbums = new Album[numAlbums];

        for(int i = 0; i < numAlbums; i++){
            sortedAlbums[i] = albums[i];
        }
        //This is where we sort by Genre
        mergeSort(sortedAlbums, 0, numAlbums-1, 2);

        for(Album entry: sortedAlbums){
            System.out.println(entry.toString());
        }
    }

    /**
     * Merge Sort algorithm to sort collections based on order.
     * @param temp copy of album collection to be sorted
     * @param l left index of current sub-array
     * @param r right index of current sub-array
     * @param order type of order for sort: 1 for release dates, 2 for genre
     */
    private void mergeSort(Album[] temp, int l, int r, int order){
        if(l >= r){
            return;
        }
        int m = l + (r-l)/2;
        mergeSort(temp, l, m, order);
        mergeSort(temp,m + 1, r, order);


        // 1 for Date, 2 for Genre;
        if(order == 1){
            mergeDate(temp, l, m, r);
        }
        else{
            mergeGenre(temp, l, m, r);
        }
    }

    /**
     * Merge Algorithm of MergeSort where release dates are compared.
     * @param temp copy of album collection to be sorted
     * @param l left index of current sub-array
     * @param m middle index of current sub-array
     * @param r right index of current sub-array
     */
    private void mergeDate(Album[] temp, int l, int m, int r){
        int leftP = l;
        int rightP = m+1;
        int i = 0;
        while(i < r - l + 1){
            int comp = albums[leftP].getReleaseDate().compareTo(albums[rightP].getReleaseDate());
            if(comp == 1){
                temp[i] = albums[rightP];
                rightP++;
            }
            else{
                temp[i] = albums[leftP];
                leftP++;
            }

            i++;
            //When left subarray has been traversed
            if(leftP > m){
                for(int j = rightP; j <= r; j++){
                    temp[j] = albums[j];
                }
                break;
            }
            // When right subarray has been traversed
            else if(rightP > r){
                for(int j = leftP; j <= m; j++){
                    temp[j] = albums[j];
                }
                break;
            }
        }
    }

    /**
     * Merge Algorithm of MergeSort where genres are compared.
     * @param temp copy of album collection to be sorted
     * @param l left index of current sub-array
     * @param m middle index of current sub-array
     * @param r right index of current sub-array
     */
    private void mergeGenre(Album[] temp, int l, int m, int r){
        int leftP = l;
        int rightP = m+1;
        int i = 0;
        while(i < r - l + 1){
            int genreL = temp[leftP].genreOrder();
            int genreR = temp[rightP].genreOrder();
            if(genreL > genreR){
                temp[i] = albums[rightP];
                rightP++;
            }
            else{
                temp[i] = albums[leftP];
                leftP++;
            }

            i++;
            //When left subarray has been traversed
            if(leftP > m){
                for(int j = rightP; j <= r; j++){
                    temp[j] = albums[j];
                }
                break;
            }
            // When right subarray has been traversed
            else if(rightP > r){
                for(int j = leftP; j <= m; j++){
                    temp[j] = albums[j];
                }
                break;
            }
        }
    }
}