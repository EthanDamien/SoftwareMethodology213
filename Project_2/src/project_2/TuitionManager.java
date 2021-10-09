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
    Roster roster = new Roster();
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
    public boolean checkInstruction(String[] input){
        switch (input[0]){
            case "Q":
                System.out.println("Collection Manager Terminated");
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
            case 1:
                if(input.length != 4){
                    System.out.println("Invalid command!");
                }
                //AR
                break;
            case 2:
                if(input.length != 4){
                    System.out.println("Invalid command!");
                }
                //AN
                break;
            case 3:
                if(input.length != 5){
                    System.out.println("Invalid command!");
                }
                //AT
                break;
            case 4:
                if(input.length != 5){
                    System.out.println("Invalid command!");
                }
                //AI
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
        roster.remove(null);
    }

    /**
     * Calculates total student Tuition based on roster
     */
    private void calculate() {
        roster.calculate();
    }

    /**
     * Pay the tuition a certain amount for a certain student
     * @param input the input array for the instruction
     */
    private void payTuition(String[] input) {
        if(input.length != 5){
            if(input.length == 3){
                System.out.println("Payment amount missing.");
            }
            System.out.println("Invalid command!");
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
        }
        else{
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

    }
}
