/*. Number Guessing Game
This number guessing game is an easy project built on Java where the player has to guess a number given in between a range. 
If the guessed number is right, the player wins else, loses. It also has the concept of limited attempts where the player has 
to guess the number within the limited attempts given. 

Abstract: The UI has an input value option where the player has to enter the guessed value, it also displays the time remaining 
to guess. After completing the limits given, if the guessed number is right, the player wins else loses. The range between the 
number can be from 1 to 100 or 1 to 1000. Also, if the number you’ve guessed is high or low to the actual value, the application 
sends you an alert “Too High” or “Too Low”. After the limited attempt is completed, the actual value is revealed. */

import java.util.*;
import java.lang.Math;

public class NumberGuess {
    public static void main(String[] args) {
        System.out.println(":::::::::  wELCOME TO NUMBER GUESSING GAME  :::::::::");
        System.out.println(" \nWe've generated a number *  ");
        int chance = 5;
        int range = 10;
        int rand = (int) (Math.random() * range);

        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 5; i++) {
            System.out.println(" Guess the value ");
            int guess = sc.nextInt();
            if (guess == rand) {
                System.out.println(" You got it ");
                break;
            } else if (guess > rand) {
                System.out.println(" Input value is greater than the expected ");
            } else if (guess < rand) {
                System.out.println(" The entered value is less than expected ");
            }
            System.out.println(" You've Total 5 chance " + i + " chance used ");

        }

    }
}