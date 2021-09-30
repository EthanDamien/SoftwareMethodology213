package project_2;


public class Student {

    private Profile profile;
    private int credits;
    private Date lastPaymentDate;
    private double lastPaymentAmount;
    private double tuition;

    public Student(String name, String major, int credits){
        profile = new Profile(name, major);
        this.credits = credits;
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

    public double getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }


    @Override
    public String toString(){
        return profile.toString() + ":" + credits +  " credit hours:tuition due:" + tuition
                + ":last payment:" + lastPaymentAmount + ":payment date: " + lastPaymentDate.toString();
    }
}
