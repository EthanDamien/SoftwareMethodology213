package project_2;

/**
 * The subclass that represents an international student
 * A subclass of NonResident
 * @author Kevin Cubillos, Ethan Chang
 */
public class International extends NonResident{

    /** tracks if international student is studying abroad */
    private boolean studyingAbroad;

    /** additional fee for international students */
    private static final int ADD_FEE = 2650;
    /** minimum amount of credits an international student can take */
    private static final int MIN_CREDITS = 12;

    /**
     * Constructor of an International where name, major, credits, and abroad status is known
     * @param name the name of student
     * @param major the major of student
     * @param credits the amount of credits
     * @param abroad studying abroad status
     */
    public International(String name, String major, int credits, boolean abroad){
        super(name, major, credits);
        studyingAbroad = abroad;
    }

    /**
     * Updates international student that is now studying abroad
     * Sets tuition to 0 and credits to 12 and then recalculates tuition
     */
    public void setStudyingAbroad() {
        if(getCredits() > MIN_CREDITS){
            setCredits(MIN_CREDITS);
        }
        setDate(null);
        setTotalPayment(0);
        setTuition(0);
        studyingAbroad = true;
        tuitionDue();
    }

    /**
     * Computes tuition due for an international student
     */
    @Override
    public void tuitionDue(){
        if(studyingAbroad){
            setTuition(UNI_FEE + ADD_FEE);
        }
        else{
            int credits = getCredits();
            int excess = credits > CREDIT_EXCEED ? credits % CREDIT_EXCEED * CREDIT_HOUR : 0;
            setTuition(FULL_TUIT + excess +  UNI_FEE + ADD_FEE);
        }
    }

    /**
     * Makes a string representation of an International
     * @return the string representation
     */
    @Override
    public String toString(){
        String date = getLastPaymentDate() == null ? "--/--/--" : getLastPaymentDate().toString();
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:"
                + Student.DECIMAL_FORMAT.format(getTuition())  + ":total payment:"
                + Student.DECIMAL_FORMAT.format(getTotalPayment())
                + ":payment date: " + date + ":non-resident:international" + (studyingAbroad ? ":study abroad" : "");
    }
}
