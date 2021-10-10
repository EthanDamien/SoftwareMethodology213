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
    private static final int EMPTY_NUM = 0;
    private static final int RESIDENT_ADD_COMMAND_AMOUNT = 4;
    private static final int NONRESIDENT_ADD_COMMAND_AMOUNT = 4;
    private static final int TRISTATE_ADD_COMMAND_AMOUNT = 5;
    private static final int INTERNATIONAL_ADD_COMMAND_AMOUNT = 5;
    private static final int RESIDENT = 1;
    private static final int NONRESIDENT = 2;
    private static final int TRISTATE = 3;
    private static final int INTERNATIONAL = 4;
    private static final int RESIDENT_CREDIT_CHECKNUM = 3;
    private static final int NONRESIDENT_CREDIT_CHECKNUM = 3;
    private static final int INTERNATIONAL_CREDIT_CHECKNUM = 3;
    private static final int ZERO = 0;

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
                addStudent(input, 1);
                break;
            case "AN":
                addStudent(input, 2);
                break;
            case "AT":
                addStudent(input, 3);
                break;
            case "AI":
                addStudent(input, 4);
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
     * Add a student to the Roster with a specified instruction
     * @param input the input array for the instruction
     * @param type the type of instruction, 1 for AR, 2 for AN, 3 for AT, 4 for AI
     */
    private void addStudent(String[] input, int type) {
        switch(type){
            case RESIDENT:
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
                break;
            case NONRESIDENT:
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
                break;
            case TRISTATE:
                if(input.length != TRISTATE_ADD_COMMAND_AMOUNT){
                    System.out.println("Missing data in command line.");
                    return;
                }
                if(!isValidCreditNum(input[3], false))
                    return;
                TriState tristate = new TriState(input[1], input[2], Integer.parseInt(input[3]), input[4]);
                roster.add(tristate);
                System.out.println("Student Added.");
                break;
            case INTERNATIONAL:
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
                break;
        }
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
            Student temp = new Student(name, major, paymentAmount, date);
            Student found = roster.makePayment(temp);
            if(found == null)
                return;
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
            System.out.println("Invalid command!");
        }
        String financialAid = input[3];
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
        int credit = Integer.parseInt(creditString);
        if(credit > 24){
            System.out.println("Credit hours exceed the maximum 24.");
            return false;
        }
        else if (credit < 0) {
            System.out.println("Credit hours cannot be negative.");
            return false;
        }
        else if(credit < 3){
            System.out.println("Minimum credit hours is 3.");
            return false;
        }
        else if(credit < 12 && isInternational){
            System.out.println("International students must enroll at least 12 credits.");
            return false;
        }
        return true;
    }
}
