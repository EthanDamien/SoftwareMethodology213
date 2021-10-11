package project_2;

/**
 * The subclass that represents a resident student
 * A subclass of Student
 * @author Kevin Cubillos, Ethan Chang
 */
public class Resident extends Student{

    /** The financial aid a resident student has */
    private double financialAid;

    /** The credit per hour charge */
    private static final int CREDIT_HOUR = 404;
    /** The full tuition of a resident student */
    private static final int FULL_TUIT = 12536;
    public static final int MAX_AID = 10000;

    /**
     * Constructor of a Resident where name, major, and credits is known
     * @param name the name of student
     * @param major the major of student
     * @param credits the amount of credits
     */
    public Resident(String name, String major, int credits){
        super(name, major, credits);
    }

    /**
     * Sets the financial aid of a student and updates tuition
     * @param aid amount of finanical aid
     * @return true if aid was given, false if aid was already given before
     */
    public boolean giveFinancialAid(double aid){
        if(financialAid != 0){
            return false;
        }
        financialAid = aid;
        setTuition(getTuition() - financialAid);
        return true;
    }

    /**
     * Computes tuition due for a resident student
     */
    @Override
    public void tuitionDue(){
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
     * Makes a string representation of a Resident
     * @return the string representation
     */
    @Override
    public String toString(){
        String date = getLastPaymentDate() == null ? "--/--/--" : getLastPaymentDate().toString();
        double tuit = getTuition() < 0 ? 0 : getTuition();
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:"
                + Student.DECIMAL_FORMAT.format(tuit) + ":total payment:"
                + Student.DECIMAL_FORMAT.format(getTotalPayment())
                + ":last payment date: " + date + ":resident" + ((financialAid == 0) ? "" :
                ":financial aid $" + Student.DECIMAL_FORMAT.format(financialAid));
    }
}
