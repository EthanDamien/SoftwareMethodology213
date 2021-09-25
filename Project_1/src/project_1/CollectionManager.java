package project_1;

import java.util.Scanner;

/**
 * This is the main running class that handles valid inputs and operations based on the requirements given in Project 1.
 * It takes in inputs with the format -> (Instruction, Album Name, Author, Genre, Date).
 * Instruction: A | Takes 4 inputs | Adds an album
 *             D | Takes 2 Inputs | Deletes an album
 *             L | Takes 2 inputs | Lend out an album
 *             R | Takes 2 inputs | Return an album
 *             P | Takes no input | Display collection without order
 *             PD| Takes no input | Display collection by release date
 *             PG| Takes no input | Display Collection in Genre Order
 *             Q | takes no input | Stop execution and display "Collection Manager Terminated
 * @author Ethan Chang, Kevin Cubillos
 */
public class CollectionManager {

    /** Runs a loop that takes in a certain amount of arguments, it can be terminated by entering Q into the
     * Command Line
     */
    public void run(){
        Scanner scan = new Scanner(System.in);
        String currString = null;
        int loop = 1;
        boolean rerun = true;
        while(rerun){
            System.out.println("Loop " + loop );
            currString = scan.nextLine();
            String[] input = currString.split(",");
            rerun = checkInstruction(input);
        }
    }

    /** This checks weather the input is valid, and will call each subsequent method that is attributed to each
     * instruction.
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     * @return returns false if instruction is "Q"
     */
    public boolean checkInstruction(String[] input){
        switch (input[0]){
            case "Q":
                System.out.println("Collection Manager Terminated");
                return false;
            case "A":
                addAlbum(input);
                break;
            case "D":
                deleteAlbum(input);
                break;
            case "L":
                lendAlbum(input);
                break;
            case "R":
                returnAlbum(input);
                break;
            case "P":
                printAlbums(input, 1);
                break;
            case "PD":
                printAlbums(input, 2);
                break;
            case "PG":
                printAlbums(input, 3);
                break;
            default:
                System.out.println("Error: Please enter a valid Command.");
                break;
        }
        return true;
    }

    /** This will Add the Album to the Collection
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     */
    public void addAlbum(String[] input){
        if(input.length != 5){
            System.out.println("Error: Please Enter a Valid number of arguments");
        }
    }

    /** This will Delete the Album from the Collection
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     */
    public void deleteAlbum(String[] input){
        if(input.length != 3){
            System.out.println("Error: Please Enter a Valid number of arguments");
        }
    }

    /** This will Lend the Album from the Collection
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     */
    public void lendAlbum(String[] input){
        if(input.length != 3){
            System.out.println("Error: Please Enter a Valid number of arguments");
        }
    }

    /** This will Return the Album to the Collection
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     */
    public void returnAlbum(String[] input){
        if(input.length != 3){
            System.out.println("Error: Please Enter a Valid number of arguments");
        }
    }

    /** This will Print the Albums in a certain order
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     * @param type 1 = no order, 2 = by Date, 3 = by Genre
     */
    public void printAlbums(String[] input, int type){
        switch(type){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
