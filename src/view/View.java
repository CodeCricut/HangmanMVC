package view;

import util.UserInput;

public class View {


    public void guessWord(String word){
        System.out.println("I think your word is " + word + ".");
    }

    public boolean guessIsCorrect(){
        System.out.print("Was my guess correct? (y)es or (n)o: ");
        return UserInput.getBoolean();
    }



    public int getWordLength(){
        System.out.print("Enter the length of your word: ");
        return UserInput.getIntInput();
    }

    public boolean hasLetter(char letter){
        System.out.print("Does your word contain the letter " + letter + "? (y)es or (n)o: ");
        return UserInput.getBoolean();
    }

    public int[] getLetterPositions(){
        System.out.println("Enter the positions of the letters, separated by spaces.");
        int[] letterPositions = UserInput.getIntArray();
        if (letterPositions.length == 0){
            System.out.println("Invalid input. Enter at least one position.");
            return getLetterPositions();
        }
        return letterPositions;
    }

    public void printCorrectGuessEnding(){
        System.out.println("I guessed the word!");
    }

    public void printIncorrectGuessEnding(){
        System.out.println("I couldn't guess the word... Oh, well.");
    }
}
