package project_2;

public class International extends NonResident{

    private boolean studyingAbroad;

    public static final int ADD_FEE = 2650;
    public static final int MIN_CREDITS = 12;

    public International(String name, String major, int credits, boolean abroad){
        super(name, major, credits);
        studyingAbroad = abroad;
    }

    public void setStudyingAbroad() {
        if(getCredits() > MIN_CREDITS){
            setCredits(MIN_CREDITS);
        }
        clearDate();
        clearTotalPayment();
        setTuition(0);
        studyingAbroad = !studyingAbroad;
        tuitionDue();
    }

    @Override
    public void tuitionDue(){
        int tuition = studyingAbroad ? FULL_TUIT : 0;
        setTuition(tuition + UNI_FEE + ADD_FEE);
    }

    @Override
    public String toString(){
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:" + getTuition()
                + ":last payment:" + getTotalPayment() + ":payment date: " + getLastPaymentDate().toString()
                + ":non-resident:international" + (studyingAbroad ? ":study abroad" : "");
    }
}
