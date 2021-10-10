package project_2;

public class Roster {

    private Student[] roster;
    private int size;
    private static final int NOT_FOUND = -1;
    private static final int SAME_NAME = 0;
    public static final int EMPTY = 0;

    public Roster(){
        //Hello
        roster = new Student[4];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    private int find(Student student){
        for(int i = 0; i < size; i++){
            if(roster[i].getProfile().equals(student.getProfile())){
                return i;
            }
        }
        return NOT_FOUND;
    }

    public Student makePayment(Student student){
        for(int i = 0; i < size; i++){
            if(roster[i].getProfile().equals(student.getProfile())){
                return roster[i];
            }
        }
        System.out.println("Student Not Found");
        return null;
    }


    private void grow(){
        Student[] temp = new Student[size+4];
        for(int i = 0; i < size; i++){
            temp[i] = roster[i];
        }

        roster = temp;
    }

    public boolean add(Student student){
        if(find(student) != NOT_FOUND){
            return false;
        }
        if(size + 1 == roster.length + 1){
            grow();
        }
        roster[size] = student;
        size++;
        return true;
    }

    public boolean remove(Student student){
        int index = find(student);
        if(index == NOT_FOUND){
            return false;
        }
        for(int j = index; j < size-1; j++){
            roster[j] = roster[j+1];
        }
        roster[size-1] = null;
        size--;
        return true;
    }

    public boolean calculate() {

        if(size == EMPTY){
            return false;
        }

        for(int i = 0; i < size; i++){
            roster[i].tuitionDue();
        }
        return true;
    }

    public boolean setAbroadStatus(Student student){

        int index = find(student);
        if(index == NOT_FOUND){
            return false;
        }

        International intStu = (International) student;
        intStu.setStudyingAbroad();
        return true;
    }


    public void print(){

        for(int i = 0; i < size; i++){
            System.out.println(roster[i].toString());
        }
    }

    public void printByName(){

        mergeSort(roster, 0, size-1, 2);

        for(int i = 0; i < size; i++){
            System.out.println(roster[i].toString());
        }

    }

    public void printByPaymentDate(){
        int count = 0;
        for(int i = 0; i < size; i++) {
            if (roster[i].getTotalPayment() > 0) {
                count++;
            }
        }

        Student[] paid = new Student[count];
        count = 0;
        for(int i = 0; i < size; i++){
            if(roster[i].getTotalPayment() > 0){
                paid[count] = roster[i];
                count++;
            }
        }

        mergeSort(paid, 0, paid.length - 1, 1);
        for (Student student : paid) {
            System.out.println(student.toString());
        }

    }


    private void mergeSort(Student[] temp, int l, int r, int order){
        if(l >= r){
            return;
        }
        int m = l + (r-l)/2;


        mergeSort(temp, l, m, order);
        mergeSort(temp,m + 1, r, order);

        // 1 for Date, 2 for Name;
        if(order == 1){
            mergeDate(temp, l, m, r);
        }
        else{
            mergeName(temp, l, m, r);
        }
    }

    private void mergeName(Student[] temp, int l, int m, int r){
        int sizeL = m - l + 1;
        int sizeR = r - m;

        Student[] L = new Student[sizeL];
        Student[] R = new Student[sizeR];
        for(int i = 0; i < sizeL; i++){
            L[i] = temp[l + i];
        }
        for(int i = 0; i < sizeR; i++){
            R[i] = temp[m + i + 1];
        }
        int leftP = 0;
        int rightP = 0;
        int current = l;

        while(leftP < sizeL && rightP < sizeR){
            int comp = L[leftP].getProfile().getName().compareTo(R[rightP].getProfile().getName());
            if(comp <= SAME_NAME){
                temp[current] = L[leftP];
                leftP++;
            }
            else{
                temp[current] = R[rightP];
                rightP++;
            }
            current++;
        }
        while(leftP < sizeL){
            temp[current] = L[leftP];
            leftP++;
            current++;
        }
        while(rightP < sizeR){
            temp[current] = R[rightP];
            rightP++;
            current++;
        }
    }

    private void mergeDate(Student[] temp, int l, int m, int r){
        int sizeL = m - l + 1;
        int sizeR = r - m;

        Student[] L = new Student[sizeL];
        Student[] R = new Student[sizeR];
        for(int i = 0; i < sizeL; i++){
            L[i] = temp[l + i];
        }
        for(int i = 0; i < sizeR; i++){
            R[i] = temp[m + i + 1];
        }
        int leftP = 0;
        int rightP = 0;
        int current = l;

        while(leftP < sizeL && rightP < sizeR){
            int comp = L[leftP].getLastPaymentDate().compareTo(R[rightP].getLastPaymentDate());
            if(comp >= Date.SAME_DATE){
                temp[current] = L[leftP];
                leftP++;
            }
            else{
                temp[current] = R[rightP];
                rightP++;
            }
            current++;
        }
        while(leftP < sizeL){
            temp[current] = L[leftP];
            leftP++;
            current++;
        }
        while(rightP < sizeR){
            temp[current] = R[rightP];
            rightP++;
            current++;
        }
    }


}
