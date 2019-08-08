package controller;

import model.Hangman;
import view.View;

public class Controller {

    private Hangman hangmanApp;
    private View view;

    public Controller(Hangman hangman, View view){
        hangmanApp = hangman;
        this.view = view;
    }

    public int getWordLength(){
        return view.getWordLength();
    }

    public void guessWord(){
        view.guessWord(hangmanApp.getOnlyWordInDictionary());
    }

    public boolean guessIsCorrect(){
        return view.guessIsCorrect();
    }

    public boolean hasLetter(char letter){
        return view.hasLetter(letter);
    }

    public int[] getLetterPositions(){
        return view.getLetterPositions();
    }

    public void printCorrectGuessEnding(){
        view.printCorrectGuessEnding();
    }

    public void printIncorrectGuessEnding(){
        view.printIncorrectGuessEnding();
    }
}
