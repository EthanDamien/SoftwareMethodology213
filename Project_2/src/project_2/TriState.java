package project_2;

public class TriState extends NonResident{
    private String state;

    public TriState(String name, String major, int credits, String state){
        super(name, major, credits);
        this.state = state;
    }

    @Override
    public void tuitionDue(){
        int credits = getCredits();
        int whichTime;
        int triDiscount;
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
        if(state.equals("CT")){
            triDiscount = 5000;
        }
        else{
            triDiscount = 4000;
        }
        setTuition(whichTime + excess + fee - triDiscount);
    }

    @Override
    public String toString(){
        String date = getLastPaymentDate() == null ? "--/--/--" : getLastPaymentDate().toString();
        return getProfile().toString() + ":" + getCredits() +  " credit hours:tuition due:"
                + Student.DECIMAL_FORMAT.format(getTuition()) + ":total payment:"
                + Student.DECIMAL_FORMAT.format(getTotalPayment())
                + ":payment date: " + date + ":non-resident(tri-state):" + state;
    }
}
