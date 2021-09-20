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
        mergeSort(sortedAlbum, 0, numAlbums-1, 1);

        for(int i = 0; i < numAlbums; i++){
            System.out.println(sortedAlbum[i].toString());
        }
        //This is where we sort by Release Date, but not in alphabetical order

    }

    public void printByGenre() {
        Album[] sortedAlbum = new Album[numAlbums];
//        System.arraycopy(albums, 0, sortedAlbum, 0, numAlbums);
        //This is where we sort by Genre

    }

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

    private void mergeGenre(Album[] temp, int l, int m, int r){
        //IDK HOW TO COMPARE GENRES RIGHT NOW CAUSE IDK HOW TO SET UP ENUM CLASS
    }
}