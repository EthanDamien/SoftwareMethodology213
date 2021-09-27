package project_1;

import java.util.Scanner;
import java.util.StringTokenizer;
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

    private Collection collection = new Collection();

    /** Runs a loop that takes in a certain amount of arguments, it can be terminated by entering Q into the
     * Command Line
     */
    public void run(){
        System.out.println("Collection Manager starts running.");
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
                System.out.println("Invalid command!");
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
            System.out.println("Invalid Command!");
            return;
        }
        Album temp = new Album(input[1], input[2], input[3], input[4]);
        if(!temp.getReleaseDate().isValid()){
            System.out.println("Invalid Date!");
            return;
        }
        if(collection.add(temp)) {
            System.out.println(temp.toString() + " >> added.");
        }
        else{
            System.out.println(temp.toString() + " >> is already in the collection.");
        }

    }

    /** This will Delete the Album from the Collection
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     */
    public void deleteAlbum(String[] input){
        if(input.length != 3){
            System.out.println("Invalid Command!");
        }
        else if(collection.remove(new Album(input[1], input[2]))){
            System.out.println(input[1] + "::" + input[2] + " >> deleted.");
        }
        else{
            System.out.println(input[1] + "::" + input[2] + " >> is not in the collection.");
        }
    }

    /** This will Lend the Album from the Collection
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author
     */
    public void lendAlbum(String[] input){
        if(input.length != 3){
            System.out.println("Invalid Command!");
        }
        else if(collection.lendingOut(new Album(input[1], input[2]))){
            System.out.println(input[1] + "::" + input[2] + " >> lending out and set to not available.");
        }
        else{
            System.out.println(input[1] + "::" + input[2] + " >> is not available.");
        }
    }

    /** This will Return the Album to the Collection
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     */
    public void returnAlbum(String[] input){
        if(input.length != 3){
            System.out.println("Invalid Command!");
        }
        else if(collection.returnAlbum(new Album(input[1], input[2]))){
            System.out.println(input[1] + "::" + input[2] + " >> returning and set to available.");
        }
        else{
            System.out.println(input[1] + "::" + input[2] + " >> return cannot be completed.");
        }
    }

    /** This will Print the Albums in a certain order
     *
     * @param input the input array Index 0 = Instruction, 1 = Name, 2 = Author, 3 = Genre, 4 = Date
     * @param type 1 = no order, 2 = by Date, 3 = by Genre
     */
    public void printAlbums(String[] input, int type){
        if(collection.size() == 0){
            System.out.println("The collection is empty!");
            return;
        }
        switch(type){
            case 1:
                System.out.println("*List of albums in the collection.");
                collection.print();
                break;
            case 2:
                System.out.println("*Album collection by the release dates.");
                collection.printByReleaseDate();
                break;
            case 3:
                System.out.println("*Album collection by genre.");
                collection.printByGenre();
                break;
            default:
                break;
        }
        System.out.println("*End of list");
    }
}
