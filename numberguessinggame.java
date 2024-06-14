import java.util.*;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
        int lowerBound = 1;
        int upperBound = 100;
        int numberToGuess = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
        
        int attempts = 0;
        boolean hasGuessedCorrectly = false;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I've selected a number between 1 and 100. Try to guess it.");
        
        while (!hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = sc.nextInt();
            attempts++;
            
            if (userGuess < lowerBound || userGuess > upperBound) {
                System.out.println("Please enter a number between 1 and 100.");
            } else if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You guessed the number " + numberToGuess + " in " + attempts + " attempts.");
            }
        }
        
        sc.close();
    }
}
