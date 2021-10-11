//package project_2;
//
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//public class TuitManager {
//
//    private Roster roster = new Roster();
//
//    public void run(){
//        System.out.println("Tuition Manager starts running.");
//        Scanner scan = new Scanner(System.in);
//        String currString;
//        boolean rerun = true;
//        while(rerun){
//            currString = scan.nextLine();
//            StringTokenizer st = new StringTokenizer(currString, ",");
//            rerun = checkInstruction(st);
//        }
//    }
//
//    private boolean checkInstruction(StringTokenizer st){
//        String command = "";
//        try{
//            command = st.nextToken();
//        }catch (Exception e){
//            return true;
//        }
//
//        if(command.equals("Q")){
//            System.out.println("Tuition Manager terminated.");
//            return false;
//        }
//        if(command.equals("AR") || command.equals("AN") || command.equals("AT") || command.equals("AI") ||
//        command.equals("T") || command.equals("S") || command.equals("F") || command.equals("R")){
//            String[] profile = validProfile(st, command.equals("AI"));
//            if(profile == null){
//                return true;
//            }
//            switch (command){
//
//            }
//            int credits = validCredit(st, command.equals("AI"));
//            if(credits == 0){
//                return true;
//            }
//            switch (command){
//                case "AR":
//                    addResident(profile, credits);
//                    break;
//                case "AN":
//                    addNonResident(profile, credits);
//                    break;
//                case "AT":
//                    addTriState(st, profile, credits);
//                    break;
//                case "AI":
//                    addInternational(st, profile, credits);
//                    break;
//            }
//        }
//        else if(command.equals("C")){
//            roster.calculate();
//            System.out.println("Calculation completed.");
//        }
//    }
//
//    private String[] validProfile(StringTokenizer st, boolean isInternational){
//        String name = null;
//        String major = null;
//        try{
//            name = st.nextToken();
//            major = validMajor(st.nextToken());
//        }catch (NoSuchElementException e){
//            System.out.println("Missing data in command line.");
//            return null;
//        }
//
//        if(major == null){
//            return null;
//        }
//        String[] profile = {name, major};
//        return profile;
//    }
//
//    private String validMajor(String major){
//        String caseMajor = major.toUpperCase();
//        if(major.equals("CS") || major.equals("IT") || major.equals("BA") || major.equals("EE") || major.equals("ME")){
//            return caseMajor;
//        }
//        System.out.println("'" + major + "' is not a valid major");
//        return null;
//    }
//
//    private int validCredit(StringTokenizer st, boolean isInternational){
//        int credits = 0;
//        try{
//            credits = Integer.parseInt(st.nextToken());
//        }catch(NoSuchElementException e1){
//            System.out.println("Credit hours missing.");
//            return 0;
//        }catch (NumberFormatException e2){
//            System.out.println("Invalid credit hours.");
//            return 0;
//        }
//        if(credits < 0){
//            System.out.println("Credit hours cannot be negative.");
//            return 0;
//        }
//        if(credits > Student.MAX_CREDITS){
//            System.out.println("Credit hours exceed the maximum 24.");
//            return 0;
//        }
//        if(credits < Student.MIN_CREDITS){
//            System.out.println("Minimum credit hours is 3.");
//        }
//        if(credits < Student.MIN_FULL_TIME && isInternational){
//            System.out.println("International students must enroll at least 12 credits.");
//            return 0;
//        }
//        return credits;
//    }
//
//    private void addResident(String[] profile, int credits){
//        Resident res = new Resident(profile[0], profile[1], credits);
//        boolean added = roster.add(res);
//        if(added){
//            System.out.println("Student added.");
//        }
//        else{
//            System.out.println("Student is already in roster");
//        }
//
//    }
//
//    private void addNonResident(String[] profile, int credits){
//        NonResident nonRes = new NonResident(profile[0], profile[1], credits);
//        boolean added = roster.add(nonRes);
//        if(added){
//            System.out.println("Student added.");
//        }
//        else{
//            System.out.println("Student is already in roster");
//        }
//    }
//
//    private void addInternational(StringTokenizer st, String[] profile, int credits){
//        boolean abroad = st.nextToken().toUpperCase().equals("TRUE");
//        International intern = new International(profile[0], profile[1], credits, abroad);
//        boolean added = roster.add(intern);
//        if(added){
//            System.out.println("Student added.");
//        }
//        else{
//            System.out.println("Student is already in roster");
//        }
//    }
//
//    private void addTriState(StringTokenizer st, String[] profile, int credits){
//        String state = validTriState(st.nextToken());
//        if(state == null){
//            return;
//        }
//        TriState tri = new TriState(profile[0], profile[1], credits, state);
//        boolean added = roster.add(tri);
//        if(added){
//            System.out.println("Student added.");
//        }
//        else{
//            System.out.println("Student is already in roster");
//        }
//    }
//
//    private String validTriState(String state){
//        state = state.toUpperCase();
//        if(state.equals("NY") || state.equals("CT")){
//            return state;
//        }
//        System.out.println("Not part of the tri-state area.");
//        return null;
//    }
//}
