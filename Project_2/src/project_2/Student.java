package project_2;

/**
 * Superclass that represents a Student enrolled in the university
 * Has subclasses Resident and NonResident
 * @author Kevin Cubillos, Ethan Chang
 */
public class Student {

    /** Profile of student (contains name and major)*/
    private Profile profile;
    /** Amount of credits student is taking */
    private int credits;
    /** Date of student's latest payment */
    private Date lastPaymentDate;
    /** Total amount of payments student has made */
    private double totalPayment;
    /** Tuition left to pay */
    private double tuition;

    /** Minimum amount of credits for full-time student */
    public static final int MIN_FULL_TIME = 12;
    /** Max amount of credits a student can take */
    public static final int MAX_CREDITS = 24;
    /** Amount of credits included in tuition */
    public static final int CREDIT_EXCEED = 16;
    /** University fee */
    public static final int UNI_FEE = 3268;
    /** Percentage of part-time students for tuition */
    public static final double PART_TIME_PERCENT = 0.8;

    /**
     * Constructor of Student where name, major, and amount of credits is known.
     * Name and major is stored into the Profile.
     * @param name name of student
     * @param major major student is under
     * @param credits amount of credits
     */
    public Student(String name, String major, int credits){
        profile = new Profile(name, major);
        this.credits = credits;
        tuition = 0;
    }

    /**
     * Dummy method for subclasses to override
     */
    public void tuitionDue(){

    }

    /**
     * Getter for profile
     * @return profile of student
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     * getter for credits
     * @return amount of credits student is taking
     */
    public int getCredits(){
        return credits;
    }

    /**
     * getter for tuition
     * @return tuition student has left
     */
    public double getTuition() {
        return tuition;
    }

    /**
     * Getter for last payment date
     * @return Date object of the lastest payment
     */
    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * Getter for total payment
     * @return the amount of all payments made
     */
    public double getTotalPayment() {
        return totalPayment;
    }

    /**
     * Setter for tuition
     * @param tuition amount for tuition to be set to
     */
    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    /**
     * Setter for credits
     * @param credits amount for credits to be set to
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Clears date of last payment (represented as "--/--/--")
     */
    public void clearDate(){
        lastPaymentDate = null;
    }

    /**
     * Clears total payment
     */
    public void clearTotalPayment(){
        totalPayment = 0;
    }

    /**
     * Makes a payment, updating tuition and total payments.
     * @param amount the amount of the payment
     * @param date the date of the payment
     * @return true if payment was made, false if amount is greater than current tuition
     */
    public boolean makePayment(double amount, Date date){
        if(amount > tuition){
            return false;
        }

        tuition = tuition - amount;
        totalPayment += amount;
        lastPaymentDate = date;
        return true;
    }

    /**
     * Represents the Student information as a String
     * @return the String with all information of the Student
     */
    @Override
    public String toString(){
        String date = lastPaymentDate == null ? "--/--/--" : lastPaymentDate.toString();
        return profile.toString() + ":" + credits +  " credit hours:tuition due:" + tuition
                + ":total payment:" + totalPayment + ":payment date: " + date;
    }
}
