package project_2;

/**
 * The subclass that represents a non-resident student
 * A subclass of Student
 * Also a superclass of TriState and International
 * @author Kevin Cubillos, Ethan Chang
 */
public class NonResident extends Student{

    /** The credit per hour charge */
    public static final int CREDIT_HOUR = 966;
    /** The full tuition of a non-resident student */
    public static final int FULL_TUIT = 29737;

    /**
     * Constructor of a NonResident where name, major, and credits is known
     * @param name the name of student
     * @param major the major of student
     * @param credits the amount of credits
     */
    public NonResident(String name, String major, int credits){
        super(name, major, credits);
    }

    /**
     * Computes tuition due for a non-resident student
     */
    @Override
    public void tuitionDue() {
        int credits = getCredits();
        int whichTime;
        double fee;
        int excess = credits > CREDIT_EXCEED ? credits % CREDIT_EXCEED * CREDIT_HOUR : 0;
        if(credits >= MIN_FULL_TIME){
            whichTime = FULL_TUIT;
            fee = UNI_FEE;
        }
        else{
            whichTime = credits * CREDIT_HOUR;
            fee = UNI_FEE * PART_TIME_PERCENT;
        }

        setTuition(whichTime + excess + fee);
    }

    /**
     * Makes a string representation of a NonResident
     * @return the string representation
     */
    @Override
    public String toString(){
        String date = getLastPaymentDate() == null ? "--/--/--" : getLastPaymentDate().toString();

        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:"
                + Student.DECIMAL_FORMAT.format(getTuition()) + ":total payment:"
                + Student.DECIMAL_FORMAT.format(getTotalPayment())
                + ":last payment date: " + date + ":non-resident";
    }
}
