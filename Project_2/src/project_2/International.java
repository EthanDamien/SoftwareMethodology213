package project_2;

public class International extends NonResident{

    private boolean studyingAbroad;

    public International(String name, String major, int credits, boolean abroad){
        super(name, major, credits);
        studyingAbroad = abroad;
    }

    @Override
    public String toString(){
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:" + getTuition()
                + ":last payment:" + getLastPaymentAmount() + ":payment date: " + getLastPaymentDate().toString()
                + ":non-resident:international" + (studyingAbroad ? ":study abroad" : "");
    }
}
