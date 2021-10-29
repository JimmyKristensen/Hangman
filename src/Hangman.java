import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    public static void hangman(String hangmanWordFromFile){
        Scanner sc = new Scanner(System.in);

        int failCounter = 0;
        boolean checkIfGameIsFailed = false;
        List<Character> playerGuesses = new ArrayList<>();

        do {
            String playerGuess = sc.nextLine();
            char playerGuessAsChar = playerGuess.charAt(0);
            playerGuesses.add(Character.toLowerCase(playerGuessAsChar));


            alreadyGuessedLetters(playerGuesses);
            printHangman(playerGuesses, hangmanWordFromFile);


            failCounter = checkForWinOrFail(playerGuessAsChar, hangmanWordFromFile, failCounter);
            checkIfGameIsFailed = checkIfGameShouldEnd(failCounter);
        }
        while(!checkIfGameIsFailed);
    }

    public static void printGreetings(String hangmanWordFromFile){
        System.out.println("Welcome to the hang man game");
        for (int i = 0; i < hangmanWordFromFile.length(); i++) {
            System.out.print("_");
        }
        System.out.println("\nguess a letter");
    }

    public static void printHangman(List<Character> playerGuesses, String hangmanWordFromFile){
        for (int i = 0; i < hangmanWordFromFile.length(); i++) {
            boolean checkIfUserGuessIsInHangmanWord = playerGuesses.contains(hangmanWordFromFile.charAt(i));
            if (checkIfUserGuessIsInHangmanWord) {
                System.out.print(hangmanWordFromFile.charAt(i));
            } else {
                System.out.print("_");
            }
        }
        System.out.println("\nkeep guessing");
    }

    public static void alreadyGuessedLetters(List<Character> playerGuesses){
        for (int i = 0; i < playerGuesses.size(); i++) {
            System.out.print(playerGuesses.get(i) + ", ");
        }
        System.out.println();
    }

    public static int checkForWinOrFail(char playerGuessAsChar, String hangmanWordFromFile, int failCounter){
        if (!(hangmanWordFromFile.indexOf(playerGuessAsChar) != -1)){
            return failCounter + 1;
        }
        return failCounter;
    }

    public static boolean checkIfGameShouldEnd(int failCounter){
        if(failCounter >= 6){
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        File hangmanFile = new File("resources/hangman_word.csv");
        try {
            Scanner scanner = new Scanner(hangmanFile);
            String hangmanWordFromFile = scanner.nextLine();
            printGreetings(hangmanWordFromFile);
            hangman(hangmanWordFromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } // end of main
}  // end of hangman
