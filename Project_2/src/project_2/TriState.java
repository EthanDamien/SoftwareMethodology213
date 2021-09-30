package project_2;

public class TriState extends NonResident{
    private String state;

    public TriState(String name, String major, int credits, String state){
        super(name, major, credits);
        this.state = state;
    }

    @Override
    public String toString(){
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:" + getTuition()
                + ":last payment:" + getLastPaymentAmount() + ":payment date: " + getLastPaymentDate().toString()
                + ":non-resident(tri-state):" + state;
    }
}
