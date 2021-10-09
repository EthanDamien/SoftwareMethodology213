package project_2;

public class NonResident extends Student{

    public static final int CREDIT_HOUR = 966;
    public static final int FULL_TUIT = 29737;

    public NonResident(String name, String major, int credits){
        super(name, major, credits);
    }

    @Override
    public void tuitionDue() {
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
        String date = getLastPaymentDate() == null ? "--/--/--" : getLastPaymentDate().toString();

        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due: "
                + Student.DECIMAL_FORMAT.format(getTuition()) + ":total payment:"
                + Student.DECIMAL_FORMAT.format(getTotalPayment())
                + ":payment date: " + date + ":non-resident";
    }
}
