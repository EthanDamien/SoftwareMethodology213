package project_2;
import java.text.DecimalFormat;


public class Student {
    /** The profile of the student */
    private Profile profile;
    /** The amount of credits student is taking*/
    private int credits;
    /** The date of the latest payment*/
    private Date lastPaymentDate;
    /** The total payment student has made */
    private double totalPayment;
    /** The tuition student has left to pay */
    private double tuition;

    /** The format when printing money values */
    private static final String FORMAT = "##,##0.00";
    /** Formatter that will format doubles to FORMAT */
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(FORMAT);
    /** The minimum amount of credits for a full-time student */
    public static final int MIN_FULL_TIME = 12;
    /** The max amount of credits any student can take */
    public static final int MAX_CREDITS = 24;
    /** The min amount of credits any student can take */
    public static final int MIN_CREDITS = 3;
    /** Max credits where all credit costs are included in tuition */
    public static final int CREDIT_EXCEED = 16;
    /** University fee */
    public static final int UNI_FEE = 3268;
    /** Percentage of tuition for part-time students */
    public static final double PART_TIME_PERCENT = 0.8;

    /**
     * Constructor of student where name, major, and amount of credits is known
     * Sets tuition to zero by default.
     * @param name name of student
     * @param major major of student
     * @param credits credits student is taking
     */
    public Student(String name, String major, int credits){
        profile = new Profile(name, major);
        this.credits = credits;
        tuition = 0;
    }

    /**
     * Constructor of student where name, and major is known
     * @param name name of student
     * @param major major of student
     */
    public Student(String name, String major){
        profile = new Profile(name, major);
    }

    /**
     * Dummy method that subclasses will override
     */
    public void tuitionDue(){

    }

    /**
     * Getter for profile of student
     * @return the profile
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     * Getter for credits
     * @return amount of credits
     */
    public int getCredits(){
        return credits;
    }

    /**
     * Getter for tuition
     * @return the tuition
     */
    public double getTuition() {
        return tuition;
    }

    /**
     * Getter for date of last payment
     * @return the date
     */
    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * getter for total payment
     * @return the total payment
     */
    public double getTotalPayment() {
        return totalPayment;
    }

    /**
     * setter for tuition
     * @param tuition the new tuition value
     */
    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    /**
     * setter for credits
     * @param credits the new credit amount
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Setter for the date of the latest payment
     * @param date the new date
     */
    public void setDate(Date date){
        lastPaymentDate = date;
    }

    /**
     * Setter for total payment
     * @param amount the new amount
     */
    public void setTotalPayment(double amount){
        totalPayment = amount;
    }

    /**
     * Makes a payment to tuition that is due
     * @param amount amount of payment
     * @param date the date of payment
     */
    public void makePayment(double amount, Date date){
        tuition = tuition - amount;
        totalPayment += amount;
        lastPaymentDate = date;
        System.out.println("Payment Applied");
    }


    /**
     * Makes a string representation of a Student
     * @return the string representation
     */
    @Override
    public String toString(){
        String date = lastPaymentDate == null ? "--/--/--" : lastPaymentDate.toString();
        return profile.toString() + ":" + credits +  " credit hours:tuition due:" + tuition
                + ":total payment:" + totalPayment + ":payment date: " + date;
    }
}
