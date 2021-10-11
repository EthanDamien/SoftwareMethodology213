package project_2;

/**
 * The subclass that represents a tri-state student
 * A subclass of NonResident
 * @author Kevin Cubillos, Ethan Chang
 */
public class TriState extends NonResident{
    /** The tri-state the student is from */
    private String state;

    /**
     * Constructor of a TriState where name, major, credits, and state is known
     * @param name the name of student
     * @param major the major of student
     * @param credits the amount of credits
     * @param state the tri-state student is from
     */
    public TriState(String name, String major, int credits, String state){
        super(name, major, credits);
        this.state = state;
    }

    /**
     * Computes tuition due for a tri-state student
     */
    @Override
    public void tuitionDue(){
        int credits = getCredits();
        int whichTime;
        int triDiscount = 0;
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
        if(credits >= MIN_FULL_TIME) {
            if (state.equals("CT")) {
                triDiscount = 5000;
            } else {
                triDiscount = 4000;
            }
        }
        setTuition(whichTime + excess + fee - triDiscount);
    }

    /**
     * Makes a string representation of a TriState
     * @return the string representation
     */
    @Override
    public String toString(){
        String date = getLastPaymentDate() == null ? "--/--/--" : getLastPaymentDate().toString();
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:"
                + Student.DECIMAL_FORMAT.format(getTuition()) + ":total payment:"
                + Student.DECIMAL_FORMAT.format(getTotalPayment())
                + ":last payment date: " + date + ":non-resident(tri-state):" + state;
    }
}
