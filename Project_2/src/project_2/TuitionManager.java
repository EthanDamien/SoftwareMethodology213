package project_2;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This is the main running class that handles valid inputs and operations based on the requirements given in Project 2.
 * It takes in inputs with the format for adding -> (Instruction, Name, Major, CreditHours, ExtraField (applying to AT and AI).
 *      Add Instruction:
 *          AR -> Add Resident Student
 *          AN -> Add non-Resident Student
 *          AT -> Add tristate student, (extra field allows NY and CT)
 *          AI -> Add international student, (extra field is a boolean, true if they study abroad, false if not)
 *          R -> Remove a resident student from the roster
 * It takes in action instructions.
 *      Action Instructions:
 *          R, name, major -> Removes resident student from the roster
 *          C -> Calculate tuition for all students from the roster
 *          T, Name, Major, Payment, Date -> Pay tuition
 *          S, Name, Major, StudyAbroadStatus -> Set Study abroad status
 *          F, Name, Major, FinancialAid -> Set financial aid amount
 *          P -> print roster as is
 *          PN -> print roster sorted by student names
 *          PT -> print only the students who have made payments, ordered by payment dates.
 *
 * @author Ethan Chang, Kevin Cubillos
 */
public class TuitionManager {
    /** Empty number for all use-cases*/
    private static final int EMPTY_NUM = 0;
    /** Amount of elements for a Resident Command*/
    private static final int RESIDENT_ADD_COMMAND_AMOUNT = 4;
    /** Amount of elements for a Non-Resident Command*/
    private static final int NONRESIDENT_ADD_COMMAND_AMOUNT = 4;
    /** Amount of elements for a TriState Command*/
    private static final int TRISTATE_ADD_COMMAND_AMOUNT = 5;
    /** Amount of elements for a International Command*/
    private static final int INTERNATIONAL_ADD_COMMAND_AMOUNT = 5;
    /** Resident Credit position in command*/
    private static final int RESIDENT_CREDIT_CHECKNUM = 3;
    /** Nonresident Credit position in command*/
    private static final int NONRESIDENT_CREDIT_CHECKNUM = 3;
    /** International Credit position in command*/
    private static final int INTERNATIONAL_CREDIT_CHECKNUM = 3;
    /** Universal Zero Constant*/
    private static final int ZERO = 0;
    /** The maximum amount of aid a student can receive */
    private static final double MAX_AID = 10000;

    private Roster roster = new Roster();
    /** Runs a loop that takes in a certain amount of arguments, it can be terminated by entering Q into the
     * Command Line
     */
    public void run(){
        System.out.println("Tuition Manager starts running.");
        Scanner scan = new Scanner(System.in);
        String currString;
        boolean rerun = true;
        while(rerun){
            currString = scan.nextLine();
            StringTokenizer st = new StringTokenizer(currString, ",");
            String[] input = new String[st.countTokens()];
            int i = 0;
            while(st.hasMoreTokens()){
                input[i] = st.nextToken();
                i++;
            }
            if(input.length != 0)
                rerun = checkInstruction(input);
        }
    }

    /** This checks whether the input is valid, and will call each method that is attributed to each instruction.
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     * @return returns false if instruction is "Q"
     */
    private boolean checkInstruction(String[] input){
        switch (input[0]){
            case "Q":
                System.out.println("Tuition Manager terminated.");
                return false;
            case "AR":
                addResident(input);
                break;
            case "AN":
                addNonResident(input);
                break;
            case "AT":
                addTriState(input);
                break;
            case "AI":
                addInternational(input);
                break;
            case "R":
                removeStudent(input);
                break;
            case "C":
                if(input.length != 1){
                    System.out.println("Invalid Command!");
                }
                calculate();
                break;
            case "T":
                payTuition(input);
                break;
            case "S":
                setAbroadStatus(input);
                break;
            case "F":
                setFinancialAid(input);
                break;
            case "P":
                printRoster(input, 1);
                break;
            case "PN":
                printRoster(input, 2);
                break;
            case "PT":
                printRoster(input, 3);
                break;
            default:
                System.out.println("Command '" + input[0] + "' not supported!");
                break;
        }
        return true;
    }

    /**
     * Add a Resident student to the roster if the input is valid.
     * @param input the input array for the instruction
     */
    private void addResident(String[] input){
        if(input.length == RESIDENT_CREDIT_CHECKNUM){
            System.out.println("Credit hours missing.");
            return;
        }
        if(input.length != RESIDENT_ADD_COMMAND_AMOUNT){
            System.out.println("Missing data in command line.");
            return;
        }
        if(!isValidCreditNum(input[3], false))
            return;
        Resident resident = new Resident(input[1], input[2], Integer.parseInt(input[3]));
        roster.add(resident);
        System.out.println("Student Added.");
    }

    /**
     * Add a Non-Resident student to the roster if the input is valid.
     * @param input the input array for the instruction
     */
    private void addNonResident(String[] input){
        if(input.length == NONRESIDENT_CREDIT_CHECKNUM){
            System.out.println("Credit hours missing.");
            return;
        }
        if(input.length != NONRESIDENT_ADD_COMMAND_AMOUNT){
            System.out.println("Missing data in command line.");
            return;
        }
        if(!isValidCreditNum(input[3], false))
            return;
        NonResident nonresident = new NonResident(input[1], input[2], Integer.parseInt(input[3]));
        roster.add(nonresident);
        System.out.println("Student Added.");
    }

    /**
     * Add a TriState student to the roster if the input is valid.
     * @param input the input array for the instruction
     */
    private void addTriState(String[] input){
        if(input.length != TRISTATE_ADD_COMMAND_AMOUNT){
            System.out.println("Missing data in command line.");
            return;
        }
        if(!isValidCreditNum(input[3], false))
            return;
        TriState tristate = new TriState(input[1], input[2], Integer.parseInt(input[3]), input[4]);
        roster.add(tristate);
        System.out.println("Student Added.");
    }

    /**
     * Add a International student to the roster if the input is valid.
     * @param input the input array for the instruction
     */
    private void addInternational(String[] input){
        if(input.length == INTERNATIONAL_CREDIT_CHECKNUM){
            System.out.println("Credit hours missing.");
            return;
        }
        if(input.length != INTERNATIONAL_ADD_COMMAND_AMOUNT || !(input[4].toUpperCase().equals("TRUE") || input[4].toUpperCase().equals("FALSE"))){
            System.out.println("Missing data in command line.");
            return;
        }
        if(!isValidCreditNum(input[3], true))
            return;
        boolean abroad = "TRUE".equals(input[4].toUpperCase());
        International international = new International(input[1], input[2], Integer.parseInt(input[3]), abroad);
        roster.add(international);
        System.out.println("Student Added.");
    }

    /**
     * Remove a student from the Roster
     * @param input the input array for the instruction
     */
    private void removeStudent(String[] input) {
        if(input.length != 3){
            System.out.println("Student is not in the roster");
            return;
        }
        Student temp = new Student(input[1], input[2], EMPTY_NUM);
        if(roster.remove(temp)){
            System.out.println("Student removed from the roster.");
        }
        else{
            System.out.println("Student is not in the roster");
        }

    }

    /**
     * Calculates total student Tuition based on roster
     */
    private void calculate() {
        roster.calculate();
        System.out.println("Calculation completed.");
    }

    /**
     * Pay the tuition a certain amount for a certain student
     * @param input the input array for the instruction
     */
    private void payTuition(String[] input) {
        if(input.length <= 5){
            if(input.length == 3){
                System.out.println("Payment amount missing.");
                return;
            }
            if(input.length < 3){
                System.out.println("Invalid Command!");
            }
            Date date = null;
            if(input.length == 5){
                date = new Date(input[4]);
            }
            String name = input[1], major = input[2];
            Double paymentAmount = Double.parseDouble(input[3]);
            Student temp = new Student(name, major);
            Student found = roster.getAStudent(temp);
            if(found == null) {
                System.out.println("Student not in the roster");
                return;
            }
            if(paymentAmount > found.getTuition()){
                System.out.println("Amount is greater than amount due.");
                return;
            }
            if(paymentAmount <= ZERO){
                System.out.println("Invalid amount.");
                return;
            }
            if(!date.isValid()){
                System.out.println("Payment date invalid.");
                return;
            }
            found.makePayment(paymentAmount, date);
            System.out.println("Payment Applied");
        }
    }

    /**
     * Sets the study abroad status based on the input
     * @param input the input array for the instruction
     */
    private void setAbroadStatus(String[] input) {
        if(input.length != 4){
            System.out.println("Invalid command!");
            return;
        }
        String isAbroad = input[3];

    }

    /**
     * Sets the financial aid based on the input
     * @param input the input array for the instruction
     */
    private void setFinancialAid(String[] input) {
        if(input.length != 4){
            if(input.length == 3){
                System.out.println("Missing the amount.");
                return;
            }
            System.out.println("Invalid command!");
            return;
        }
        String name = input[1], major = input[2];
        Double financialAid = Double.parseDouble(input[3]);
        Student temp = new Student(name, major, ZERO);
        Student found = roster.getAStudent(temp);
        if(found == null){
            System.out.println("Student not in the roster");
            return;
        }
        if(!isValidFinancialAid(financialAid)){
            return;
        }
        if(isPartTime(found)){
            return;
        }
        if(!(found instanceof Resident)){
            System.out.println("Not a resident student.");
            return;
        }
        if(!((Resident) found).giveFinancialAid(financialAid)){
            System.out.println("Awarded once already.");
            return;
        }
        System.out.println("Tuition updated.");
    }

    /**
     * Prints the roster given the type of print order
     * @param input the input array for the instruction
     * @param type 1 = no order, 2 = by Names, 3 = those who've paid, ordered by payment date
     */
    private void printRoster(String[] input, int type) {
        if(roster.getSize() == Roster.EMPTY){
            System.out.println("Student roster is empty!");
            return;
        }
        switch(type){
            case 1:
                System.out.println("* list of students in the roster **");
                roster.print();
                break;
            case 2:
                System.out.println("* list of students ordered by name **");
                roster.printByName();
                break;
            case 3:
                System.out.println("* list of students made payments ordered by payment date **");
                roster.printByPaymentDate();
                break;
            default:
                break;
        }
    }

    /**
     * Checks if it's a valid Credit amount based on project details
     * @param creditString string contains the credit amount to be checked
     * @param isInternational a boolean that denotes if international rule is applied
     * @return
     */
    private boolean isValidCreditNum(String creditString, boolean isInternational){
        int credit = 0;
        try {
            credit = Integer.parseInt(creditString);
        }
        catch (Exception e){
            System.out.println("Invalid credit hours.");
            return false;
        };
        if(credit > Student.MAX_CREDITS){
            System.out.println("Credit hours exceed the maximum 24.");
            return false;
        }
        else if (credit < 0) {
            System.out.println("Credit hours cannot be negative.");
            return false;
        }
        else if(credit < Student.MIN_CREDITS){
            System.out.println("Minimum credit hours is 3.");
            return false;
        }
        else if(credit < Student.MIN_FULL_TIME && isInternational){
            System.out.println("International students must enroll at least 12 credits.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the amount of financial aid is valid
     * @param financialAid the amount of financial aid we're checking
     * @return true if it's a valid amount
     */
    private boolean isValidFinancialAid(double financialAid){
        if(financialAid <= 0 || financialAid > MAX_AID){
            System.out.println("Invalid amount.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the student is Part-time
     * @param student the student object we are checking
     * @return true if student is Part-time
     */
    private boolean isPartTime(Student student){
        if(student.getCredits() < Student.MIN_FULL_TIME){
            System.out.println("Parttime student doesn't qualify for the award.");
            return true;
        }
        return false;
    }
}
