package project_2;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TuitManager {

    private static final double INVALID_PAY = 0;
    private static final int INVALID_CREDIT = 0;
    private static final int NAME_INDEX = 0;
    private static final int MAJOR_INDEX = 0;
    private Roster roster = new Roster();

    public void run(){
        System.out.println("Tuition Manager starts running.");
        Scanner scan = new Scanner(System.in);
        String currString;
        boolean rerun = true;
        while(rerun){
            currString = scan.nextLine();
            StringTokenizer st = new StringTokenizer(currString, ",");
            rerun = checkInstruction(st);
        }
    }

    private boolean checkInstruction(StringTokenizer st){
        String command;
        try{
            command = st.nextToken();
        }catch (Exception e){
            return true;
        }

        if(command.equals("Q")){
            System.out.println("Tuition Manager terminated.");
            return false;
        }
        if(command.equals("AR") || command.equals("AN") || command.equals("AT") || command.equals("AI") ||
                command.equals("T") || command.equals("S") || command.equals("F") || command.equals("R")){
            String[] profile = validProfile(st);
            if(profile == null){
                return true;
            }
            switch (command) {
                case "T" -> {
                    payTuition(st, profile);
                    return true;
                }
                case "F" -> {
                    setFinancialAid(st, profile);
                    return true;
                }
                case "S" -> {
                    setAbroadStatus(profile);
                    return true;
                }
                case "R" -> {
                    removeStudent(profile);
                    return true;
                }
            }
            int credits = validCredit(st, command.equals("AI"));
            if(credits == INVALID_CREDIT){
                return true;
            }
            switch (command) {
                case "AR" -> addResident(profile, credits);
                case "AN" -> addNonResident(profile, credits);
                case "AT" -> addTriState(st, profile, credits);
                case "AI" -> addInternational(st, profile, credits);
            }
        }
        else if(command.equals("C")){
            roster.calculate();
            System.out.println("Calculation completed.");
        }
        else if(command.equals("P")){
            printRoster(1);
        }
        else if(command.equals("PN")){
            printRoster(2);
        }
        else if(command.equals("PT")){
            printRoster(3);
        }
        else{
            System.out.println("Command '" + command + "' not supported!");
        }
        return true;
    }

    private String[] validProfile(StringTokenizer st){
        String name;
        String major;
        try{
            name = st.nextToken();
            major = validMajor(st.nextToken());
        }catch (NoSuchElementException e){
            System.out.println("Missing data in command line.");
            return null;
        }

        if(major == null){
            return null;
        }
        return new String[]{name, major};
    }

    private String validMajor(String major){
        String caseMajor = major.toUpperCase();
        if(caseMajor.equals("CS") || caseMajor.equals("IT") || caseMajor.equals("BA") ||
                caseMajor.equals("EE") || caseMajor.equals("ME")){
            return caseMajor;
        }
        System.out.println("'" + major + "' is not a valid major.");
        return null;
    }

    private int validCredit(StringTokenizer st, boolean isInternational){
        int credits;
        try{
            credits = Integer.parseInt(st.nextToken());
        }catch(NoSuchElementException e1){
            System.out.println("Credit hours missing.");
            return INVALID_CREDIT;
        }catch (NumberFormatException e2){
            System.out.println("Invalid credit hours.");
            return INVALID_CREDIT;
        }
        if(credits < 0){
            System.out.println("Credit hours cannot be negative.");
            return INVALID_CREDIT;
        }
        if(credits > Student.MAX_CREDITS){
            System.out.println("Credit hours exceed the maximum 24.");
            return INVALID_CREDIT;
        }
        if(credits < Student.MIN_CREDITS){
            System.out.println("Minimum credit hours is 3.");
            return INVALID_CREDIT;
        }
        if(credits < Student.MIN_FULL_TIME && isInternational){
            System.out.println("International students must enroll at least 12 credits.");
            return INVALID_CREDIT;
        }
        return credits;
    }

    private void addResident(String[] profile, int credits){
        Resident res = new Resident(profile[NAME_INDEX], profile[MAJOR_INDEX], credits);
        boolean added = roster.add(res);
        if(added){
            System.out.println("Student added.");
        }
        else{
            System.out.println("Student is already in the roster.");
        }

    }

    private void addNonResident(String[] profile, int credits){
        NonResident nonRes = new NonResident(profile[NAME_INDEX], profile[MAJOR_INDEX], credits);
        boolean added = roster.add(nonRes);
        if(added){
            System.out.println("Student added.");
        }
        else{
            System.out.println("Student is already in the roster.");
        }
    }

    private void addInternational(StringTokenizer st, String[] profile, int credits){
        boolean abroad = st.nextToken().equalsIgnoreCase("TRUE");
        International intern = new International(profile[NAME_INDEX], profile[MAJOR_INDEX], credits, abroad);
        boolean added = roster.add(intern);
        if(added){
            System.out.println("Student added.");
        }
        else{
            System.out.println("Student is already in the roster.");
        }
    }

    private void addTriState(StringTokenizer st, String[] profile, int credits){
        String state;
        try {
            state = validTriState(st.nextToken());
        }catch (NoSuchElementException e){
            System.out.println("Missing data in command line.");
            return;
        }
        if(state == null){
            return;
        }
        TriState tri = new TriState(profile[NAME_INDEX], profile[MAJOR_INDEX], credits, state);
        boolean added = roster.add(tri);
        if(added){
            System.out.println("Student added.");
        }
        else{
            System.out.println("Student is already in the roster.");
        }
    }

    private String validTriState(String state){
        state = state.toUpperCase();
        if(state.equals("NY") || state.equals("CT")){
            return state;
        }
        System.out.println("Not part of the tri-state area.");
        return null;
    }

    private void payTuition(StringTokenizer st, String[] profile){
        double payment;
        try{
            payment = Double.parseDouble(st.nextToken());
        }catch (NoSuchElementException e){
            System.out.println("Payment amount missing.");
            return;
        }

        Student temp = new Student(profile[NAME_INDEX], profile[MAJOR_INDEX]);
        Student found = roster.getAStudent(temp);

        if(found == null){
            System.out.println("Student not in the roster.");
            return;
        }
        if(payment > found.getTuition()){
            System.out.println("Amount is greater than amount due.");
            return;
        }
        if(payment <= INVALID_PAY){
            System.out.println("Invalid amount.");
            return;
        }

        Date date = new Date(st.nextToken());

        if(!date.isValid()){
            System.out.println("Payment date invalid.");
            return;
        }
        found.makePayment(payment, date);
        System.out.println("Payment applied.");
    }

    private void setFinancialAid(StringTokenizer st, String[] profile){
        double aid;
        try {
            aid = Double.parseDouble(st.nextToken());
        }catch (NoSuchElementException e){
            System.out.println("Missing the amount.");
            return;
        }
        Student temp = new Student(profile[NAME_INDEX], profile[MAJOR_INDEX]);
        Student found = roster.getAStudent(temp);
        if(found == null){
            System.out.println("Student not in the roster.");
            return;
        }
        if(!isValidFinancialAid(aid)){
            return;
        }
        if(found.getCredits() < Student.MIN_FULL_TIME){
            System.out.println("Parttime student doesn't qualify for the award.");
            return;
        }
        if(!(found instanceof Resident)){
            System.out.println("Not a resident student.");
            return;
        }
        if(!((Resident) found).giveFinancialAid(aid)){
            System.out.println("Awarded once already.");
            return;
        }
        System.out.println("Tuition updated.");
    }

    private boolean isValidFinancialAid(double financialAid){
        if(financialAid <= 0 || financialAid > Resident.MAX_AID){
            System.out.println("Invalid amount.");
            return false;
        }
        return true;
    }

    private void setAbroadStatus(String[] profile){
        Student temp = new Student(profile[NAME_INDEX], profile[MAJOR_INDEX]);
        Student found = roster.getAStudent(temp);
        if(found == null){
            System.out.println("Couldn't find the international student.");
            return;
        }
        ((International) found).setStudyingAbroad();
        System.out.println("Tuition updated.");
    }

    private void removeStudent(String[] profile){
        Student temp = new Student(profile[NAME_INDEX], profile[MAJOR_INDEX]);
        if(roster.remove(temp)){
            System.out.println("Student removed from the roster.");
        }
        else{
            System.out.println("Student is not in the roster.");
        }
    }

    private void printRoster(int type){
        if(roster.getSize() == Roster.EMPTY){
            System.out.println("Student roster is empty!");
            return;
        }
        switch (type) {
            case 1 -> {
                System.out.println("* list of students in the roster **");
                roster.print();
            }
            case 2 -> {
                System.out.println("* list of students ordered by name **");
                roster.printByName();
            }
            case 3 -> {
                System.out.println("* list of students made payments ordered by payment date **");
                roster.printByPaymentDate();
            }
            default -> {
            }
        }
        System.out.println("* end of roster **");
    }


}
