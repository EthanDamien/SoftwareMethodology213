package project_2;

public class Resident extends Student{

    private double financialAid;

    public static final int CREDIT_HOUR = 404;
    public static final int FULL_TUIT = 12536;


    public Resident(String name, String major, int credits){
        super(name, major, credits);
    }

    public void setFinancialAid(double financialAid) {
        this.financialAid = financialAid;
    }

    public boolean giveFinancialAid(int financialAid){
        if(financialAid != 0){
            return false;
        }
        this.financialAid = financialAid;
        setTuition(getTuition() - financialAid);
        return true;
    }

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

    @Override
    public String toString(){
        //Use DecimalFormat for tuition and last payment fields.
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:" + getTuition()
                + ":last payment:" + getTotalPayment() + ":payment date: " + getLastPaymentDate().toString()
                + ":resident";
    }
}
