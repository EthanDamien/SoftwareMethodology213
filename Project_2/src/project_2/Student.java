package project_2;


public class Student {

    private Profile profile;
    private int credits;
    private Date lastPaymentDate;
    private double totalPayment;
    private double tuition;

    public static final int MIN_FULL_TIME = 12;
    public static final int MAX_CREDITS = 24;
    public static final int CREDIT_EXCEED = 16;
    public static final int UNI_FEE = 3268;
    public static final double PART_TIME_PERCENT = 0.8;

    public Student(String name, String major, int credits){
        profile = new Profile(name, major);
        this.credits = credits;
        tuition = 0;
    }


    public void tuitionDue(){

    }

    public Profile getProfile(){
        return profile;
    }

    public int getCredits(){
        return credits;
    }

    public double getTuition() {
        return tuition;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void clearDate(){
        lastPaymentDate = null;
    }

    public void clearTotalPayment(){
        totalPayment = 0;
    }

    public boolean makePayment(double amount, Date date){
        if(amount > tuition){
            return false;
        }

        tuition = tuition - amount;
        totalPayment += amount;
        lastPaymentDate = date;
        return true;
    }


    @Override
    public String toString(){
        return profile.toString() + ":" + credits +  " credit hours:tuition due:" + tuition
                + ":total payment:" + totalPayment + ":payment date: " + lastPaymentDate.toString();
    }
}
