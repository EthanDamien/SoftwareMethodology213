package project_2;

public class International extends NonResident{

    private boolean studyingAbroad;

    private static final int ADD_FEE = 2650;
    private static final int MIN_CREDITS = 12;

    public International(String name, String major, int credits, boolean abroad){
        super(name, major, credits);
        studyingAbroad = abroad;
    }

    public void setStudyingAbroad() {
        if(getCredits() > MIN_CREDITS){
            setCredits(MIN_CREDITS);
        }
        setDate(null);
        setTotalPayment(0);
        setTuition(0);
        studyingAbroad = !studyingAbroad;
        tuitionDue();
    }

    @Override
    public void tuitionDue(){
        int tuition = studyingAbroad ? 0 : FULL_TUIT;
        setTuition(tuition + UNI_FEE + ADD_FEE);
    }

    @Override
    public String toString(){
        String date = getLastPaymentDate() == null ? "--/--/--" : getLastPaymentDate().toString();
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:"
                + Student.DECIMAL_FORMAT.format(getTuition())  + ":total payment:"
                + Student.DECIMAL_FORMAT.format(getTotalPayment())
                + ":payment date: " + date + ":non-resident:international" + (studyingAbroad ? ":study abroad" : "");
    }
}
