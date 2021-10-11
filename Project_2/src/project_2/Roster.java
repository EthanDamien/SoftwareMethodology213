package project_2;

/**
 * A class that represents a Roster of Students.
 * @author Kevin Cubillos, Ethan Chang
 */
public class Roster {
    /** The list of students for the roster. */
    private Student[] roster;
    /** The number of students in the array (neglects null elements). */
    private int size;
    /** represents a student could not be found. */
    private static final int NOT_FOUND = -1;
    /** represents when two students have the same name. */
    private static final int SAME_NAME = 0;
    /** represents when the roster is empty. */
    public static final int EMPTY = 0;

    /**
     * Basic constructor that sets the capacity of roster to 4.
     */
    public Roster(){
        roster = new Student[4];
        size = 0;
    }

    /**
     * Getter for current size of roster.
     * @return size of roster.
     */
    public int getSize() {
        return size;
    }

    /**
     * Find a student based on name and major.
     * @param student the target student.
     * @return the index of target student.
     */
    private int find(Student student){
        for(int i = 0; i < size; i++){
            if(roster[i].getProfile().equals(student.getProfile())){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Finds a student and returns it.
     * This method returns.
     * @param student the target student.
     * @return the target student, null if not found.
     */
    public Student getAStudent(Student student){
        int index = find(student);
        if(index != NOT_FOUND){
            return roster[index];
        }
        return null;
    }

    /**
     * Updates capacity of roster by increasing it by 4.
     */
    private void grow(){
        Student[] temp = new Student[size+4];
        for(int i = 0; i < size; i++){
            temp[i] = roster[i];
        }

        roster = temp;
    }

    /**
     * Adds a student to the roster.
     * @param student the new student being added.
     * @return true if student was added, false if student already exists.
     */
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

    /**
     * Removes a student from the roster.
     * Keeps the order of students the same after the deletion happens.
     * @param student the target student.
     * @return true if student was deleted, false if student does not exist.
     */
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

    /**
     * Calculates the tuition that is due for each student.
     */
    public void calculate() {

        for(int i = 0; i < size; i++){
            if(roster[i].getTuition() == 0 && roster[i].getLastPaymentDate() == null) {
                roster[i].tuitionDue();
            }
        }

    }


    /**
     * Prints all students in roster.
     */
    public void print(){

        for(int i = 0; i < size; i++){
            System.out.println(roster[i].toString());
        }
    }

    /**
     * Prints all students by name in alphabetical order.
     */
    public void printByName(){

        mergeSort(roster, 0, size-1, 2);

        for(int i = 0; i < size; i++){
            System.out.println(roster[i].toString());
        }

    }

    /**
     * Prints all students in order from oldest to youngest latest payment date.
     * Includes only students who made at least one payment.
     */
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

    /**
     * Merge Sort algorithm to sort collections based on order.
     * @param temp roster to be sorted.
     * @param l left index of current sub-array.
     * @param r right index of current sub-array.
     * @param order type of order for sort: 1 for payment date, 2 for name.
     */
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

    /**
     * Merge Algorithm of MergeSort where genres are compared.
     * @param temp roster to be sorted.
     * @param l left index of current sub-array.
     * @param m middle index of current sub-array.
     * @param r right index of current sub-array.
     */
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

    /**
     * Merge Algorithm of MergeSort where release dates are compared.
     * @param temp a temp sub-array of roster to be sorted.
     * @param l left index of current sub-array.
     * @param m middle index of current sub-array.
     * @param r right index of current sub-array.
     */
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
