package project_2;

public class Roster {

    private Student[] roster;
    private int size;
    private static final int NOT_FOUND = -1;

    private int find(Student student){
        for(int i = 0; i < size; i++){
            if(roster[i].getProfile().equals(student.getProfile())){
                return i;
            }
        }
        return NOT_FOUND;
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
        return true;
    }

    public boolean setAbroadStatus(Student student, String isAbroad){
        return true;
    }


    public void print(){

    }

    public void printByName(){

    }

    public void printByPaymentDate(){

    }


}
