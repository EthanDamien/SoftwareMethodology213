package project_2;

public class NonResident extends Student{

    public NonResident(String name, String major, int credits){
        super(name, major, credits);
    }

    @Override
    public String toString(){
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:" + getTuition()
                + ":last payment:" + getLastPaymentAmount() + ":payment date: " + getLastPaymentDate().toString()
                + ":non-resident";
    }
}
