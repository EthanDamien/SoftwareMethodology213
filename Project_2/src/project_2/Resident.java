package project_2;

public class Resident extends Student{

    private double financialAid;

    public Resident(String name, String major, int credits){
        super(name, major, credits);
    }

    public void setFinancialAid(double financialAid) {
        this.financialAid = financialAid;
    }

    @Override
    public String toString(){
        //Use DecimalFormat for tuition and last payment fields.
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:" + getTuition()
                + ":last payment:" + getLastPaymentAmount() + ":payment date: " + getLastPaymentDate().toString()
                + ":resident";
    }
}
