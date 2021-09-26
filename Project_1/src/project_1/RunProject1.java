package project_1;
import java.util.Scanner;

/**
 * This is the main running class that handles valid inputs and operations based on the requirements given in Project 1.
 * It takes in inputs with the format -> (Instruction, Album Name, Author, Genre, Date).
 * Intruction: A | Takes 4 inputs | Adds an album
 *             D | Takes 2 Inputs | Deletes an album
 *             L | Takes 2 inputs | Lend out an album
 *             R | Takes 2 inputs | Return an album
 *             P |
 *             Takes no input | Display collection without order
 *             PD| Takes no input | Display collection by release date
 *             PG| Takes no input | Display Collection in Genre Order
 *             Q | takes no input | Stop execution and display "Collection Manager Terminated
 * @author Ethan Chang, Kevin Cubillos
 */
//A,Donda,Kanye,pop,01/01/2001
public class RunProject1 {
    public static void main(String[] args) {
        CollectionManager App = new CollectionManager();
        App.run();
    }
}
